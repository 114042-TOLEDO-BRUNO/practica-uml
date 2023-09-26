package ar.edu.utn.frc.tup.lciii.finalpractica.Controllers;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarTypeResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.CarTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CarTypeController {
    @Autowired
    private CarTypeService carTypeService;

    @GetMapping("/carTypes")
    public ResponseEntity<List<CarTypeResponseDTO>> getAllTypes(){
        List<CarTypeResponseDTO> carTypeList=carTypeService.getAllTypes();
        return ResponseEntity.ok(carTypeList);
    }

    @PostMapping("/newType")
    public ResponseEntity<CarTypeResponseDTO>postType(@RequestBody @Valid CarTypeResponseDTO carTypeResponseDTO){
        CarTypeResponseDTO carTypeResponseDTO1=carTypeService.newTypes(carTypeResponseDTO);
        return ResponseEntity.ok(carTypeResponseDTO1);
    }


}
