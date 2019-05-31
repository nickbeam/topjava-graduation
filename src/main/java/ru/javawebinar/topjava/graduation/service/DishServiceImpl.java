package ru.javawebinar.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.graduation.model.Dish;
import ru.javawebinar.topjava.graduation.repository.DishRepository;
import ru.javawebinar.topjava.graduation.repository.RestaurantRepository;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;
import java.time.LocalDate;
import java.util.List;
import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("dishService")
public class DishServiceImpl implements DishService {
    private final DishRepository repository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishServiceImpl(DishRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElse(null));
        return repository.save(dish);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId) != 0, id);
    }

    @Override
    public Dish get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null), id);
    }

    @Override
    public void update(Dish dish, int restaurantId) throws NotFoundException {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.findById(dish.getId()), dish.getId());
        create(dish, restaurantId);
    }

    @Override
    public List<Dish> getAll(int restaurantId, LocalDate date) {
        return repository.getByRestaurantAndDate(restaurantId, date);
    }
}
