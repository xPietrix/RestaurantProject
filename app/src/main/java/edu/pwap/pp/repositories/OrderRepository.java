package edu.pwap.pp.repositories;

import edu.pwap.pp.dataGetters.ConnectionInitializer;
import edu.pwap.pp.models.Order;

public class OrderRepository
{
    private ConnectionInitializer connectionInitializer;

    public OrderRepository()
    {
        this.connectionInitializer = new ConnectionInitializer();
    }

    public void addOrder(Order order)
    {
        //TODO
    }

    public void addOrderToPrepare(String id)
    {
        //TODO
    }

    public void addOrderToDeliver(String id)
    {
        //TODO
    }

    public void getOrdersToPrepare()
    {
        //TODO
    }

    public void getOrdersToDeliver()
    {
        //TODO
    }
}
