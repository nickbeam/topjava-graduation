package ru.javawebinar.topjava.graduation.service;

import ru.javawebinar.topjava.graduation.model.Dish;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface DishService {
    Dish create(Dish dish, int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    void update(Dish dish, int restaurantId) throws NotFoundException;

    Dish get(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId, LocalDate date);
}
