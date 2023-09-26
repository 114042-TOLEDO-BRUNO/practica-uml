package ar.edu.utn.frc.tup.lciii.finalpractica.Dtos;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.CarType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarDTO {
    @NotNull
    private Long id;
    private Long carTypeId;
    private String brand;
    @NotNull
    private String model;
}
