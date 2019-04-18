package edu.pwap.pp.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order
{
    private long id;
    private long tableId;
    private double totalPrice;
    private long estimatedPreparationTime;
    private String orderDate;
    private long orderStatus;
    private List<Dish> dishes;

    public Order(long orderStatus, double totalPrice, long tableId, String orderDate, long estimatedPreparationTime, List<Dish> dishes)
    {
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.tableId = tableId;
        this.orderDate = orderDate;
        this.estimatedPreparationTime = estimatedPreparationTime;
        this.dishes = dishes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getEstimatedPreparationTime() {
        return estimatedPreparationTime;
    }

    public void setEstimatedPreparationTime(long estimatedPreparationTime) {
        this.estimatedPreparationTime = estimatedPreparationTime;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
