package ru.javawebinar.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.graduation.model.Dish;
import ru.javawebinar.topjava.graduation.repository.DishRepository;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public class DishServiceImpl implements DishService {
    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        return repository.save(dish);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {

    }

    @Override
    public Dish get(int id, int restaurantId) throws NotFoundException {
        return null;
    }

    @Override
    public void update(Dish dish, int restaurantId) throws NotFoundException {

    }

    @Override
    public List<Dish> getAll(int restaurantId, LocalDate date) {
        return null;
    }
}
