package by.oit_videorental.service;

import by.oit_videorental.model.AgeConstraint;

import java.util.List;

public interface AgeConstraintService {
    void addConstraint(AgeConstraint constraint);

    void deleteConstraint(long id);

    List<AgeConstraint> getByAge(int age);

    List<AgeConstraint> getByTitle(String title);

    void updateConstraint(AgeConstraint constraint);

    List<AgeConstraint> getAll();
}
