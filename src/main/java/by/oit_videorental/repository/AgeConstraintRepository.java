package by.oit_videorental.repository;

import by.oit_videorental.model.AgeConstraint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AgeConstraintRepository extends CrudRepository<AgeConstraint, Long> {
    List<AgeConstraint> findByAllowedAge(int age);
    List<AgeConstraint> findByConstraintTitle(String title);
}
