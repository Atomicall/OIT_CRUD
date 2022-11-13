package by.oit_videorental.repository;

import by.oit_videorental.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findCategoryByCategoryTitle(String categoryTitle);
    Category findCategoryByAgeConstraint_Id(long ageConstraint_id);
}
