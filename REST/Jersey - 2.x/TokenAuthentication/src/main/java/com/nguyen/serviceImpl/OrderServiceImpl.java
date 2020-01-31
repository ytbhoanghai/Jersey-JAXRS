package com.nguyen.serviceImpl;

import com.nguyen.model.Order;
import com.nguyen.service.OrderService;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/order")
public class OrderServiceImpl implements OrderService {

    @Override
    public Response findById(Integer id) {
        String message = String.format("find ORDER by id: %d", id);
        System.out.println(message);
        return Response.ok(message).build();
    }

    @Override
    public Response findAll() {
        String message = String.format("findAll ORDER");
        System.out.println(message);
        return Response.ok(message).build();
    }

    @Override
    public Response update(Order order) {
        String message = String.format("update ORDER: %s", order);
        System.out.println(message);
        return Response.ok(message).build();
    }

    @Override
    public Response deleteById(Integer id) {
        String message = String.format("delete ORDER by id: %d", id);
        System.out.println(message);
        return Response.ok(message).build();
    }

    @Override
    public Response insert(Order order) {
        String message = String.format("insert ORDER: %s", order);
        System.out.println(message);
        return Response.ok(message).build();
    }
}
