package ru.javawebinar.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.repository.RestaurantRepository;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNotFoundWithId;


@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    //@CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    //@CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    //@CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void update(Restaurant restaurant) throws NotFoundException {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    //@Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant getRestaurantWithDishes(int id, LocalDate date) throws NotFoundException {
        return repository.getRestaurantWithDishes(id, date);
    }

    //@Cacheable("restaurants")
    @Override
    public List<Restaurant> getAllRestaurantWithDishes(LocalDate date) {
        return repository.getAllRestaurantWithDishes(date);
    }

    @Override
    public Restaurant getRestaurantWithVotes(int id) throws NotFoundException {
        return repository.getRestaurantWithVotes(id);
    }
}
