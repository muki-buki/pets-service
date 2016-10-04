package org.muki.pets.web;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotNull;

public class CreatePetWebRequest {

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @NotNull
    private String name = "";
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @NotNull
    private String category = "";
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

}
