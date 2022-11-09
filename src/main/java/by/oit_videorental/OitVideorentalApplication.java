package by.oit_videorental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// без exclude ошибка
//class org.springframework.orm.jpa.EntityManagerHolder cannot be cast to class org.springframework.orm.hibernate5.SessionHolder (org.springframework.orm.jpa.EntityManagerHolder and org.springframework.orm.hibernate5.SessionHolder are in unnamed module of loader 'app')
@SpringBootApplication (exclude = HibernateJpaAutoConfiguration.class)
//@SpringBootApplication
public class OitVideorentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(OitVideorentalApplication.class, args);
    }

}
