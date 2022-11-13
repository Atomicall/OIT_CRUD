package by.oit_videorental.repository;

import by.oit_videorental.model.AgeConstraint;
import by.oit_videorental.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgeConstraintRepository extends CrudRepository<AgeConstraint, Long> {
    List<AgeConstraint> findAgeConstraintsByAllowedAge(int age);
    AgeConstraint findAgeConstraintsByConstraintTitle(String title);
    AgeConstraint findAgeConstraintByCategoriesId(long categories_id);
}
