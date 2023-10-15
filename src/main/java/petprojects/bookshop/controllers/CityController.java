package petprojects.bookshop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.shopinfrastructure.CityModel;
import petprojects.bookshop.services.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Get all cities.
     *
     * @return a list of city models
     */
    @GetMapping
    public List<CityModel> getCities() {
        return cityService.getCities();
    }

    /**
     * Add a new city.
     *
     * @param cityModel the city model to be added
     */
    @PostMapping
    public void addNewCity(@RequestBody CityModel cityModel) {
        cityService.addNewCity(cityModel);
    }

    /**
     * Update an existing city.
     *
     * @param cityId     the ID of the city to be updated
     * @param cityModel  the updated city model
     */
    @PutMapping(path = "/{cityId}")
    public void updateAuthor(@PathVariable("cityId") Long cityId,
                             @Valid @RequestBody CityModel cityModel) {
        cityService.updateCity(cityId, cityModel);
    }

    /**
     * Update specific fields of an existing city.
     *
     * @param cityId    the ID of the city to be updated
     * @param cityName  the updated city name (optional)
     */
    @PatchMapping(path = "/{cityId}")
    public void updateCityFields(@PathVariable("cityId") Long cityId,
                                 @RequestParam(required = false) String cityName) {
        cityService.updateCityFields(cityId, cityName);
    }

    /**
     * Delete an existing city.
     *
     * @param cityId the ID of the city to be deleted
     */
    @DeleteMapping(path = "/{cityId}")
    public void deleteCity(@PathVariable("cityId") Long cityId) {
        cityService.deleteCity(cityId);
    }
}