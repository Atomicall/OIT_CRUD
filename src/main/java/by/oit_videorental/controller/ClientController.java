package by.oit_videorental.controller;

import by.oit_videorental.controller.util.GenericCrudUtil;
import by.oit_videorental.model.Client;
import by.oit_videorental.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClientRepository repository;
    private GenericCrudUtil<Client, ClientRepository> crudUtil;

    @PostConstruct
    private void postConstruct() {
        this.crudUtil = new GenericCrudUtil<>(repository);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Client>> getAllClients() {
        logger.info("GET" + " clients");
        return crudUtil.getAllEntities();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        logger.info("GET " + "clients/{}", id);
        return crudUtil.getEntityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        logger.info("POST " + "clients" + " Body:\n {}", client);
        return crudUtil.addEntity(client);
    }

    @PostMapping("/add/batch")
    public ResponseEntity<List<Client>> addAuthorBatch(@RequestBody(required = true) List<Client> clients) {
        logger.info("POST " + "clients/batch" + " Body:\n {}", clients);
        return crudUtil.addEntitiesBatch(clients);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Client> replaceCategory(@RequestBody Client client, @PathVariable("id") Long id) {
        logger.info("PUT " + "clients/{}" + " Body:\n {}", id, client);
        return new ResponseEntity<>(repository.findById(id).map(foundClient -> {
            foundClient.setFirstName(client.getFirstName());
            foundClient.setLastName(client.getLastName());
            foundClient.setPassportNumber(client.getPassportNumber());
            return repository.save(foundClient);
        }).orElseGet(() -> {
            client.setId(id);
            return repository.save(client);
        }), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        logger.info("DELETE " + "clients/remove/{}", id);
        return crudUtil.deleteEntityById(id);
    }

    @DeleteMapping("/remove/all")
    public ResponseEntity<?> deleteAll() {
        logger.info("DELETE " + "clients/remove/all");
        return crudUtil.deleteAllEntities();
    }
}
