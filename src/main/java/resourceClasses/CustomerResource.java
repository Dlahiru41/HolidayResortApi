package resourceClasses;

import DAOs.CustomerDAO;
import entityClasses.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
public class CustomerResource {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Create a new customer
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        return customerDAO.createCustomer(customer);
    }

    // Get a customer by ID
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCustomerById(@PathParam("id") int id) {
        return customerDAO.getCustomerById(id);
    }

    // Update an existing customer
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        return customerDAO.updateCustomer(id, customer);
    }

    // Delete an existing customer
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") int id) {
        return customerDAO.deleteCustomer(id);
    }

    // Get all customers (optional)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No customers found").build();
        }
        return Response.status(Response.Status.OK).entity(customers).build();
    }
}

