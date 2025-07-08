package com.example.assiandelifruitmarket;

public class FruitItem {
    private int imageResource;
    private String name;
    private String description;
    private boolean isFavorite;

    public FruitItem(int imageResource, String description, String name) {
        this.imageResource = imageResource;
        this.description = description;
        this.name = name;
        this.isFavorite = false;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
