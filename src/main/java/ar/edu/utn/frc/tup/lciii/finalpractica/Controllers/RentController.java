package ar.edu.utn.frc.tup.lciii.finalpractica.Controllers;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.*;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Rent;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.RentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
public class RentController {
    @Autowired
    private RentService rentService;

    @GetMapping("/allRents")
    public ResponseEntity<List<RentResponseDTO>>getAllRents(){
        List<RentResponseDTO> responseEntityList=rentService.getAllRents();
        return ResponseEntity.ok(responseEntityList);
    }
    @GetMapping("/rent/{id}")
    public ResponseEntity<RentResponseDTO>getRentById(@PathVariable Long id){
        RentResponseDTO rentResponseDTO=rentService.getRentById(id);
        if(Objects.isNull(rentResponseDTO.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the id rent is not found");
        }
        return ResponseEntity.ok(rentResponseDTO);
    }
    @PostMapping("/newrent")
    public ResponseEntity<RentResponseDTO>postRent(@RequestBody @Valid RentRequestDTO rentRequestDTO){
        RentResponseDTO rentResponseDTO=rentService.newRent(rentRequestDTO);
        if(Objects.isNull(rentResponseDTO)){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400),"the start rent date cant later than end rent date");
        }
        return ResponseEntity.ok(rentResponseDTO);
    }
    @PutMapping("/updateRent")
    public ResponseEntity<RentResponseDTO>putRent(@RequestBody @Valid UpdateRentDTO updateRentDTO){
        RentResponseDTO rentResponseDTO=rentService.updateRent(updateRentDTO);
        if(Objects.isNull(rentResponseDTO)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the rent not exist");
        }
        return ResponseEntity.ok(rentResponseDTO);
    }
    @DeleteMapping("/deleteRent/{id}")
    public ResponseEntity<String>deleteRent(@PathVariable Long id){
        RentResponseDTO rentResponseDTO=rentService.deleteRent(id);
        if(Objects.isNull(rentResponseDTO)){
            throw new NullPointerException("the id rent is not found");
        }
        return ResponseEntity.ok("the rent with id" +id+" is deleted");
    }
    @GetMapping("/rentsAvailabilityByDate/{date}")
    public ResponseEntity<List<CarResponseDTO>> getCarAvailabilityByDate(@PathVariable String date)
    {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        LocalDateTime currentDateTime = LocalDateTime.now();
        if (dateTime.isBefore(currentDateTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the date is can not before the current date");
        }

        return ResponseEntity.ok(rentService.getCarAvailabilityByDate(dateTime));
    }


}
