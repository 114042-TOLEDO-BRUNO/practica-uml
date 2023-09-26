package ar.edu.utn.frc.tup.lciii.finalpractica.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
    @GetMapping("/hola")
    public String hola(){
        return "chau";
    }
}
