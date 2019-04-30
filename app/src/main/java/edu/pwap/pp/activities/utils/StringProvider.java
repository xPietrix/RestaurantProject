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

        for(DishCategory dishCategory: categories)
        {
            content = content + "ID: " + dishCategory.getId() + "\n";
            content = content + "Category name: " + dishCategory.getCategoryName() + "\n";
            content = content + "\n";
        }

        return content;
    }

    public static String dishesWithCategoryString(List<Dish> dishes)
    {
        String content = "";

        content = content + "\n";
        for(Dish dish: dishes)
        {
            content = content + "ID: " + dish.getId() + "\n";
            content = content + "Dish name: " + dish.getDishName() + "\n";
            content = content + "Dish price: " + dish.getDishPrice() + "\n";
            content = content + "Estimated preparation time: " + dish.getEstimatedPreparationTime() + "\n";
            content = content + "Dish category id: " + dish.getDishCategory().getId() + "\n";
            content = content + "Dish category name: " + dish.getDishCategory().getCategoryName() + "\n";
            content = content + "\n";
        }

        return content;
    }

    public static String getAllOrdersToPrepareOrDeliverString(List<Order> ordersToDeliver)
    {
        String content = "";

        content = content + "\n";
        for(Order order: ordersToDeliver)
        {
            content = content + "Order id: " + order.getId() + "\n";
            content = content + "Order status: " + order.getOrderStatus() + "\n";
            content = content + "TotalPrice: " + order.getTotalPrice() + "\n";
            content = content + "TableId: " + order.getTableId() + "\n";
            content = content + "OrderDate: " + order.getOrderDate() + "\n";
            content = content + "Estimated Preparation Time: " + order.getEstimatedPreparationTime() + "\n";

            List<Dish> dishes = order.getDishes();
            for(Dish dish: dishes)
            {
                content = content + dish.getDishCategoryId() + "\n";
                content = content + dish.getDishName() + "\n";
                content = content + dish.getDishPrice() + "\n";
                content = content + dish.getEstimatedPreparationTime()+ "\n";
                content = content + "/n";
            }
            content = content + "/n";
        }

        return content;
    }
}
