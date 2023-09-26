package ar.edu.utn.frc.tup.lciii.finalpractica.Dtos;

import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentResponseDTO {
    private Long id;
    private String customer;
    private String carType;
    private String carBrand;
    private String carModel;
    private Integer rentedDays;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime startRent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime endRent;
    private BigDecimal totalPrice;
}
