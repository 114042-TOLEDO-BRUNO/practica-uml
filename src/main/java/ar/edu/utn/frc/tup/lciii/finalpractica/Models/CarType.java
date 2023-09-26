package ar.edu.utn.frc.tup.lciii.finalpractica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarType {
    private Long id;
    private String type;
    private BigDecimal price;
}
