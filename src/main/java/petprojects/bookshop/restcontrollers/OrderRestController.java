package petprojects.bookshop.restcontrollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {
    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Get all orders.
     *
     * @return a list of order models
     */
    @GetMapping
    public List<OrderModel> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{orderId}")
    public OrderModel getOrderById(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderById(orderId);
    }
    /**
     * Add a new order.
     *
     * @param orderModel the order model to be added
     */
    @PostMapping
    public void addNewOrder(@RequestBody OrderModel orderModel) {
        orderService.addNewOrder(orderModel);
    }

    /**
     * Update an existing order.
     *
     * @param orderId     the ID of the order to be updated
     * @param orderModel  the updated order model
     */
    @PutMapping(path = "/{orderId}")
    public void updateOrder(@PathVariable("orderId") Long orderId,
                            @Valid @RequestBody OrderModel orderModel) {
        orderService.updateOrder(orderId, orderModel);
    }

    /**
     * Update specific fields of an existing order.
     *
     * @param orderId    the ID of the order to be updated
     * @param cardNumber the updated card number (optional)
     * @param cvv        the updated CVV (optional)
     * @param month      the updated month (optional)
     * @param year       the updated year (optional)
     * @param userId     the updated user ID (optional)
     */
    @PatchMapping(path = "/{orderId}")
    public void updateOrderFields(@PathVariable("orderId") Long orderId,
                                  @RequestParam(required = false) Long cardNumber,
                                  @RequestParam(required = false) Integer cvv,
                                  @RequestParam(required = false) Integer month,
                                  @RequestParam(required = false) Integer year,
                                  @RequestParam(required = false) Long userId) {
        orderService.updateOrderFields(orderId,
                cardNumber,
                cvv,
                month,
                year,
                userId);
    }

    /**
     * Delete an existing order.
     *
     * @param orderId the ID of the order to be deleted
     */
    @DeleteMapping(path = "/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
    }

}