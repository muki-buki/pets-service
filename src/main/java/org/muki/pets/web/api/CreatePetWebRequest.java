package org.muki.pets.web.api;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotNull;

public class CreatePetWebRequest {

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @NotNull
    private String name;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @NotNull
    private String category;
    @NotNull
    private double price;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String imageUrl;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String description;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
