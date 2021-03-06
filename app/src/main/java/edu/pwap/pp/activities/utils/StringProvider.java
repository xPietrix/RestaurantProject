package edu.pwap.pp.activities.utils;

import java.util.List;

import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.models.Order;

public class StringProvider
{
    public static String allDishCategoriesString(List<DishCategory> categories)
    {
        String content = "";

        content = content + "================================" + "\n" + "\n";
        for(DishCategory dishCategory: categories)
        {
            content = content + "ID: " + dishCategory.getId() + "\n";
            content = content + "Category name: " + dishCategory.getCategoryName() + "\n";
            content = content + "\n" + "================================" + "\n" + "\n";
        }

        return content;
    }

    public static String dishesWithCategoryString(List<Dish> dishes)
    {
        String content = "";

        content = content + "\n" + "================================" + "\n" + "\n";
        for(Dish dish: dishes)
        {
            content = content + "ID: " + dish.getId() + "\n";
            content = content + "Dish name: " + dish.getDishName() + "\n";
            content = content + "Dish price: " + dish.getDishPrice() + "\n";
            content = content + "Estimated preparation time: " + dish.getEstimatedPreparationTime() + "\n";
            content = content + "Dish category id: " + dish.getDishCategory().getId() + "\n";
            content = content + "Dish category name: " + dish.getDishCategory().getCategoryName() + "\n";
            content = content + "\n" + "================================" + "\n" + "\n";
        }
        content = content + "\n";
        return content;
    }

    public static String getAllOrdersToPrepareOrDeliverString(List<Order> ordersToDeliver)
    {
        String content = "";

        content = content + "\n" + "================================" + "\n" + "\n";
        for(Order order: ordersToDeliver)
        {
            content = content + "\n";
            content = content + "Order id: " + order.getId() + "\n";
            List<Dish> dishes = order.getDishes();
            for(Dish dish: dishes)
            {
                content = content + dish.getDishName() + "\n";
            }
            content = content + "Total price: " + order.getTotalPrice() + "zł" + "\n";
            content = content + "Table number: " + order.getTableId() + "\n";
            content = content + "Order date: " + order.getOrderDate() + "\n";
            content = content + "Estimated preparation time: " + order.getEstimatedPreparationTime() + "\n";
            content = content + "\n" + "================================" + "\n" + "\n";
        }

        return content;
    }

    public static String getDishString(Dish dish)
    {
        String content = "";

        content = content + "\n";
        content = content + "ID: " + dish.getId() + "\n";
        content = content + "Dish name: " + dish.getDishName() + "\n";
        content = content + "Dish price: " + dish.getDishPrice() + "\n";
        content = content + "Estimated preparation time: " + dish.getEstimatedPreparationTime() + "\n";
        content = content + "Dish category name: " + dish.getDishCategory().getCategoryName() + "\n";
        content = content + "\n";

        return content;
    }

}
