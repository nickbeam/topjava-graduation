package ru.javawebinar.topjava.graduation.repository;

import ru.javawebinar.topjava.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {
    Dish save(Dish dish);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll();

    List<Dish> getByDate(LocalDate date);

    List<Dish> getByRestaurant(int id);
}
