package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.models.orderinfrastructure.TakenModel;
import petprojects.bookshop.models.shopinfrastructure.PresentLiteratureModel;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TakenRepository extends JpaRepository<TakenModel, Long> {
    /**
     * Finds a TakenModel by counter.
     *
     * @param counter The counter to search for.
     * @return An Optional containing the matching TakenModel, or an empty Optional if not found.
     */
    Optional<TakenModel> findByCounterAndOrder(PresentLiteratureModel counter, OrderModel order);

}
