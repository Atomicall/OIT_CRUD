package by.oit_videorental.Service;

import by.oit_videorental.Model.AgeConstraint;

import java.util.List;

public interface AgeConstraintService {
    void addConstraint(AgeConstraint constraint);

    void deleteConstraint(long id);

    List<AgeConstraint> getByAge(int age);

    List<AgeConstraint> getByTitle(String title);

    void updateConstraint(AgeConstraint constraint);

    List<AgeConstraint> getAll();
}
