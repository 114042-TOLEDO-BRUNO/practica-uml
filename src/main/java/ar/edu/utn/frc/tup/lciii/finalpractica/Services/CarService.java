package ar.edu.utn.frc.tup.lciii.finalpractica.Services;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.UpdateCarDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    List<CarResponseDTO> getAllCars();
    List<CarEntity>getAllCarsEntity();
    Car getCarById(Long id);
    CarResponseDTO getCarDTOById(Long id);
    CarResponseDTO newCar(CarRequestDTO carResponseDTO);
    CarResponseDTO updateCar(UpdateCarDTO updateCarDTO);
    CarResponseDTO delete(Long id);
}
