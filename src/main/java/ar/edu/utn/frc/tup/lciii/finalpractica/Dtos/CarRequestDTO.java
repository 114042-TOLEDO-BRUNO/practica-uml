package ar.edu.utn.frc.tup.lciii.finalpractica.Dtos;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestDTO {
    @NotNull
    private Long carType;
    @NotNull
    private String brand;
    @NotNull
    private String model;
}
