package petprojects.bookshop.dbModels.orderinfrastructure.taken;

import java.time.LocalDate;

public class TakenModel {
    Long id;
    LocalDate date;
    int amount;
    Long orderId;
    Long counterId;
}
