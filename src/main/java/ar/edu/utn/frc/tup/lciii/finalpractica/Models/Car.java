package ar.edu.utn.frc.tup.lciii.finalpractica.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private Long id;
    private CarType typeId;
    private String brand;
    private String model;
}
