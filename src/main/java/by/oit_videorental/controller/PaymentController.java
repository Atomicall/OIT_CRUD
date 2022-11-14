package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.Payment;
import by.oit_videorental.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PaymentRepository repository;
     private GenericCrudUtil<Payment, PaymentRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Payment>> getAllPayments() {
        logger.info("GET" + " payments");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Long id) {
        logger.info("GET " + "payments/{}", id);
        return crudUtil.getEntityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
        logger.info("POST " + "payments" + " Body:\n {}", payment);
        return crudUtil.addEntity(payment);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<Payment>> addPaymentsBatch(@RequestBody(required = true) List<Payment> payments) {
        logger.info("POST " + "payments/batch" + " Body:\n {}", payments);
        return crudUtil.addEntitiesBatch(payments);
    }

    //toDo редактирование сетов как в Категориии

    @PutMapping("/edit/{id}")
    public ResponseEntity<Payment> replaceFilm(@RequestBody Payment payment, @PathVariable("id") Long id) {
        logger.info("PUT " + "payments/{}" + " Body:\n {}", id, payment);
        return new ResponseEntity<>(repository.findById(id).map(foundPayment -> {

            foundPayment.setPaymentDate(payment.getPaymentDate());
            foundPayment.setPaymentAmount(payment.getPaymentAmount());
            foundPayment.setPaymentDate(payment.getPaymentDate());

            // todo думай можно ли сеты присваивать тут
            return repository.save(foundPayment);
        }).orElseGet(() -> {
            payment.setId(id);
            return repository.save(payment);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "payments/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "payments/remove/all");
        return crudUtil.deleteAllEntities();
    }
}
