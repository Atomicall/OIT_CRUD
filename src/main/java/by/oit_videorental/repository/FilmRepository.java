package by.oit_videorental.repository;

import by.oit_videorental.model.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
}
