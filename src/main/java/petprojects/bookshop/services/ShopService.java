package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petprojects.bookshop.models.shopinfrastructure.ShopModel;
import petprojects.bookshop.repositories.ShopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final CityService cityService;

    private final String SHOP_EXISTS = "Shop: Shop with such an id {%s} already exists";

    private final String NO_SUCH_SHOP_EXISTS = "No shop with such an id {%s} exists";

    @Autowired
    public ShopService(ShopRepository shopRepository,
                      CityService cityService) {
        this.shopRepository = shopRepository;
        this.cityService = cityService;
    }


    public List<ShopModel> getShops() {
        return shopRepository.findAll();
    }

    public ShopModel getShopById(Long shopId) {
        Optional<ShopModel> shop = shopRepository.findById(shopId);
        if (shop.isEmpty())
            throw new IllegalStateException(String.format(NO_SUCH_SHOP_EXISTS, shopId));
        else
            return shop.get();
    }
    @Transactional
    public void addNewShop(ShopModel shopModel) {
        shopRepository.findByAddress(shopModel.getAddress()).ifPresentOrElse(
                shop -> {
                    throw new IllegalStateException(String.format(SHOP_EXISTS, shop.getAddress()));
                },
                () -> shopRepository.save(shopModel)
        );
    }
    @Transactional
    public void updateShopFields(Long shopId,
                                 String shopName,
                                 String address,
                                 Integer workersAmount,
                                 Integer bookStorageSize,
                                 Long cityId) {
        shopRepository.findById(shopId)
                .ifPresentOrElse(
                        shop -> {
                            if (shopName != null && !shopName.isEmpty())
                                shop.setShopName(shopName);
                            if (address != null && !address.isEmpty())
                                shop.setAddress(address);
                            if (workersAmount != null)
                                shop.setWorkersAmount(workersAmount);
                            if (bookStorageSize != null)
                                shop.setBookStorageSize(bookStorageSize);
                            if (cityId != null)
                                shop.setCity(cityService.getCityById(cityId));
                            shopRepository.save(shop);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_SHOP_EXISTS, shopId));
                        });
    }

    @Transactional
    public void updateShop(Long shopId, ShopModel shopModel) {
        updateShopFields(
                shopId,
                shopModel.getShopName(),
                shopModel.getAddress(),
                shopModel.getWorkersAmount(),
                shopModel.getBookStorageSize(),
                shopModel.getCity().getCityId()
        );
    }

    @Transactional
    public void deleteShop(Long shopId) {
        shopRepository.findById(shopId)
                .ifPresentOrElse(
                        shopRepository::delete,
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_SHOP_EXISTS, shopId));
                        });
    }
}
