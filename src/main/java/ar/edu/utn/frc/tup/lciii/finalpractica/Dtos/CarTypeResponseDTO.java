package ar.edu.utn.frc.tup.lciii.finalpractica.Dtos;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarTypeResponseDTO {
    @NotNull
    private Long id;
    @NotNull
    private String type;
}
