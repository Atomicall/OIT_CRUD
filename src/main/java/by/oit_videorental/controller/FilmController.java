package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.Film;
import by.oit_videorental.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FilmRepository repository;
    private GenericCrudUtil<Film, FilmRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }
    
    @GetMapping("/get")
    public ResponseEntity<List<Film>> getAllClients() {
        logger.info("GET" + " films");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable("id") Long id) {
        logger.info("GET " + "films/{}", id);
        return crudUtil.getEntityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        logger.info("POST " + "films" + " Body:\n {}", film);
        return crudUtil.addEntity(film);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<Film>> addFilmsBatch(@RequestBody(required = true) List<Film> films) {
        logger.info("POST " + "films/batch" + " Body:\n {}", films);
        return crudUtil.addEntitiesBatch(films);
    }

    //toDo редактирование сетов как в Категориии

    @PutMapping("/edit/{id}")
    public ResponseEntity<Film> replaceFilm(@RequestBody Film film, @PathVariable("id") Long id) {
        logger.info("PUT " + "films/{}" + " Body:\n {}", id, film);
        return new ResponseEntity<>(repository.findById(id).map(foundFilm -> {
            foundFilm.setFilmTitle(film.getFilmTitle());
            foundFilm.setFilmPrice(film.getFilmPrice());
            // todo думай можно ли сеты присваивать тут
            return repository.save(foundFilm);
        }).orElseGet(() -> {
            film.setId(id);
            return repository.save(film);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "films/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "films/remove/all");
        return crudUtil.deleteAllEntities();
    }

}
