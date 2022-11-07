package by.oit_videorental.Repository;

import by.oit_videorental.Model.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Long> {
}
