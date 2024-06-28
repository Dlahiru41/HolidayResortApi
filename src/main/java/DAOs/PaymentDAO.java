package DAOs;

import entityClasses.Payment;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDAO {

    // Data store for payments
    private static final Map<Integer, Payment> payments = new HashMap<>();
    private static int nextPaymentID = 1;

    // Create a new payment
    public Response createPayment(Payment payment) {
        if (payment == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid payment data").build();
        }
        payment.setId(nextPaymentID++); // Use setter assuming it exists
        payments.put(payment.getId(), payment);
        return Response.status(Response.Status.CREATED).entity(payment).build();
    }

    // Read a payment by ID
    public Response getPaymentById(int id) {
        Payment payment = payments.get(id);
        if (payment == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Payment not found").build();
        }
        return Response.status(Response.Status.OK).entity(payment).build();
    }

    // Update an existing payment
    public Response updatePayment(int id, Payment updatedPayment) {
        Payment existingPayment = payments.get(id);
        if (existingPayment == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Payment not found").build();
        }
        // Validate and update payment data
        existingPayment.setBookingId(updatedPayment.getBookingId());
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setStatus(updatedPayment.getStatus());
        // Update other fields as necessary
        return Response.status(Response.Status.OK).entity(existingPayment).build();
    }

    // Delete an existing payment
    public Response deletePayment(int id) {
        if (!payments.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Payment not found").build();
        }
        payments.remove(id);
        return Response.status(Response.Status.OK).entity("Payment deleted successfully").build();
    }

    // Get all payments (optional)
    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments.values());
    }
}
