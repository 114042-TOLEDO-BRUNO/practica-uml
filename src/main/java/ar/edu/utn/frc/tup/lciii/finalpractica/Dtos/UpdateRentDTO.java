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
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentDTO {
        @NotNull
        private Long id;
        private String customer;
        @NotNull
        private Long carId;
        private LocalDateTime startRent;
        private LocalDateTime endRent;
}
