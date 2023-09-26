package ar.edu.utn.frc.tup.lciii.finalpractica;

import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.CarResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.RentRequestDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Dtos.RentResponseDTO;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarTypeEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.RentEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Car;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.CarType;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.Rent;
import ar.edu.utn.frc.tup.lciii.finalpractica.Repositories.CarJPARepository;
import ar.edu.utn.frc.tup.lciii.finalpractica.Repositories.RentJPARepository;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.CarService;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.Impl.RentServiceImpl;
import ar.edu.utn.frc.tup.lciii.finalpractica.Services.RentService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentServiceTest {
    @Mock
    private CarService carService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private RentJPARepository rentJPARepository;
    @InjectMocks
    private RentServiceImpl rentService;
//    @Mock
//    private Rent rent;
    @Test
    public void getCarAvailabilityByDateIsEmptyRentTest(){
        LocalDateTime dateTime=LocalDateTime.of(2023,9,7,0,0);

        List<CarEntity> carEntityList = new ArrayList<>();
        // Populate carEntityList with some test CarEntity objects
        CarEntity car=new CarEntity(1L,(new CarTypeEntity(1L,"Auto",new BigDecimal(10000))),"Fiat","argo");
        carEntityList.add(car);
        List<RentEntity> rentEntityList = new ArrayList<>();
        // Populate rentEntityList with some test RentEntity objects

        when(carService.getAllCarsEntity()).thenReturn(carEntityList);
        when(rentJPARepository.findAll()).thenReturn(rentEntityList);
        List<CarResponseDTO>result=rentService.getCarAvailabilityByDate(dateTime);
        assertEquals(1,result.size());
    }
    @Test
    public void getCarAvailabilityByDateIsNotEmptyRentTest(){
        LocalDateTime dateTime=LocalDateTime.of(2023,9,7,0,0);

        List<CarEntity> carEntityList = new ArrayList<>();
        // Populate carEntityList with some test CarEntity objects
        CarEntity car=new CarEntity(1L,(new CarTypeEntity(1L,"Auto",new BigDecimal(10000))),"Fiat","argo");
        carEntityList.add(car);
        List<RentEntity> rentEntityList = new ArrayList<>();
        // Populate rentEntityList with some test RentEntity objects
        rentEntityList.add(new RentEntity(1L,"pepe",car,1,dateTime,dateTime,new BigDecimal(10000)));
        when(carService.getAllCarsEntity()).thenReturn(carEntityList);
        when(rentJPARepository.findAll()).thenReturn(rentEntityList);
        List<CarResponseDTO>result=rentService.getCarAvailabilityByDate(dateTime);
        assertEquals(0,result.size());
    }
    @Test
    public void getAllRentsTest(){
        List<RentResponseDTO>rentResponseDTOList=rentService.getAllRents();

        RentResponseDTO rentResponseDTO=new RentResponseDTO();
        rentResponseDTO.setId(1L);
        rentResponseDTO.setCustomer("pepe");
        rentResponseDTO.setCarBrand("Fiat");
        rentResponseDTO.setCarModel("500");
        rentResponseDTO.setStartRent(LocalDateTime.of(2023,8,7,0,0));
        rentResponseDTO.setEndRent(LocalDateTime.of(2023,8,9,0,0));
        rentResponseDTO.setRentedDays(2);
        rentResponseDTO.setTotalPrice(new BigDecimal(2000));
        rentResponseDTO.setCarType("auto");

        rentResponseDTOList.add(rentResponseDTO);
        when(rentService.getAllRents()).thenReturn(rentResponseDTOList);

        assertEquals(1,rentResponseDTOList.size());

    }

    @Test
    public void getRentByIdTest(){
        LocalDateTime datetime=LocalDateTime.of(2023,8,7,0,0);
        CarEntity car=new CarEntity(1L,(new CarTypeEntity(1L,"Auto",new BigDecimal(10000))),"Fiat","argo");
        RentEntity rentEntity=new RentEntity(1L,"pepe",car,1,datetime,datetime,new BigDecimal(10000));
        when(rentJPARepository.getReferenceById(1L)).thenReturn(rentEntity);
        RentResponseDTO rentResponseDTO1=new RentResponseDTO();
        rentResponseDTO1.setId(1L);
        rentResponseDTO1.setCustomer("pepe");
        rentResponseDTO1.setCarBrand("Fiat");
        rentResponseDTO1.setCarModel("500");
        rentResponseDTO1.setStartRent(LocalDateTime.of(2023,8,7,0,0));
        rentResponseDTO1.setEndRent(LocalDateTime.of(2023,8,9,0,0));
        rentResponseDTO1.setRentedDays(2);
        rentResponseDTO1.setTotalPrice(new BigDecimal(2000));
        rentResponseDTO1.setCarType("auto");
        when(rentService.getRentById(1L)).thenReturn(rentResponseDTO1);
        RentResponseDTO result = rentService.getRentById(1L);;
        assertEquals(rentResponseDTO1,result);
    }
    @Test
    public void testAvailableCarTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Configuración de datos para la prueba
        Long carId = 1L;
        LocalDateTime startRent = LocalDateTime.of(2023, 8, 8, 0, 0);
        LocalDateTime endRent = LocalDateTime.of(2023, 8, 9, 0, 0);
        // Configurar rentEntityList con datos simulados
        CarEntity car=new CarEntity(1L,(new CarTypeEntity(1L,"Auto",new BigDecimal(10000))),"Fiat","argo");
        LocalDateTime datetime=LocalDateTime.of(2023,8,6,0,0);
        LocalDateTime dateTime1=LocalDateTime.of(2023,8,7,0,0);
        RentEntity rentEntity=new RentEntity(1L,"pepe",car,1,datetime,dateTime1,new BigDecimal(10000));


        // Obtener el método availableCar usando reflexión
        Method availableCarMethod = RentServiceImpl.class.getDeclaredMethod("availableCar", Long.class, LocalDateTime.class, LocalDateTime.class);
        availableCarMethod.setAccessible(true); // Hacer el método accesible
        // Crear una lista de RentEntity simulada para el test
        List<RentEntity> rentEntityList = new ArrayList<>();
        rentEntityList.add(rentEntity);
        when(rentJPARepository.findAll()).thenReturn(rentEntityList);

        // Llamar al método availableCar usando reflexión y obtener el resultado
        boolean isCarAvailable = (boolean) availableCarMethod.invoke(rentService, carId, startRent, endRent);
        // Verificar el resultado esperado
        assertTrue(isCarAvailable);
    }
    @Test
    public void testAvailableCarFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Configuración de datos para la prueba
        Long carId = 1L;
        LocalDateTime startRent = LocalDateTime.of(2023, 8, 8, 0, 0);
        LocalDateTime endRent = LocalDateTime.of(2023, 8, 9, 0, 0);
        // Configurar rentEntityList con datos simulados
        CarEntity car=new CarEntity(1L,(new CarTypeEntity(1L,"Auto",new BigDecimal(10000))),"Fiat","argo");
        LocalDateTime datetime=LocalDateTime.of(2023,8,8,0,0);
        LocalDateTime dateTime1=LocalDateTime.of(2023,8,9,0,0);
        RentEntity rentEntity=new RentEntity(1L,"pepe",car,1,datetime,dateTime1,new BigDecimal(10000));


        // Obtener el método availableCar usando reflexión
        Method availableCarMethod = RentServiceImpl.class.getDeclaredMethod("availableCar", Long.class, LocalDateTime.class, LocalDateTime.class);
        availableCarMethod.setAccessible(true); // Hacer el método accesible
        // Crear una lista de RentEntity simulada para el test
        List<RentEntity> rentEntityList = new ArrayList<>();
        rentEntityList.add(rentEntity);
        when(rentJPARepository.findAll()).thenReturn(rentEntityList);

        // Llamar al método availableCar usando reflexión y obtener el resultado
        boolean isCarAvailable = (boolean) availableCarMethod.invoke(rentService, carId, startRent, endRent);
        // Verificar el resultado esperado
        assertFalse(isCarAvailable);
    }




}
