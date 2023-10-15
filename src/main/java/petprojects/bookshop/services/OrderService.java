package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    private final String ORDER_EXISTS = "Order: Order with such a number {%s} already exists";
    private final String NO_SUCH_ORDER_EXISTS = "No order with such an id {%s} exists";

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    /**
     * Retrieve all orders from the order repository.
     *
     * @return a list of OrderModel objects
     */
    public List<OrderModel> getOrders() {
        return orderRepository.findAll();
    }
    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return an Optional containing the order if found, or an empty Optional if not found
     */
    public Optional<OrderModel> getOrderById(Long orderId) {
        Optional<OrderModel> order = orderRepository.findById(orderId);
        if (order.isEmpty())
            throw new IllegalStateException(String.format(NO_SUCH_ORDER_EXISTS, orderId));
        return order;
    }
    /**
     * Adds a new order to the order repository.
     *
     * @param orderModel the order model to be added
     * @throws IllegalStateException if an order with the same card number already exists
     */
    public void addNewOrder(OrderModel orderModel) {
        orderRepository.findByCardNumber(orderModel.getCardNumber())
                .ifPresentOrElse(
                        order -> {
                            throw new IllegalStateException(String.format(ORDER_EXISTS, order.getCardNumber()));
                        },
                        ()-> orderRepository.save(orderModel)
                );
    }
    /**
     * Updates the fields of an order.
     *
     * @param orderId    the ID of the order to update
     * @param cardNumber the new card number to set (null if not provided)
     * @param cvv        the new CVV to set (null if not provided)
     * @param month      the new month to set (null if not provided)
     * @param year       the new year to set (null if not provided)
     * @param userId     the ID of the user to set on the order
     * @throws IllegalStateException if the order with the given ID does not exist
     */
    public void updateOrderFields(Long orderId,
                                  Long cardNumber,
                                  Integer cvv,
                                  Integer month,
                                  Integer year,
                                  Long userId) {
        orderRepository.findById(orderId)
                .ifPresentOrElse(
                        order -> {
                            if (cardNumber != null) {
                                order.setCardNumber(cardNumber);
                            }
                            if (cvv != null) {
                                order.setCvv(cvv);
                            }
                            if (month != null) {
                                order.setMonth(month);
                            }
                            if (year != null) {
                                order.setYear(year);
                            }

                            userService.getUserById(userId).ifPresent(order::setUser);

                            orderRepository.save(order);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_ORDER_EXISTS, orderId));
                        }
                );
    }

    /**
     * Updates the order with the given order ID using the information from the provided order model.
     *
     * @param orderId    The ID of the order to update.
     * @param orderModel The order model containing the updated information.
     */
    public void updateOrder(Long orderId, OrderModel orderModel) {
        updateOrderFields(
                orderId,
                orderModel.getCardNumber(),
                orderModel.getCvv(),
                orderModel.getMonth(),
                orderModel.getYear(),
                orderModel.getUser().getUserId()
        );
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId The ID of the order to be deleted.
     * @throws IllegalStateException if no order with the given ID exists.
     */
    public void deleteOrder(Long orderId) {
        orderRepository.findById(orderId)
                .ifPresentOrElse(
                        order -> {
                            orderRepository.deleteById(orderId);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_ORDER_EXISTS, orderId));
                        }
                );
    }

}
