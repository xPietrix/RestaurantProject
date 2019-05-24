package edu.pwap.pp.activities.utils;

import java.util.ArrayList;
import java.util.List;

import edu.pwap.pp.models.Dish;

public class ClientOrder
{
    private static List<Dish> orderedDishes = new ArrayList<>();

    public static void addOrderToList(Dish dish)
    {
        orderedDishes.add(dish);
    }

    public static List<Dish> getOrderedDishes()
    {
        return orderedDishes;
    }
}
