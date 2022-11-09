package by.oit_videorental.repository;

import by.oit_videorental.model.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Long> {
}
