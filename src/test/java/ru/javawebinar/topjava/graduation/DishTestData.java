package ru.javawebinar.topjava.graduation;

import org.assertj.core.api.Assertions;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjava.graduation.model.Dish;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.graduation.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final int DISH_1_ID = START_SEQ + 5;

    public static final Dish NEW_DISH = new Dish(null, LocalDate.now(), "New Dish", 555);
    public static final Dish DISH_1 = new Dish(100005, LocalDate.now(), "Fish", 350);
    public static final Dish DISH_2 = new Dish(100006, LocalDate.now(), "BBQ", 450);
    public static final Dish DISH_3 = new Dish(100007, LocalDate.now(), "Roast beef", 200);
    public static final Dish DISH_4 = new Dish(100008, LocalDate.now(), "Steak", 500);
    public static final Dish DISH_5 = new Dish(100009, LocalDate.now(), "Stew", 300);
    public static final Dish DISH_6 = new Dish(100010, LocalDate.now(), "Apple Pie", 50);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Dish... expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, Dish.class), List.of(expected));
    }

    public static ResultMatcher contentJson(Dish expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, Dish.class), expected);
    }
}
