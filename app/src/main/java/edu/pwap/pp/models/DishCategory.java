package edu.pwap.pp.models;

import java.io.Serializable;

public class DishCategory implements Serializable
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

    public void setId(long id)
    {
        this.id = id;
    }

    public DishCategory(String categoryName)
    {
        this.id = 0;
        this.categoryName = categoryName;
    }

    public DishCategory(long id, String categoryName)
    {
        this.id = id;
        this.categoryName = categoryName;
    }
}
