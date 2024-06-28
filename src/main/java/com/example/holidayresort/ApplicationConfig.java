package com.example.holidayresort;

import resourceClasses.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")  // Defines the base path for the application
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        // Register resource classes
        classes.add(CustomerResource.class);
        classes.add(HotelResource.class);
        classes.add(RoomResource.class);
        classes.add(BookingResource.class);
        classes.add(PaymentResource.class);

        // You can add more resource classes, providers, etc., as needed

        return classes;
    }
}
