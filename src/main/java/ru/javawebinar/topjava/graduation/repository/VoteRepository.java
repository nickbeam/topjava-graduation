package ru.javawebinar.topjava.graduation.repository;

import ru.javawebinar.topjava.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote);

    boolean delete(int id);

    Vote get(int id);

    List<Vote> getAll();

    List<Vote> getByDate(LocalDate date);

    List<Vote> getByRestaurant(int id);

    List<Vote> getByUser(int id);
}
