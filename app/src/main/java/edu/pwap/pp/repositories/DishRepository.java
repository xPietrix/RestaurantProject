package edu.pwap.pp.repositories;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.models.Dish;

public class DishRepository
{
    private ConnectionInitializer connectionInitializer;

    public DishRepository()
    {
        this.connectionInitializer = new ConnectionInitializer();
    }

    public void getDishWithCategory(String id)
    {
        //TODO
    }

    public void getDishWithId(String id)
    {
        //TODO
    }

    public void addDish(Dish dish)
    {
        //TODO
    }
}
