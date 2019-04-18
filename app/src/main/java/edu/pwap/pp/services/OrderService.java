package edu.pwap.pp.services;

import edu.pwap.pp.models.Order;
import edu.pwap.pp.repositories.OrderRepository;

public class OrderService
{
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order)
    {
        this.orderRepository.addOrder(order);
    }

    public void addOrderToPrepare(String id)
    {
        this.orderRepository.addOrderToPrepare(id);
    }

    public void addOrderToDeliver(String id)
    {
        this.orderRepository.addOrderToDeliver(id);
    }

    public void getOrdersToPrepare()
    {
        this.orderRepository.getOrdersToPrepare();
    }

    public void getOrdersToDeliver()
    {
        this.orderRepository.getOrdersToDeliver();
    }
}
