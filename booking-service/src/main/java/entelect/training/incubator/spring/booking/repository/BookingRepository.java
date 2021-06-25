package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    Optional<Booking> findById(Integer id);
    Optional<List<Booking>> findAllByCustomerId(Integer customerId);
    Optional<List<Booking>> findByReferenceNumber(String referenceNumber);
}
