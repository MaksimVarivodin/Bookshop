package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.shopinfrastructure.CityModel;
import petprojects.bookshop.repositories.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    private final String CITY_EXISTS = "City: City with such a name {%s} already exists";

    private final String NO_SUCH_CITY_EXISTS = "No city with such an id {%s} exists";

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Retrieves a list of all city models.
     *
     * @return the list of city models
     */
    public List<CityModel> getCities() {
        return cityRepository.findAll();
    }

    /**
     * Retrieves a city by its ID.
     *
     * @param cityId The ID of the city to retrieve.
     * @return An Optional containing the CityModel if found, or an empty Optional if not found.
     */
    public Optional<CityModel> getCityById(Long cityId) {
        Optional<CityModel> city = cityRepository.findById(cityId);
        if (city.isEmpty())
            throw new IllegalStateException(String.format(NO_SUCH_CITY_EXISTS, cityId));
        return city;
    }

    /**
     * Adds a new city to the city repository.
     *
     * @param cityModel The city model to be added.
     * @throws IllegalStateException If the city already exists in the repository.
     */
    public void addNewCity(CityModel cityModel) {
        cityRepository.findByCityName(cityModel.getCityName())
                .ifPresentOrElse(
                        city -> {
                            throw new IllegalStateException(String.format(CITY_EXISTS, city.getCityName()));
                        },
                        () -> cityRepository.save(cityModel)
                );
    }

    /**
     * Updates the fields of a city with the given city ID.
     *
     * @param cityId    The ID of the city to update.
     * @param cityName  The new name of the city.
     * @throws IllegalStateException If no city exists with the given ID.
     */
    public void updateCityFields(Long cityId, String cityName) {
        cityRepository.findById(cityId).ifPresentOrElse(
                city -> {
                    if (cityName != null && !cityName.isEmpty())
                        city.setCityName(cityName);
                    cityRepository.save(city);
                },
                () -> {
                    throw new IllegalStateException(String.format(NO_SUCH_CITY_EXISTS, cityId));
                }
        );
    }

    /**
     * Updates the city with the given cityId using the information from the provided CityModel object.
     *
     * @param cityId    The ID of the city to update.
     * @param cityModel The CityModel object containing the updated information.
     */
    public void updateCity(Long cityId, CityModel cityModel) {
        updateCityFields(cityId, cityModel.getCityName());
    }

    /**
     * Deletes a city with the given cityId.
     * If the city does not exist, an IllegalStateException is thrown.
     *
     * @param cityId the id of the city to delete
     * @throws IllegalStateException if no city exists with the given cityId
     */
    public void deleteCity(Long cityId) {
        cityRepository.findById(cityId)
                .ifPresentOrElse(
                        cityRepository::delete,
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_CITY_EXISTS, cityId));
                        }
                );
    }
}
