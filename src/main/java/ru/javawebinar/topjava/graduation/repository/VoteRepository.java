package ru.javawebinar.topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    List<Vote> findByUserId(int userId);

    Vote findByUserIdAndDate(int userId, LocalDate date);

    List<Vote> findByRestaurantId(int restaurantId);

    Vote findByRestaurantIdAndDate(int restaurantId, LocalDate date);
}
