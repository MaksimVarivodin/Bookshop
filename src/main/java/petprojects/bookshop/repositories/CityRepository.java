package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.shopinfrastructure.CityModel;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {
    /**
     * Retrieves a city model by its name.
     * @param cityName The name of the city.
     * @return An optional CityModel object representing the city, or an empty optional if the city is not found.
     */
    Optional<CityModel> findByCityName(String cityName);
}
