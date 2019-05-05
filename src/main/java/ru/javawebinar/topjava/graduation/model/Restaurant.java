package ru.javawebinar.topjava.graduation.model;

public class Restaurant extends AbstractNamedEntity {
    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName());
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
