package ru.javawebinar.topjava.graduation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Restaurant> findById(Integer id);

    @Override
    List<Restaurant> findAll(Sort sort);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.dishes d WHERE r.id=:id AND d.date=:date")
    Restaurant getRestaurantWithDishes(@Param("id") int id, @Param("date") LocalDate date);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.dishes d WHERE d.date=:date ORDER BY r.name")
    List<Restaurant> getAllRestaurantWithDishes(@Param("date") LocalDate date);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant getRestaurantWithVotes(@Param("id") int id);
}
