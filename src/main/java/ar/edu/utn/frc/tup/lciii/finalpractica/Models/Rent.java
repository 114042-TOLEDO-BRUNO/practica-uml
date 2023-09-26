package ar.edu.utn.frc.tup.lciii.finalpractica.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    private Long id;
    private String customer;
    private Car carId;
    private Integer rentedDays;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDateTime startRent;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDateTime endRent;
    private BigDecimal totalPrice;

    public BigDecimal totalPriceMethod(){
        return new BigDecimal(rentedDays*carId.getTypeId().getPrice().intValue());
    }
    public Integer rentedDaysCount(){
        Duration daysCount=Duration.between(startRent,endRent);
        return Math.toIntExact(daysCount.toDays());
    }

}
