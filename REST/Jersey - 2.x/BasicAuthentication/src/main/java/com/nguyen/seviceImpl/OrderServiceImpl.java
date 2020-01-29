package com.nguyen.seviceImpl;

import com.nguyen.model.Order;
import com.nguyen.service.OrderService;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/order")
public class OrderServiceImpl implements OrderService {
    @Override
    public Response get(Integer id) {
        String message = "OrderService#get(" + id + ")";
        System.out.println(message);
        return Response.ok(message, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response insert(Order order, SecurityContext context) {
        String message = String.format("OrderService#insert(%s, %s)", order, context.getUserPrincipal().getName());
        System.out.println(message);
        return Response.ok(message, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response update(Order order) {
        String message = "OrderService#update(" + order + ")";
        System.out.println(message);
        return Response.ok(message, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response delete(Integer id) {
        String message = "OrderService#delete(" + id + ")";
        System.out.println(message);
        return Response.ok(message, MediaType.APPLICATION_JSON).build();
    }
}
