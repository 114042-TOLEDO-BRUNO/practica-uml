package ar.edu.utn.frc.tup.lciii.finalpractica.Services.Impl;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarTypeResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.UpdateCarDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarTypeEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Car;
import ar.edu.utn.frc.tup.lciii.finalpractica.Repositories.CarJPARepository;
import ar.edu.utn.frc.tup.lciii.finalpractica.Repositories.CarTypeJPARepository;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.CarService;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.CarTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarTypeServiceImpl implements CarTypeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper mergerMapper;
    @Autowired
    private CarTypeJPARepository carTypeJPARepository;
    @Override
    public List<CarTypeResponseDTO> getAllTypes() {
        List<CarTypeEntity> carTypeEntities=carTypeJPARepository.findAll();
        List<CarTypeResponseDTO> carTypeResponseDTOList=carTypeEntities.stream()
                .map(carTypeEntity -> modelMapper.map(carTypeEntity,CarTypeResponseDTO.class))
                .collect(Collectors.toList());
        return carTypeResponseDTOList;
    }

    @Override
    public CarTypeResponseDTO newTypes(CarTypeResponseDTO carTypeResponseDTO) {
        CarTypeEntity carTypeEntity=carTypeJPARepository.getReferenceById(carTypeResponseDTO.getId());
        if(Objects.isNull(carTypeEntity.getType())){
            CarTypeEntity carTypeEntity1=modelMapper.map(carTypeResponseDTO,CarTypeEntity.class);
            CarTypeEntity carTypeSaved=carTypeJPARepository.save(carTypeEntity1);
            return modelMapper.map(carTypeSaved,CarTypeResponseDTO.class);
        }
        throw new IllegalArgumentException("the type is present");
    }




}
