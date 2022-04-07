package com.chathupererajay.nadyafashion.dto;

import lombok.Data;

/**
 *
 * @author CPere
 */
@Data
public class ProductDTO {

    private int id;
    private String name;
    private double price;
    private String description;
    private String imageName;
    private int categoryId;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, double price, String description, String imageName, int categoryId) {
        setId(id);
        setName(name);
        setPrice(price);
        setDescription(description);
        setImageName(imageName);
        setCategory(categoryId);
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setPrice(double price) {
        this.price = price;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public final void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }

    public int getCategory() {
        return categoryId;
    }

}
