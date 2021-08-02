package org.digitalthinking.resteasyjackson;


import org.digitalthinking.entites.Customer;
import org.digitalthinking.repositories.CustomerRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerApi {
 @Inject
 CustomerRepository cr;

    @GET
    public List<Customer> list() {
        return cr.listCustomer();
    }

    @GET
    @Path("/{Id}")
    public Customer getById(@QueryParam("Id") Long Id) {
        return cr.findCustomer(Id);
    }

    @POST
    public Response add(Customer c) {
        c.getProducts().forEach(p-> p.setCustomer(c));
        cr.createdCustomer(c);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{Id}")
    public Response delete(@QueryParam("Id") Long Id) {
        Customer customer = cr.findCustomer(Id);
        cr.deleteCustomer(customer);
        return Response.ok().build();
    }
    @PUT
    public Response update(Customer c) {
        Customer customer = cr.findCustomer(c.getId());
        customer.setCode(c.getCode());
        customer.setAccountNumber(c.getAccountNumber());
        customer.setSurname(c.getSurname());
        customer.setPhone(c.getPhone());
        customer.setAddress(c.getAddress());
        customer.setProducts(c.getProducts());
        cr.updateCustomer(customer);
        return Response.ok().build();
    }


}
