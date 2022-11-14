package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.Office;
import by.oit_videorental.repository.OfficeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/offices")
public class OfficeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OfficeRepository repository;
    private GenericCrudUtil<Office, OfficeRepository> crudUtil;
    
    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }
    
    @GetMapping("/get")
    public ResponseEntity<List<Office>> getAllOffices() {
        logger.info("GET" + " offices");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable("id") Long id) {
        logger.info("GET " + "offices/{}", id);
        return crudUtil.getEntityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Office> addOffice(@RequestBody Office office) {
        logger.info("POST " + "offices" + " Body:\n {}", office);
        return crudUtil.addEntity(office);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<Office>> addOfficesBatch(@RequestBody(required = true) List<Office> offices) {
        logger.info("POST " + "offices/batch" + " Body:\n {}", offices);
        return crudUtil.addEntitiesBatch(offices);
    }

    //toDo редактирование сетов как в Категориии

    @PutMapping("/edit/{id}")
    public ResponseEntity<Office> replaceFilm(@RequestBody Office office, @PathVariable("id") Long id) {
        logger.info("PUT " + "offices/{}" + " Body:\n {}", id, office);
        return new ResponseEntity<>(repository.findById(id).map(foundOffice -> {

            foundOffice.setAddress(office.getAddress());
            foundOffice.setAddressCity(office.getAddressCity());
            // todo думай можно ли сеты присваивать тут
            return repository.save(foundOffice);
        }).orElseGet(() -> {
            office.setId(id);
            return repository.save(office);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "offices/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "offices/remove/all");
        return crudUtil.deleteAllEntities();
    }
}
