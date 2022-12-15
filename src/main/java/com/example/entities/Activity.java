package com.example.entities;

public class Activity {

    private Long id;
    private String name;
    private Category category;
    private int duration;

    public Activity() {
    }

    public Activity(ActivityBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.category = builder.category;
        this.duration = builder.duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static class ActivityBuilder {
        private Long id;
        private String name;
        private Category category;
        private int duration;

        public ActivityBuilder() {
        }

        public ActivityBuilder(Long id) {
            this.id = id;
        }

        public ActivityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ActivityBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ActivityBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public ActivityBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }
}
