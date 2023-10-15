package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    /**
     * Finds an order model by card number.
     *
     * @param number the card number to search for
     * @return an optional containing the found order model, or an empty optional if not found
     */
    Optional<OrderModel> findByCardNumber(Long number);
}
