package petprojects.bookshop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.shopinfrastructure.ShopModel;
import petprojects.bookshop.services.ShopService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shops")
public class ShopController {
    private final ShopService shopService;

    // Constructor
    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    /**
     * Get all shops
     *
     * @return List of ShopModel objects representing all the shops
     */
    @GetMapping
    public List<ShopModel> getShops() {
        return shopService.getShops();
    }

    /**
     * Add a new shop
     *
     * @param shopModel The ShopModel object containing the details of the new shop
     */
    @PostMapping
    public void addNewShop(ShopModel shopModel) {
        shopService.addNewShop(shopModel);
    }

    /**
     * Update an existing shop
     *
     * @param shopId     The ID of the shop to be updated
     * @param shopModel  The ShopModel object containing the updated details of the shop
     */
    @PutMapping(path = "/{shopId}")
    public void updateShop(@PathVariable("shopId") Long shopId,
                           @Valid @RequestBody ShopModel shopModel) {
        shopService.updateShop(shopId, shopModel);
    }

    /**
     * Update specific fields of an existing shop
     *
     * @param shopId            The ID of the shop to be updated
     * @param shopName          The updated name of the shop (optional)
     * @param address           The updated address of the shop (optional)
     * @param workersAmount     The updated number of workers in the shop (optional)
     * @param bookStorageSize   The updated book storage size of the shop (optional)
     * @param cityId            The updated ID of the city where the shop is located (optional)
     */
    @PatchMapping(path = "/{shopId}")
    public void updateShopFields(@PathVariable("shopId") Long shopId,
                                 @RequestParam(required = false) String shopName,
                                 @RequestParam(required = false) String address,
                                 @RequestParam(required = false) Integer workersAmount,
                                 @RequestParam(required = false) Integer bookStorageSize,
                                 @RequestParam(required = false) Long cityId) {
        shopService.updateShopFields(shopId, shopName, address, workersAmount, bookStorageSize, cityId);
    }

    /**
     * Delete a shop
     *
     * @param shopId The ID of the shop to be deleted
     */
    @DeleteMapping(path = "/{shopId}")
    public void deleteShop(@PathVariable("shopId") Long shopId) {
        shopService.deleteShop(shopId);
    }
}