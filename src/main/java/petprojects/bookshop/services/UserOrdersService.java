package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.models.userinfrastructure.UserModel;

import java.util.List;

@Service
public class UserOrdersService {
    private final UserService userService ;
    @Autowired
    public UserOrdersService(UserService userService ) {
        this.userService  = userService ;
    }
    public List<OrderModel> getUserOrdersById(Long userId) {
        UserModel user = userService.getUserById(userId);
        if (user == null) {
            throw new NullPointerException("User not found");
        }
        return user.getOrders();
    }
}
