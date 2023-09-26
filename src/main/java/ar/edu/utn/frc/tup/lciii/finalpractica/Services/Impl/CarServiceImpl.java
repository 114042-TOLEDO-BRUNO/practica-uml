package ar.edu.utn.frc.tup.lciii.finalpractica.Services.Impl;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.UpdateCarDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Car;
import ar.edu.utn.frc.tup.lciii.finalpractica.Repositories.CarJPARepository;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.CarService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper mergerMapper;
    @Autowired
    private CarJPARepository carJPARepository;

    @Override
    public List<CarResponseDTO> getAllCars() {
        List<CarEntity> carEntities = carJPARepository.findAll();
        List<CarResponseDTO> carResponseDTOList =carEntities.stream()
                .map(carEntity -> modelMapper.map(carEntity, CarResponseDTO.class))
                .collect(Collectors.toList());
        return carResponseDTOList;
    }

    @Override
    public List<CarEntity> getAllCarsEntity() {
        return carJPARepository.findAll();
    }


    @Override
    public Car getCarById(Long id) {
        CarEntity carEntity=carJPARepository.getReferenceById(id);
        if(Objects.isNull(carEntity.getId())){
            throw new NullPointerException("the car id is null");
        }
        return modelMapper.map(carEntity,Car.class);
    }
    @Override
    public CarResponseDTO getCarDTOById(Long id) {
        CarEntity carEntity=carJPARepository.getReferenceById(id);
        if(Objects.isNull(carEntity.getBrand())){
           return null;
        }
        return modelMapper.map(carEntity, CarResponseDTO.class);
    }

    @Override
    public CarResponseDTO newCar(CarRequestDTO carRequestDTO) {
        Optional<CarEntity> carEntityOptional=carJPARepository.findByBrandAndModel(carRequestDTO.getBrand(), carRequestDTO.getModel());
        if(carEntityOptional.isPresent()){
            throw new IllegalArgumentException("the brand and model is present");
        }
        CarEntity carEntity=modelMapper.map(carRequestDTO,CarEntity.class);
        CarEntity carEntitySaved=carJPARepository.save(carEntity);
        return modelMapper.map(carEntitySaved,CarResponseDTO.class);
    }

    @Override
    public CarResponseDTO updateCar(UpdateCarDTO updateCarDTO) {
        CarEntity carEntity=carJPARepository.getReferenceById(updateCarDTO.getId());
        mergerMapper.map(modelMapper.map(updateCarDTO,CarEntity.class),carEntity);
        CarEntity carEntitySaved=carJPARepository.saveAndFlush(carEntity);
        return modelMapper.map(carEntitySaved,CarResponseDTO.class);
    }

    @Override
    public CarResponseDTO delete(Long id) {
        CarEntity carEntity=carJPARepository.getReferenceById(id);
        carJPARepository.deleteById(id);
        return modelMapper.map(carEntity,CarResponseDTO.class);
    }
}
