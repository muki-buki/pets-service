package org.muki.pets.model;

import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

public class Pet {

    public Pet() {

    }

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String category;


    private DateTime updated;

    private Double price;

    private String description;

    public Pet(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        final int prime = 31;
        if (id != null) {
            hash = hash * prime + id.hashCode();
        }
        if (name != null) {
            hash = hash * prime + name.hashCode();
        }
        if (category != null) {
            hash = hash * prime + category.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        Pet that = (Pet) obj;
        return this.hashCode() == that.hashCode();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
