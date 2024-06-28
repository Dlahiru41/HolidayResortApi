package DAOs;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import entityClasses.Customer;

public class CustomerDAO {

    // Data store for customers
    private static final Map<Integer, Customer> customers = new HashMap<>();
    private static int nextCustomerID = 1;

    // Create a new customer
    public Response createCustomer(Customer customer) {
        if (customer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid customer data").build();
        }
        customer.setId(nextCustomerID++); // Use setter assuming it exists
        customers.put(customer.getId(), customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    // Read a customer by ID
    public Response getCustomerById(int id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }
        return Response.status(Response.Status.OK).entity(customer).build();
    }

    // Update an existing customer
    public Response updateCustomer(int id, Customer updatedCustomer) {
        Customer existingCustomer = customers.get(id);
        if (existingCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }
        // Validate and update customer data
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        // Update other fields as necessary
        return Response.status(Response.Status.OK).entity(existingCustomer).build();
    }

    // Delete an existing customer
    public Response deleteCustomer(int id) {
        if (!customers.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        }
        customers.remove(id);
        return Response.status(Response.Status.OK).entity("Customer deleted successfully").build();
    }


    // Get all customers (optional)
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
}