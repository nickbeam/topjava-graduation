package ru.javawebinar.topjava.graduation.repository;

import ru.javawebinar.topjava.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    default Restaurant getWithDishes(int id) {
        throw new UnsupportedOperationException();
    }

    default Restaurant getWithVotes(int id) {
        throw new UnsupportedOperationException();
    }
}
