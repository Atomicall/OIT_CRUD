package by.oit_videorental.repository;

import by.oit_videorental.model.AgeConstraint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgeConstraintRepository extends CrudRepository<AgeConstraint, Long> {
    List<AgeConstraint> findByAllowedAge(int age);
    List<AgeConstraint> findByConstraintTitle(String title);
}
