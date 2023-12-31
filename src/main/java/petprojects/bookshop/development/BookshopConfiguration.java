package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.models.literatureinfrastructure.AuthorModel;
import petprojects.bookshop.models.literatureinfrastructure.GenreModel;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.models.orderinfrastructure.TakenModel;
import petprojects.bookshop.models.shopinfrastructure.CityModel;
import petprojects.bookshop.models.shopinfrastructure.PresentLiteratureModel;
import petprojects.bookshop.models.shopinfrastructure.ShopModel;
import petprojects.bookshop.models.userinfrastructure.UserModel;
import petprojects.bookshop.models.userinfrastructure.UserRoles;
import petprojects.bookshop.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class BookshopConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(
                    AuthorService authorService,
                    CityService cityService,
                    GenreService genreService,
                    LiteratureInfoService literatureInfoService,
                    OrderService orderService,
                    PresentLiteratureService presentLiteratureService,
                    ShopService shopService,
                    TakenService takenService,
                    UserService userService
    ) {
        return args -> {

            AuthorModel authorModel = new AuthorModel(
                    "Іва́н Петро́вич Котляре́вський ",
                    LocalDate.of(1769, 8, 29),
                    LocalDate.of(1838, 10, 29),
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/%D0%9A%D0%BE%D1%82%D0%BB%D1%8F%D1%80%D0%B5%D0%B2%D1%81%D1%8C%D0%BA%D0%B8%D0%B9_%D0%86.jpg/270px-%D0%9A%D0%BE%D1%82%D0%BB%D1%8F%D1%80%D0%B5%D0%B2%D1%81%D1%8C%D0%BA%D0%B8%D0%B9_%D0%86.jpg",
                    "Український письменник, військовий, класик нової української літератури, громадський діяч." +
                            "<br>Його поема «Енеїда» (1798) стала великим твором загальнонаціонального значення нової української літератури, написаним народною мовою." +
                            "<br>Котляревський зробив найвагоміший внесок у становлення сучасної української літературної мови. " +
                            "<br>В умовах занепаду всіх різновидів староукраїнської писемної мови, поема «Енеїда», п'єси «Наталка Полтавка» " +
                            "і «Москаль-чарівник», написані на основі живого усного мовлення народу, започаткували новий етап формування літературної мови. "
            );
            authorService.addNewAuthor(authorModel);
            GenreModel genreModel = new GenreModel(
                "Поема"
            );
            genreService.addNewGenre(genreModel);

            LiteratureInfoModel literatureInfoModel = new LiteratureInfoModel(
                    336,
                    7000,
                    "Енеїда",
                    BigDecimal.valueOf(120),
                    "Українська бурлескно-травестійна поема, написана українським письменником Іваном Котляревським, заснована на сюжеті однойменної класичної поеми римського поета Вергілія." +
                            "<br>Складається з шести частин, на відміну від дванадцяти частин Вергілія. " +
                            "<br>Написана чотиристопним ямбом. ",
                    "https://static.yakaboo.ua/media/cloudflare/product/webp/600x840/_/3/_3__1_11.jpg",
                    authorModel,
                    genreModel
            );
            literatureInfoService.addNewLiterature(literatureInfoModel);
            CityModel cityModel = new CityModel(
                    "Харків"
            );
            cityService.addNewCity(cityModel);

            UserModel userModel = new UserModel(
                "Bob",
                    "Bean",
                    "bbean@ya.ru",
                    "123456",
                    123456789L,
                    UserRoles.USER,
                    false,
                    true,
                    "https://cdn-icons-png.flaticon.com/512/149/149071.png"
            );
            userService.addNewUser(userModel);

            ShopModel shopModel = new ShopModel(
                "Харків БукМаг",
                    "Пушкінська вул. 2",
                    10,
                    100,
                    cityModel
            );

            shopService.addNewShop(shopModel);
            PresentLiteratureModel presentLiteratureModel = new PresentLiteratureModel(
                10,
                literatureInfoModel,
                shopModel
            );
            presentLiteratureService.addNewPresentLiterature(presentLiteratureModel);

            OrderModel orderModel = new OrderModel(
                1234567890123456L,
                LocalDateTime.now(),
                123,
                12,
                24,
                userModel
            );
            orderService.addNewOrder(orderModel);

            TakenModel takenModel = new TakenModel(
                    5,
                orderModel,
                presentLiteratureModel
            );
            takenService.addNewTaken(takenModel);
        };
    }
}
