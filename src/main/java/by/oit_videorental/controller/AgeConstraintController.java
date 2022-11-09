package by.oit_videorental.controller;

import by.oit_videorental.model.AgeConstraint;
import by.oit_videorental.repository.AgeConstraintRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/age_constraints")
public class AgeConstraintController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AgeConstraintRepository ageConstraintRepository;

    @GetMapping("/get")
    public ResponseEntity<List<AgeConstraint>> getAllAgeConstraints() {
        List<AgeConstraint> ageConstraints = new ArrayList<>();
        HttpStatus status;
        logger.info("GET" + " age_constraints");
        try {
            ageConstraintRepository.findAll().forEach(ageConstraints::add);
            if (ageConstraints.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                status = HttpStatus.OK;
            }
            return new ResponseEntity<>(ageConstraints, status);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AgeConstraint> getAgeConstraintById(@PathVariable("id") Long id) {
        logger.info("GET " + "age_constraints/{}", id);
        Optional<AgeConstraint> constraint = ageConstraintRepository.findById(id);
        if (constraint.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(constraint.get(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AgeConstraint> addAgeConstraint(@RequestBody AgeConstraint constraint) {
        logger.info("POST " + "age_constraints" + " Body:\n {}", constraint);
        try {
            AgeConstraint ac = ageConstraintRepository.save(constraint);
            return new ResponseEntity<>(ac, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<AgeConstraint>> addAgeConstraintBatch(@RequestBody(required = true) List<AgeConstraint> constraints) {
        logger.info("POST " + "age_constraints/batch" + " Body:\n {}", constraints);
        try {
            return new ResponseEntity<>((List<AgeConstraint>) ageConstraintRepository.saveAll(constraints), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AgeConstraint> replaceAgeConstraint(@RequestBody AgeConstraint ageConstraint, @PathVariable("id") Long id) {
        logger.info("PUT " + "age_constraints/{}" + " Body:\n {}", id, ageConstraint);
        return new ResponseEntity<>(ageConstraintRepository.findById(id).map(foundAgeConstraint -> {
            foundAgeConstraint.setAllowedAge(ageConstraint.getAllowedAge());
            foundAgeConstraint.setConstraintTitle(ageConstraint.getConstraintTitle());
            return ageConstraintRepository.save(foundAgeConstraint);
        }).orElseGet(() -> {
            ageConstraint.setId(id);
            return ageConstraintRepository.save(ageConstraint);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "age_constraints/remove/{}", id);
        ageConstraintRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "age_constraints/remove/all");
        ageConstraintRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
