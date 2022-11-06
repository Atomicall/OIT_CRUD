package by.oit_videorental.Repository;

import by.oit_videorental.Model.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
}
