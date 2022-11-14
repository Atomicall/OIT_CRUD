package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.PublishingCompany;
import by.oit_videorental.repository.PublishingCompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/publishing_companies")
public class PublishingCompanyController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PublishingCompanyRepository repository;

    private GenericCrudUtil<PublishingCompany, PublishingCompanyRepository> crudUtil;
    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }

    @GetMapping("/get")
    public ResponseEntity<List<PublishingCompany>> getAllPublishingCompanies(){
        logger.info("GET" + " publishing_companies");
        return crudUtil.getAllEntities();
    }

     @GetMapping("/get/{id}")
    public ResponseEntity<PublishingCompany> getPublishingCompanyById(@PathVariable("id") Long id) {
        logger.info("GET " + "publishing_companies/{}", id);
        return crudUtil.getEntityById(id);
    }

     @PostMapping("/add")
    public ResponseEntity<PublishingCompany> addPublishingCompany(@RequestBody PublishingCompany company) {
        logger.info("POST " + "publishing_companies" + " Body:\n {}", company);
        return crudUtil.addEntity(company);
    }

     @PostMapping("/add/batch")
    public ResponseEntity<List<PublishingCompany>> addPublishingCompaniesBatch(@RequestBody(required = true) List<PublishingCompany> publishingCompanies) {
        logger.info("POST " + "publishing_companies/batch" + " Body:\n {}", publishingCompanies);
        return crudUtil.addEntitiesBatch(publishingCompanies);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PublishingCompany> replacePublishingCompany(@RequestBody PublishingCompany company, @PathVariable("id") Long id) {
        logger.info("PUT " + "publishing_companies/{}" + " Body:\n {}", id, company);
        return new ResponseEntity<>(repository.findById(id).map(foundCompany -> {
            foundCompany.setCompanyTitle(company.getCompanyTitle());
            return repository.save(foundCompany);
        }).orElseGet(() -> {
            company.setId(id);
            return repository.save(company);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "publishing_companies/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "publishing_companies/remove/all");
        return crudUtil.deleteAllEntities();
    }
}
