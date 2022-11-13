package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.AgeConstraint;
import by.oit_videorental.model.Category;
import by.oit_videorental.repository.AgeConstraintRepository;
import by.oit_videorental.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AgeConstraintRepository ageConstraintRepository;
    @Autowired
    private CategoryRepository repository;
    private GenericCrudUtil<Category, CategoryRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("GET" + " categories");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        logger.info("GET " + "categories/{}", id);
        return crudUtil.getEntityById(id);
    }

    @GetMapping("/get/{id}/age_constraint")
    public ResponseEntity<AgeConstraint> getAgeConstraintsFromCategoryById(@PathVariable("id") Long id) {
        logger.info("GET " + "categories/{}/age_constraints", id);
        if (!repository.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        AgeConstraint ageConstraints = ageConstraintRepository.findAgeConstraintByCategoriesId(id);
        return new ResponseEntity<>(ageConstraints, HttpStatus.OK);
    }

    @PostMapping("/add/{id}/age_constraint")
    public ResponseEntity<Category> addAgeConstraintsToCategoryById(@PathVariable("id") Long id, @RequestBody AgeConstraint constraint) {
        logger.info("POST " + "add/{}/age_constraints" + " Body:\n {}", id, constraint);
        Optional<Category> categoryFromRepository = repository.findById(id);
        if (categoryFromRepository.isEmpty()) {
            logger.info("Category with id:{} not found in CategoryRepository", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Category editedCategory = categoryFromRepository.map((category) -> {
            long ageConstraintId = constraint.getId();
            if (ageConstraintRepository.existsById(ageConstraintId)) {
                // есть в базе, нашли по ид
                AgeConstraint constraintFromRepository = ageConstraintRepository.findById(ageConstraintId).get();
                category.addAgeConstraint(constraintFromRepository);
                repository.save(category);
                return category;
            } else {
                // не нашли по ид в базе
                // todo сделать поиск по названию
                logger.info("AgeConstraint with id:{} not found in AgeConstraintRepository, skipping", ageConstraintId);
                return category;
            }
        }).get();
        return new ResponseEntity<>(editedCategory, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        logger.info("POST " + "categories" + " Body:\n {}", category);
        return crudUtil.addEntity(category);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<Category>> addCategoryBatch(@RequestBody(required = true) List<Category> categoryList) {
        logger.info("POST " + "categories/batch" + " Body:\n {}", categoryList);
        return crudUtil.addEntitiesBatch(categoryList);
    }

    // Todo переделать на crudUtil

    //todo подумать, стоит ил в этом методе давать возможность менять ageConstraint по id
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> replaceCategory(@RequestBody Category category, @PathVariable("id") Long id) {
        logger.info("PUT " + "categories/{}" + " Body:\n {}", id, category);
        return new ResponseEntity<>(repository.findById(id).map(foundCategory -> {
            foundCategory.setCategoryTitle(category.getCategoryTitle());
            return repository.save(foundCategory);
        }).orElseGet(() -> {
            category.setId(id);
            return repository.save(category);
        }), HttpStatus.OK);
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
