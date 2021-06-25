package entelect.training.incubator.spring.booking.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingCreationDTO {
    private Integer customerId;
    private Integer flightId;
}
