package org.muki.pets.web.api;


import org.joda.time.DateTime;
import java.io.Serializable;

public class GetPetWebResponse implements Serializable{

    private String id;
    private String name;
    private String category;
    private Double price;
    private String description;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
