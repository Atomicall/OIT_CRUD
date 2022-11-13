package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.AgeConstraint;
import by.oit_videorental.repository.AgeConstraintRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/age_constraints")
public class AgeConstraintController {
    @Autowired
    private AgeConstraintRepository repository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GenericCrudUtil<AgeConstraint, AgeConstraintRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        repository.findAll();
        this.crudUtil = new GenericCrudUtil<>(repository);
    }

    @GetMapping("/get")
    public ResponseEntity<List<AgeConstraint>> getAllAgeConstraints() {
        logger.info("GET" + " age_constraints");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AgeConstraint> getAgeConstraintById(@PathVariable("id") Long id) {
        logger.info("GET " + "age_constraints/{}", id);
        return crudUtil.getEntityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AgeConstraint> addAgeConstraint(@RequestBody AgeConstraint constraint) {
        logger.info("POST " + "age_constraints" + " Body:\n {}", constraint);
        return crudUtil.addEntity(constraint);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<AgeConstraint>> addAgeConstraintBatch(@RequestBody(required = true) List<AgeConstraint> constraints) {
        logger.info("POST " + "age_constraints/batch" + " Body:\n {}", constraints);
        return crudUtil.addEntitiesBatch(constraints);
    }

    // Todo переделать на crudUtil
    @PutMapping("/edit/{id}")
    public ResponseEntity<AgeConstraint> replaceAgeConstraint(@RequestBody AgeConstraint ageConstraint, @PathVariable("id") Long id) {
        logger.info("PUT " + "age_constraints/{}" + " Body:\n {}", id, ageConstraint);
        return new ResponseEntity<>(repository.findById(id).map(foundAgeConstraint -> {
            foundAgeConstraint.setAllowedAge(ageConstraint.getAllowedAge());
            foundAgeConstraint.setConstraintTitle(ageConstraint.getConstraintTitle());
            return repository.save(foundAgeConstraint);
        }).orElseGet(() -> {
            ageConstraint.setId(id);
            return repository.save(ageConstraint);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "age_constraints/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "age_constraints/remove/all");
        return crudUtil.deleteAllEntities();
    }
}
