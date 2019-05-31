package ru.javawebinar.topjava.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javawebinar.topjava.graduation.model.Dish;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.topjava.graduation.DishTestData.*;
import static ru.javawebinar.topjava.graduation.RestaurantTestData.RESTAURANT_1_ID;

@SpringJUnitConfig(locations = "classpath:spring/spring-app-test.xml")
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class DishServiceTest {
    @Autowired
    private DishService service;

    @Test
    void get() throws Exception {
        Dish dish = service.get(DISH_1_ID, RESTAURANT_1_ID);
        assertMatch(dish, DISH_1);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(RESTAURANT_1_ID, LocalDate.now()), DISH_1, DISH_2);
    }

    @Test
    void create() {
        service.create(NEW_DISH, RESTAURANT_1_ID);
        assertMatch(service.getAll(RESTAURANT_1_ID, LocalDate.now()), DISH_1, DISH_2, NEW_DISH);
    }

    @Test
    void delete() throws Exception {
        service.delete(DISH_1_ID, RESTAURANT_1_ID);
        assertMatch(service.getAll(RESTAURANT_1_ID, LocalDate.now()), DISH_2);
    }

    @Test
    void update() throws Exception {
        Dish dish = service.get(DISH_1_ID, RESTAURANT_1_ID);
        dish.setName("New Dish");
        service.update(dish, RESTAURANT_1_ID);
        assertMatch(service.get(DISH_1_ID, RESTAURANT_1_ID), dish);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1, 1));
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1, RESTAURANT_1_ID));
    }

    @Test
    void getNotFoundRestaurant() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1,1));
    }

    @Test
    void updateWrongDish() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.update(new Dish(), RESTAURANT_1_ID));
    }
}