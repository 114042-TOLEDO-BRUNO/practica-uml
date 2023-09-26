package ar.edu.utn.frc.tup.lciii.finalpractica.Dtos;

import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Car;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentRequestDTO {
    private String customer;
    @NotNull
    private Long carId;
    @NotNull
    private LocalDateTime startRent;
    @NotNull
    private LocalDateTime endRent;
}
