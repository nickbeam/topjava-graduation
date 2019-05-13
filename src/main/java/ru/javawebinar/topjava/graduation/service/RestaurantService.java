package ru.javawebinar.topjava.graduation.service;

import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    void update(Restaurant restaurant) throws NotFoundException;
    
    List<Restaurant> getAll();
}
