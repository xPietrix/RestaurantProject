package edu.pwap.pp.models;

public class Dish
{
    private long id;
    private String dishName;
    private double dishPrice;
    private long estimatedPreparationTime;
    private long dishCategoryId;
    private DishCategory dishCategory;

    public Dish(String dishName, double dishPrice, long estimatedPreparationTime, DishCategory dishCategory)
    {
        this.id = 0;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.estimatedPreparationTime = estimatedPreparationTime;
        this.dishCategory = dishCategory;
    }

    public Dish(long id, String dishName, double dishPrice, long estimatedPreparationTime, DishCategory dishCategory)
    {
        this.id = id;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.estimatedPreparationTime = estimatedPreparationTime;
        this.dishCategory = dishCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public long getEstimatedPreparationTime() {
        return estimatedPreparationTime;
    }

    public void setEstimatedPreparationTime(long estimatedPreparationTime) {
        this.estimatedPreparationTime = estimatedPreparationTime;
    }

    public DishCategory getDishCategory() {return dishCategory;}

    public long getDishCategoryId() {
        return dishCategoryId;
    }

    public void setDishCategory(long dishCategoryId) {
        this.dishCategoryId = dishCategoryId;
    }
}
