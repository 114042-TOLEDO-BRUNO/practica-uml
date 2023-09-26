package ar.edu.utn.frc.tup.lciii.finalpractica;

import ar.edu.utn.frc.tup.lciii.finalpractica.Controllers.HolaController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinalPracticaApplicationTests {
    @Autowired
    private HolaController controller;
    @Test
    void contextLoads()throws Exception {
        assertThat(controller).isNotNull();
    }

}
