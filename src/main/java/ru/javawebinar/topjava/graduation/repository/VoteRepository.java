package ru.javawebinar.topjava.graduation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.graduation.model.Dish;
import ru.javawebinar.topjava.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Vote> findById(Integer id);

    @Override
    List<Vote> findAll(Sort sort);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.date BETWEEN :date AND :date ORDER BY v.date DESC")
    List<Vote> getByDate(LocalDate date);

    @Query("SELECT v from Vote v WHERE v.restaurant.id=:id ORDER BY v.date DESC")
    List<Vote> getByRestaurant(int id);


    List<Vote> getByUser(int id);
}
