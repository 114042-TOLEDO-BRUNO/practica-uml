package ar.edu.utn.frc.tup.lciii.finalpractica.Services.Impl;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.RentRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.RentResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.UpdateRentDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.RentEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.*;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Rent;
import ar.edu.utn.frc.tup.lciii.finalpractica.Repositories.RentJPARepository;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.CarService;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.RentService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentJPARepository rentJPARepository;

    @Autowired
    private ModelMapper mergerMapper;
    @Autowired
    private CarService carService;
    @Override
    public List<RentResponseDTO> getAllRents() {
        List<RentEntity> rentEntityList=rentJPARepository.findAll();
        return rentEntityList.stream()
                .map(rentEntity -> modelMapper.map(rentEntity,RentResponseDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public RentResponseDTO getRentById(Long id) {
        RentEntity rentEntity=rentJPARepository.getReferenceById(id);
        if(Objects.isNull(rentEntity.getId())){
            return null;
        }
        return modelMapper.map(rentEntity,RentResponseDTO.class);
    }
    @Override
    public RentResponseDTO newRent(RentRequestDTO rentRequestDTO) {
        Rent rent = new Rent();
        Car car = carService.getCarById(rentRequestDTO.getCarId()); // Obtener el carro correcto por su ID

        rent.setCarId(car);
        rent.getCarId().setBrand(car.getBrand());
        rent.getCarId().setModel(car.getModel());
        rent.setCustomer(rentRequestDTO.getCustomer());
        rent.setStartRent(rentRequestDTO.getStartRent());
        rent.setEndRent(rentRequestDTO.getEndRent());
        if(!availableCar(car.getId(),rentRequestDTO.getStartRent(),rentRequestDTO.getEndRent())){
            throw new EntityNotFoundException("the car is not available");
        }
        if(rentRequestDTO.getStartRent().isAfter(rentRequestDTO.getEndRent())){
            return null;
        }
        rent.setRentedDays(rent.rentedDaysCount()); // Usa el método que calcula los días alquilados.
        rent.setTotalPrice(rent.totalPriceMethod());
        RentEntity rentEntity = modelMapper.map(rent, RentEntity.class);
        RentEntity rentEntitySaved = rentJPARepository.saveAndFlush(rentEntity);
        return modelMapper.map(rentEntitySaved, RentResponseDTO.class);
    }


    private boolean availableCar(Long id, LocalDateTime startRent,LocalDateTime endRent) {
        List<RentEntity>rentEntityList=rentJPARepository.findAll();
        for(RentEntity rentEntity:rentEntityList){
            if (rentEntity.getCarId().getId().equals(id) &&
                    rentEntity.getEndRent().isAfter(startRent) &&
                    rentEntity.getStartRent().isBefore(endRent)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public RentResponseDTO updateRent(UpdateRentDTO updateRentDTO) {
        RentEntity rentEntity=rentJPARepository.getReferenceById(updateRentDTO.getId());
        mergerMapper.map(modelMapper.map(updateRentDTO,RentEntity.class),rentEntity);
        Car car=carService.getCarById(updateRentDTO.getCarId());//para que salga la marca y modelo en el put
        rentEntity.setCarId(modelMapper.map(car,CarEntity.class));
        RentEntity rentEntitySaved=rentJPARepository.saveAndFlush(rentEntity);
        return modelMapper.map(rentEntitySaved,RentResponseDTO.class);
    }

    @Override
    public RentResponseDTO deleteRent(Long id) {
        RentEntity rentEntity=rentJPARepository.getReferenceById(id);
        if(Objects.isNull(rentEntity.getId())){
            throw new NullPointerException("this id is not found");
        }
        rentJPARepository.delete(rentEntity);
        return modelMapper.map(rentEntity,RentResponseDTO.class);
    }

    @Override
    public List<CarResponseDTO> getCarAvailabilityByDate(LocalDateTime dateTime) {
        List<CarEntity> carEntityList = carService.getAllCarsEntity();
        List<RentEntity> rentEntityList = rentJPARepository.findAll();
        List<Long> rentedCarIds = new ArrayList<>();
        for (RentEntity rentEntity : rentEntityList) {
            if (!dateTime.isAfter(rentEntity.getEndRent()) &&
                    !dateTime.isBefore(rentEntity.getStartRent())) {
                rentedCarIds.add(rentEntity.getCarId().getId());
            }
        }
        List<CarResponseDTO> carResponseDTOList = new ArrayList<>();

        for (CarEntity carEntity : carEntityList) {
            if (!rentedCarIds.contains(carEntity.getId())) {
                carResponseDTOList.add(modelMapper.map(carEntity, CarResponseDTO.class));
            }
        }
        return carResponseDTOList;
    }
}
