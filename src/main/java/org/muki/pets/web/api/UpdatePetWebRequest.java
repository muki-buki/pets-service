package org.muki.pets.web.api;

import org.hibernate.validator.constraints.SafeHtml;


public class UpdatePetWebRequest {

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String name;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String category;
    private Double price;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String imageUrl;
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    private String description;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
