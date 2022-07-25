package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingCreationDTO;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final RestService restService;

    public BookingService(BookingRepository bookingRepository, RestService restService) {
        this.bookingRepository = bookingRepository;
        this.restService = restService;
    }

    public Booking createBooking(BookingCreationDTO bookingCreationDTO){

        Integer customerId = bookingCreationDTO.getCustomerId();
        Integer flightId = bookingCreationDTO.getFlightId();

        if (!restService.validateCustomer(customerId).equals("null") && !restService.validateFlight(flightId).equals("null")){

            //CREATE BOOKING IF FLIGHT AND CUSTOMER EXIST
            Booking booking = new Booking();
            booking.setId(0);
            booking.setCustomerId(bookingCreationDTO.getCustomerId());
            booking.setFlightId(bookingCreationDTO.getFlightId());
            booking.setReferenceNumber(HelperFunctions.referenceGenerator());

            //SAVE TO DB
            bookingRepository.save(booking);
            return booking;
        }

        return null;
    }

    public List<Booking> getAllBookings(){

        Iterable<Booking> bookingIterable = bookingRepository.findAll();
        List<Booking> outputList = new ArrayList<>();
        bookingIterable.forEach(outputList::add);
        return outputList;
    }

    public Booking getBookingByID(Integer id){

        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.orElse(null);
    }

    public List<Booking> getBookingsByCustomer(Integer customerId){

        Optional<List<Booking>> outputList = bookingRepository.findAllByCustomerId(customerId);
        return outputList.orElse(null);
    }

    public List<Booking> getBookingsByReferenceNumber(String referenceNumber){

        Optional<List<Booking>> outputList = bookingRepository.findByReferenceNumber(referenceNumber);
        return outputList.orElse(null);
    }



}
