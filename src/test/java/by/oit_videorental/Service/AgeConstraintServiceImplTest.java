package by.oit_videorental.Service;

import by.oit_videorental.Model.AgeConstraint;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AgeConstraintServiceImplTest {

@Autowired
private AgeConstraintService service;

    @Test
    void addConstraint() {
        AgeConstraint constraint = new AgeConstraint();
        constraint.setConstraintTitle("G");
        constraint.setAllowedAge(0);
        service.addConstraint(constraint);
    }
}