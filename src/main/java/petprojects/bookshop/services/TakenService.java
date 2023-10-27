package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public TakenModel getTakenById(Long takenId) {
        Optional<TakenModel> taken = takenRepository.findById(takenId);
        if (taken.isEmpty()) {
            throw new IllegalStateException(String.format(NO_SUCH_TAKEN_EXISTS, takenId));
        } else
            return taken.get();
    }
    @Transactional
    public void addNewTaken(TakenModel takenModel) {
        takenRepository.findByCounterAndOrder(takenModel.getCounter(), takenModel.getOrder())
                .ifPresentOrElse(
                        order -> {
                            throw new IllegalStateException(String.format(TAKEN_EXISTS, order.getTakenId()));
                        },
                        () -> takenRepository.save(takenModel)
                );
    }
    @Transactional
    public void updateTakenFields(Long takenId,
                                  Integer amount,
                                  Long orderId,
                                  Long counterId) {
        takenRepository.findById(takenId)
                .ifPresentOrElse(
                        taken -> {

                            if (amount != null)
                                taken.setAmount(amount);
                            if (orderId != null)
                                taken.setOrder(orderService.getOrderById(orderId));

                            if (counterId != null)
                                taken.setCounter(presentLiteratureService.getPresentLiteratureById(counterId));

                            takenRepository.save(taken);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_TAKEN_EXISTS, takenId));
                        }
                );
    }
    @Transactional
    public void updateTaken(Long takenId, TakenModel takenModel) {
        updateTakenFields(takenId,
                takenModel.getAmount(),
                takenModel.getOrder().getOrderId(),
                takenModel.getCounter().getCounterId());
    }
    @Transactional
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
