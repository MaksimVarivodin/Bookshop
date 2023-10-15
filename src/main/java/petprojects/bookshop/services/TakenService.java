package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.orderinfrastructure.TakenModel;
import petprojects.bookshop.repositories.TakenRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TakenService {
    private final TakenRepository takenRepository;
    private final OrderService orderService;

    private final PresentLiteratureService presentLiteratureService;

    private final String TAKEN_EXISTS = "Taken: Taken with such a number {%s} already exists";
    private final String NO_SUCH_TAKEN_EXISTS = "No Taken with such an id {%s} exists";

    @Autowired
    public TakenService(TakenRepository takenRepository,
                       OrderService orderService,
                       PresentLiteratureService presentLiteratureService) {
        this.takenRepository = takenRepository;
        this.orderService = orderService;
        this.presentLiteratureService = presentLiteratureService;
    }

    public List<TakenModel> getTaken() {
        return takenRepository.findAll();
    }


    public void addNewTaken(TakenModel takenModel) {
        takenRepository.findByDate(takenModel.getDate())
                .ifPresentOrElse(
                        order -> {
                            throw new IllegalStateException(String.format(TAKEN_EXISTS, order.getTakenId()));
                        },
                        () -> takenRepository.save(takenModel)
                );
    }

    public void updateTakenFields(Long takenId,
                                  LocalDate date,
                                  Integer amount,
                                  Long orderId,
                                  Long counterId) {
        takenRepository.findById(takenId)
                .ifPresentOrElse(
                        taken -> {
                            if (date != null)
                                taken.setDate(date);
                            if (amount != null)
                                taken.setAmount(amount);
                            if (orderId != null)
                                orderService.getOrderById(orderId).ifPresent(taken::setOrder);
                            if (counterId != null)
                                presentLiteratureService.getPresentLiteratureById(counterId).ifPresent(taken::setCounter);

                            takenRepository.save(taken);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_TAKEN_EXISTS, takenId));
                        }
                );
    }

    public void updateTaken(Long takenId, TakenModel takenModel) {
        updateTakenFields(takenId,
                takenModel.getDate(),
                takenModel.getAmount(),
                takenModel.getOrder().getOrderId(),
                takenModel.getCounter().getCounterId());
    }

    public void deleteTaken(Long orderId) {
        takenRepository.findById(orderId)
                .ifPresentOrElse(
                        order -> {
                            takenRepository.deleteById(orderId);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_TAKEN_EXISTS, orderId));
                        }
                );
    }
}
