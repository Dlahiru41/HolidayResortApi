package resourceClasses;

import DAOs.PaymentDAO;
import entityClasses.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/payments")
public class PaymentResource {

    private PaymentDAO paymentDAO = new PaymentDAO();

    // Create a new payment
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPayment(Payment payment) {
        return paymentDAO.createPayment(payment);
    }

    // Get a payment by ID
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPaymentById(@PathParam("id") int id) {
        return paymentDAO.getPaymentById(id);
    }

    // Update an existing payment
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePayment(@PathParam("id") int id, Payment payment) {
        return paymentDAO.updatePayment(id, payment);
    }

    // Delete an existing payment
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePayment(@PathParam("id") int id) {
        return paymentDAO.deletePayment(id);
    }

    // Get all payments (optional)
    @GET
    @Produces("application/json")
    public Response getAllPayments() {
        List<Payment> payments = paymentDAO.getAllPayments();
        if (payments.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No payments found").build();
        }
        return Response.status(Response.Status.OK).entity(payments).build();
    }
}

