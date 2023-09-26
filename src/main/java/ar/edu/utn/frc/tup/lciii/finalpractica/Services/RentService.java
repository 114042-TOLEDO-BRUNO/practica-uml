package ar.edu.utn.frc.tup.lciii.finalpractica.Services;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.RentRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.RentResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.UpdateRentDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Rent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface RentService {
    List<RentResponseDTO> getAllRents();
    RentResponseDTO getRentById(Long id);
    RentResponseDTO newRent(RentRequestDTO rentRequestDTO);
    RentResponseDTO updateRent(UpdateRentDTO updateRentDTO);
    RentResponseDTO deleteRent(Long id);
    List<CarResponseDTO>getCarAvailabilityByDate(LocalDateTime date);


}
