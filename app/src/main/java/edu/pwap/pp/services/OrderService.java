package edu.pwap.pp.services;

import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Order;
import edu.pwap.pp.repositories.OrderRepository;
import io.reactivex.Single;

public class OrderService
{
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order, OrderApi api)
    {
        this.orderRepository.addOrder(order, api);
    }

    public void deliverOrder(long orderId, OrderApi api)
    {
        this.orderRepository.deliverOrder(orderId, api);
    }

    public Single<Order> prepareOrder(long orderId, OrderApi api)
    {
        return this.orderRepository.prepareOrder(orderId, api);
    }
}
