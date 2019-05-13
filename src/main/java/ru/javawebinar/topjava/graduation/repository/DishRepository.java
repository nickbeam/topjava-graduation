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

    @Override
    List<Dish> findAll(Sort sort);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT d from Dish d WHERE d.restaurant.id=:restaurantId AND d.date BETWEEN :date AND :date ORDER BY d.date DESC")
    List<Dish> getByDate(LocalDate date, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=?1 ORDER BY d.date DESC")
    List<Dish> getByRestaurant(int id);
}
