package ru.javawebinar.topjava.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;

import static ru.javawebinar.topjava.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantAdminRestController.class);

    public static final String REST_URL = "/rest/admin/restaurants";

    private final RestaurantService service;

    @Autowired
    public RestaurantAdminRestController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        LOG.info("Create new restaurant {}", restaurant);
        checkNew(restaurant);
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        LOG.info("Delete restaurant with id {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        LOG.info("Update restaurant {} with id {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }
}
