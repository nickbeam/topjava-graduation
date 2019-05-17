package ru.javawebinar.topjava.graduation.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.service.RestaurantService;
import ru.javawebinar.topjava.graduation.web.user.AdminRestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_URL = "/rest/restaurants";

    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id, @RequestParam(value = "withDishes", required = false, defaultValue = "true") boolean withDishes,
                          @RequestParam(value = "withVotes", required = false, defaultValue = "true") boolean withVotes) {
        if (withDishes) {
            return service.getRestaurantWithDishes(id, LocalDate.now());
        } else if (withVotes) {
            return service.getRestaurantWithVotes(id);
        }
        return service.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll(@RequestParam(value = "withDishes", required = false, defaultValue = "true") boolean withDishes) {
        if (withDishes) {
            return service.getAllRestaurantWithDishes(LocalDate.now());
        }
        return service.getAll();
    }
}
