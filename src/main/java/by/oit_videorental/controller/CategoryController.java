package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.Category;
import by.oit_videorental.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CategoryRepository repository;
    private GenericCrudUtil<Category, CategoryRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        repository.findAll();
        this.crudUtil = new GenericCrudUtil<>(repository);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getAllAgeConstraints() {
        logger.info("GET" + " categories");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getAgeConstraintById(@PathVariable("id") Long id) {
        logger.info("GET " + "categories/{}", id);
        return crudUtil.getEntityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addAgeConstraint(@RequestBody Category category) {
        logger.info("POST " + "categories" + " Body:\n {}", category);
        return crudUtil.addEntity(category);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<Category>> addAgeConstraintBatch(@RequestBody(required = true) List<Category> constraints) {
        logger.info("POST " + "categories/batch" + " Body:\n {}", constraints);
        return crudUtil.addEntitiesBatch(constraints);
    }

    // Todo переделать на crudUtil
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> replaceAgeConstraint(@RequestBody Category category, @PathVariable("id") Long id) {
        logger.info("PUT " + "categories/{}" + " Body:\n {}", id, category);
//        return new ResponseEntity<>(repository.findById(id).map(foundAgeConstraint -> {
//            foundAgeConstraint.setAllowedAge(ageConstraint.getAllowedAge());
//            foundAgeConstraint.setConstraintTitle(ageConstraint.getConstraintTitle());
//            return repository.save(foundAgeConstraint);
//        }).orElseGet(() -> {
//            ageConstraint.setId(id);
//            return repository.save(ageConstraint);
//        }), HttpStatus.OK);
        return null;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "categories/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "categories/remove/all");
        return crudUtil.deleteAllEntities();
    }
}
