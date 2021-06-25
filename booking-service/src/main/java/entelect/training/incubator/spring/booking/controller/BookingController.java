package entelect.training.incubator.spring.booking.controller;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingCreationDTO;
import entelect.training.incubator.spring.booking.model.CustomerInfoDTO;
import entelect.training.incubator.spring.booking.model.ReferenceNumberInfoDTO;
import entelect.training.incubator.spring.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingController {

    private final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    //INJECT INSTANCE OF SERVICE HERE
    private final BookingService bookingService;

    //CONSTRUCTOR INJECTION OF SERVICE HERE
    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<?> createBooking(@RequestBody BookingCreationDTO bookingCreationDTO) {
        LOGGER.info("Processing booking request for booking={}", bookingCreationDTO);

        final Booking savedBooking = bookingService.createBooking(bookingCreationDTO);

        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllBookings(){
        List<Booking> outputList = bookingService.getAllBookings();

        if (!outputList.isEmpty()){
            LOGGER.trace("Bookings Found");
            return new ResponseEntity<>(outputList, HttpStatus.CREATED);
        }

        LOGGER.info("No bookings found");
        return ResponseEntity.notFound().build();
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
        Booking booking = bookingService.getBookingByID(id);

        if (booking != null){
            LOGGER.trace("Booking found");
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        }

        LOGGER.info("No bookings found matching id: {}",id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/searchCustomer")
    public ResponseEntity<?> getBookingsByCustomer(@RequestBody CustomerInfoDTO customerInfoDTO) {
        List<Booking> outputList = bookingService.getBookingsByCustomer(customerInfoDTO.getCustomerId());

        if (!outputList.isEmpty()){
            LOGGER.trace("Bookings Found");
            return new ResponseEntity<>(outputList, HttpStatus.CREATED);
        }

        LOGGER.info("No bookings found");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/searchReference")
    public ResponseEntity<?> getBookingByReferenceNumber(@RequestBody ReferenceNumberInfoDTO referenceNumberInfoDTO) {
        List<Booking> outputList = bookingService.getBookingsByReferenceNumber(referenceNumberInfoDTO.getReferenceNumber());

        if (!outputList.isEmpty()){
            LOGGER.trace("Bookings Found");
            return new ResponseEntity<>(outputList, HttpStatus.CREATED);
        }

        LOGGER.info("No bookings found");
        return ResponseEntity.notFound().build();
    }

    //REQUIRED FUNCTIONS
/*
    POST    : MAKE BOOKING
    GET     : GET BOOKING BY ID
    POST    : GET ALL BOOKINGS PER CUSTOMER
    POST    : GET BOOKING BY REFERENCE NUMBER
*/

}