package by.oit_videorental.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// сделать бином но prototype
// но как сделать Autowired к параметризированному типу7

public class GenericCrudUtil<T, K extends CrudRepository<T, Long>> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private K repository;

    public GenericCrudUtil(K repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<T>> getAllEntities() {
        List<T> entitiesList = new ArrayList<>();
        HttpStatus status;
        try {
            repository.findAll().forEach(entitiesList::add);
            if (entitiesList.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                status = HttpStatus.OK;
            }
            return new ResponseEntity<>(entitiesList, status);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<T> getEntityById(Long id) {
        Optional<T> constraint = repository.findById(id);
        if (constraint.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(constraint.get(), HttpStatus.OK);
    }

    public ResponseEntity<T> addEntity(T entityToAdd) {
        try {
            T savedEntity = repository.save(entityToAdd);
            return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<T>> addEntitiesBatch(List<T> entities) {
        try {
            return new ResponseEntity<>((List<T>) repository.saveAll(entities), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Можно переделать на какой-то Util чтобы можно было конструировать новый T на основе старого T
    // Или заморочться и переписать все на рефликсии
//    public ResponseEntity<T> replaceEntity(T newEntity, Long id){
//        return new ResponseEntity<>(repository.findById(id).map(foundAgeConstraint -> {
//            foundAgeConstraint.setAllowedAge(ageConstraint.getAllowedAge());
//            foundAgeConstraint.setConstraintTitle(ageConstraint.getConstraintTitle());
//            return repository.save(foundAgeConstraint);
//        }).orElseGet(() -> {
//            ageConstraint.setId(id);
//            return repository.save(ageConstraint);
//        }), HttpStatus.OK);
//    }
    public ResponseEntity<?> deleteEntityById(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> deleteAllEntities() {
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
