package ru.javawebinar.topjava.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.graduation.model.Dish;
import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository {
    private static final Sort SORT_DATE_RESTAURANT_NAME = new Sort(Sort.Direction.ASC, "date", "restaurant", "name");

    @Autowired
    private CrudDishRepository crudRepository;

    @Override
    public Dish save(Dish dish) {
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return crudRepository.findAll(SORT_DATE_RESTAURANT_NAME);
    }

    @Override
    public List<Dish> getByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<Dish> getByRestaurant(int id) {
        return null;
    }
}
