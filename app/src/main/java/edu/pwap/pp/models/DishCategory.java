package edu.pwap.pp.models;

public class DishCategory
{
    private long id;
    private String categoryName;

    public Long getId()
    {
        return id;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public DishCategory(String categoryName)
    {
        this.id = 0;
        this.categoryName = categoryName;
    }
}
