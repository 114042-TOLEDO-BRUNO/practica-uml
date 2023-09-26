package ar.edu.utn.frc.tup.lciii.finalpractica.Services;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarTypeResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarTypeService {
    List<CarTypeResponseDTO> getAllTypes();
    CarTypeResponseDTO newTypes(CarTypeResponseDTO carTypeResponseDTO);
}
