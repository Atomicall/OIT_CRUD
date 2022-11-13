package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.Author;
import by.oit_videorental.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/film_authors")
public class AuthorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthorRepository repository;

    private GenericCrudUtil<Author, AuthorRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }

     @GetMapping("/get")
    public ResponseEntity<List<Author>> getAllAuthors() {
        logger.info("GET" + " film_authors");
        return crudUtil.getAllEntities();
    }
    
     @GetMapping("/get/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
        logger.info("GET " + "film_authors/{}", id);
        return crudUtil.getEntityById(id);
    }
    
     @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        logger.info("POST " + "film_authors" + " Body:\n {}", author);
        return crudUtil.addEntity(author);
    }
    
     @PostMapping("/add/batch")
    public ResponseEntity<List<Author>> addAuthorBatch(@RequestBody(required = true) List<Author> constraints) {
        logger.info("POST " + "film_authors/batch" + " Body:\n {}", constraints);
        return crudUtil.addEntitiesBatch(constraints);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> replaceCategory(@RequestBody Author author, @PathVariable("id") Long id) {
        logger.info("PUT " + "categories/{}" + " Body:\n {}", id, author);
        return new ResponseEntity<>(repository.findById(id).map(foundAuthor -> {
            foundAuthor.setFirstName(author.getFirstName());
            foundAuthor.setLastName(author.getLastName());
            return repository.save(foundAuthor);
        }).orElseGet(() -> {
            author.setId(id);
            return repository.save(author);
        }), HttpStatus.OK);
    }

    
     @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "film_authors/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "film_authors/remove/all");
        return crudUtil.deleteAllEntities();
    }




}
