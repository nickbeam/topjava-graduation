package ru.javawebinar.topjava.graduation;

import org.assertj.core.api.Assertions;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjava.graduation.model.Restaurant;
import ru.javawebinar.topjava.graduation.web.json.JsonUtil;

import java.util.List;

import static ru.javawebinar.topjava.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_1_ID = START_SEQ + 2;
    public static final int RESTAURANT_2_ID = START_SEQ + 3;
    public static final int RESTAURANT_3_ID = START_SEQ + 4;

    public static final Restaurant NEW_RESTAURANT = new Restaurant(null, "New Restaurant");
    public static final Restaurant RESTAURANT_1 = new Restaurant(100002, "Spirit");
    public static final Restaurant RESTAURANT_2 = new Restaurant(100003, "Seventh sky");
    public static final Restaurant RESTAURANT_3 = new Restaurant(100004, "Five corners");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, Restaurant.class), List.of(expected));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, Restaurant.class), expected);
    }
}
