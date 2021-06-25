package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingCreationDTO;
import entelect.training.incubator.spring.booking.model.BookingSearchRequest;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(BookingCreationDTO bookingCreationDTO){

        //CREATE BOOKING AND ASSIGN PARAMETERS
        Booking booking = new Booking();
        booking.setId(12);
        booking.setCustomerId(bookingCreationDTO.getCustomerId());
        booking.setFlightId(bookingCreationDTO.getFlightId());
        booking.setReferenceNumber("RandomRef");

        //SAVE TO DB
        bookingRepository.save(booking);

        return booking;
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
