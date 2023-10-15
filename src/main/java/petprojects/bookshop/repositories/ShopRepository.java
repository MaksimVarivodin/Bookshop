package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.shopinfrastructure.ShopModel;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<ShopModel, Long> {
    /**
     * Finds a shop by its address.
     *
     * @param address the address of the shop
     * @return an Optional containing the shop if found, otherwise an empty Optional
     */
    Optional<ShopModel> findByAddress(String address);
}
