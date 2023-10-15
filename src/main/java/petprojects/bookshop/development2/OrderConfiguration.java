package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.models.userinfrastructure.UserModel;
import petprojects.bookshop.services.OrderService;
import petprojects.bookshop.services.UserService;

@Configuration
public class OrderConfiguration {
    @Bean
    CommandLineRunner commandLineRunOrderConfig(OrderService orderService, UserService userService) {
//            Long cardNumber,
//            Integer cvv,
//            Integer month,
//            Integer year,
//            UserModel user
        return args -> {
            UserModel userModel = userService.getUserById(1L).get();

            // Create the first order model
            OrderModel orderModel1 = new OrderModel(
                   24323445L,
                    232,
                    12,
                    25,
                    userModel
            );
            orderService.addNewOrder(orderModel1);
        };
    }
}
