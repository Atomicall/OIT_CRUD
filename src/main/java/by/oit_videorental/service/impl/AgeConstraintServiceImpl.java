package by.oit_videorental.service.impl;

import by.oit_videorental.model.AgeConstraint;
import by.oit_videorental.repository.AgeConstraintRepository;
import by.oit_videorental.service.AgeConstraintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgeConstraintServiceImpl implements AgeConstraintService {

    @Autowired
    private AgeConstraintRepository repository;

    @Override
    public void addConstraint(AgeConstraint constraint) {
        repository.save(constraint);
    }

    @Override
    public void deleteConstraint(long id) {

        repository.deleteById(id);
    }

    @Override
    public List<AgeConstraint> getByAge(int age) {

        return repository.findByAllowedAge(age);
    }

    @Override
    public List<AgeConstraint> getByTitle(String title) {

        return repository.findByConstraintTitle(title);
    }

    @Override
    public void updateConstraint(AgeConstraint constraint) {
        AgeConstraint constraintFromDb = repository.findById(constraint.getId()).orElse(new AgeConstraint());
        constraintFromDb.setConstraintTitle(constraint.getConstraintTitle());
        constraintFromDb.setAllowedAge(constraint.getAllowedAge());
        repository.save(constraintFromDb);
    }

    @Override
    public List<AgeConstraint> getAll() {
        return (List<AgeConstraint>) repository.findAll();
    }
}
