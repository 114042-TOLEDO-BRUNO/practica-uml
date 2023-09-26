package ar.edu.utn.frc.tup.lciii.finalpractica.Controllers;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.UpdateCarDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Car;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.CarService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarResponseDTO>> getAllCars(){
        List<CarResponseDTO> carList=carService.getAllCars();
        return ResponseEntity.ok(carList);
    }
    @GetMapping("/carsEntity")
    public ResponseEntity<List<CarEntity>> getAllCarsEntity(){
        List<CarEntity> carList=carService.getAllCarsEntity();
        if(carList.isEmpty()){
            throw new EntityNotFoundException("the cars list is not found");
        }
        return ResponseEntity.ok(carList);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarResponseDTO>getCarById(@PathVariable Long id){
        CarResponseDTO carResponseDTO =carService.getCarDTOById(id);
        if(carResponseDTO==null){
            throw new NullPointerException("the car id is not exist in de database");
        }
        return ResponseEntity.ok(carResponseDTO);
    }
    @PostMapping("/newCar")
    public ResponseEntity<CarResponseDTO>postCar(@RequestBody @Valid CarRequestDTO carRequestDTO){
        CarResponseDTO carResponseDTO=carService.newCar(carRequestDTO);
        return ResponseEntity.ok(carResponseDTO);
    }
    @PutMapping("/updateCar")
    public ResponseEntity<CarResponseDTO>putCar(@RequestBody @Valid UpdateCarDTO updateCarDTO){
        CarResponseDTO carResponseDTO=carService.updateCar(updateCarDTO);
        return ResponseEntity.ok(carResponseDTO);
    }
    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<CarResponseDTO>deleteCar(@PathVariable Long id){
        CarResponseDTO carResponseDTO=carService.delete(id);
        if(Objects.isNull(carResponseDTO)){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"id not found");
        }
        return ResponseEntity.ok(carResponseDTO);
    }
}
