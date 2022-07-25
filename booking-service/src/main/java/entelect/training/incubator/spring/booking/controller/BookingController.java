package entelect.training.incubator.spring.booking.controller;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingCreationDTO;
import entelect.training.incubator.spring.booking.model.CustomerInfoDTO;
import entelect.training.incubator.spring.booking.model.ReferenceNumberInfoDTO;
import entelect.training.incubator.spring.booking.service.BookingService;
import entelect.training.incubator.spring.booking.service.RestService;
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
    private  final RestService restService;

    //CONSTRUCTOR INJECTION OF SERVICE HERE
    public BookingController(BookingService bookingService, RestService restService) {

        this.bookingService = bookingService;
        this.restService = restService;
    }

    @PostMapping()
    public ResponseEntity<?> createBooking(@RequestBody BookingCreationDTO bookingCreationDTO) {
        LOGGER.info("Processing booking request for booking={}", bookingCreationDTO);

        final Booking savedBooking = bookingService.createBooking(bookingCreationDTO);

        if (savedBooking != null){
            LOGGER.trace("Booking created as {}", savedBooking);
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        }

        LOGGER.info("Booking could not be created, either flight or customer do not exist");
        return ResponseEntity.notFound().build();
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

    @GetMapping("/GetOther")
    public ResponseEntity<?> testFunction(){

        //Test flight check
        LOGGER.info(restService.validateCustomer(1));

        // Test customer check
        LOGGER.info(restService.validateFlight(2));

        return new ResponseEntity<>("Testing Completed", HttpStatus.CREATED);
    }


    //REQUIRED FUNCTIONS
/*
    POST    : MAKE BOOKING
    GET     : GET BOOKING BY ID
    POST    : GET ALL BOOKINGS PER CUSTOMER
    POST    : GET BOOKING BY REFERENCE NUMBER
*/

}