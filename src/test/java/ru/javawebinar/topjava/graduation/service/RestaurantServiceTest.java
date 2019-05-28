package ru.javawebinar.topjava.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.topjava.graduation.RestaurantTestData.*;

@SpringJUnitConfig(locations = "classpath:spring/spring-app-test.xml")
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class RestaurantServiceTest {
    @Autowired
    private RestaurantService service;

    @Test
    void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_1_ID);
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void create() {
        service.create(NEW_RESTAURANT);
        assertMatch(service.getAll(), RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, NEW_RESTAURANT);
    }

    @Test
    void delete() throws Exception {
        service.delete(RESTAURANT_1_ID);
        assertMatch(service.getAll(), RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void update() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_1_ID);
        restaurant.setName("New Spirit");
        service.update(restaurant);
        assertMatch(service.get(RESTAURANT_1_ID), restaurant);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void updateWrongRestaurant() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.update(new Restaurant()));
    }
}