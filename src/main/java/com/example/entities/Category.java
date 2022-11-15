package com.example.entities;

public class Category {

    private Long id;
    private String name;

    public Category() {
    }

    public Category(CategoryBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
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

    public static class CategoryBuilder {

        private Long id;
        private String name;

        public CategoryBuilder() {
        }

        public CategoryBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
