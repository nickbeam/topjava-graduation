package ru.javawebinar.topjava.graduation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Override
    @Transactional
    Dish save(Dish dish);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Dish> findById(Integer id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:id AND d.date=:date ORDER BY d.name ASC")
    List<Dish> getByRestaurantAndDate(@Param("id") int id, @Param("date") LocalDate date);
}
