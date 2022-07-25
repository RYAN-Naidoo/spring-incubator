package entelect.training.incubator.spring.Booking;

import entelect.training.incubator.spring.flight.FlightServiceApplication;
import entelect.training.incubator.spring.customer.CustomerServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
    }
}
