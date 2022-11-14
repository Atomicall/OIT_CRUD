package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.FilmCopy;
import by.oit_videorental.repository.FilmCopyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/films_copies")
public class FilmCopyController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FilmCopyRepository repository;
    private GenericCrudUtil<FilmCopy, FilmCopyRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }
    
     @GetMapping("/get")
    public ResponseEntity<List<FilmCopy>> getAllFilmCopies() {
        logger.info("GET" + " films_copies");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FilmCopy> getFilmCopyById(@PathVariable("id") Long id) {
        logger.info("GET " + "films_copies/{}", id);
        return crudUtil.getEntityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<FilmCopy> addFilmCopy(@RequestBody FilmCopy film) {
        logger.info("POST " + "films_copies" + " Body:\n {}", film);
        return crudUtil.addEntity(film);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<FilmCopy>> addFilmCopiesBatch(@RequestBody(required = true) List<FilmCopy> films) {
        logger.info("POST " + "films_copies/batch" + " Body:\n {}", films);
        return crudUtil.addEntitiesBatch(films);
    }

    //toDo редактирование сетов как в Категориии

    @PutMapping("/edit/{id}")
    public ResponseEntity<FilmCopy> replaceFilmCopy(@RequestBody FilmCopy filmCopy, @PathVariable("id") Long id) {
        logger.info("PUT " + "films_copies/{}" + " Body:\n {}", id, filmCopy);
        return new ResponseEntity<>(repository.findById(id).map(foundFilmCopy -> {
            foundFilmCopy.setFilmStatus(filmCopy.getFilmStatus());
            // todo думай можно ли сеты присваивать тут
            return repository.save(foundFilmCopy);
        }).orElseGet(() -> {
            filmCopy.setId(id);
            return repository.save(filmCopy);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "films_copies/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "films_copies/remove/all");
        return crudUtil.deleteAllEntities();
    }
    

}
