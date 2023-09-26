package ar.edu.utn.frc.tup.lciii.finalpractica.Config;


import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarTypeEntity;
import ar.edu.utn.frc.tup.lciii.finalpractica.Models.CarType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//esta anotacion me dice que esta clase tiene metodos que levantan beans
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper=new ModelMapper();

        TypeMap<CarTypeEntity, CarType> carTypeMap = mapper.createTypeMap(CarTypeEntity.class, CarType.class);
        carTypeMap.addMapping(CarTypeEntity::getType, CarType::setType);





        return mapper;
    }

    @Bean @Qualifier("mergerMapper")//otro modelmappe
    public ModelMapper mergerMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {//seteo de configuracion de localdatetime para que serialice correctamente
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());//EL JAVATIMEMODULE INTERPRETA EL LOCALDATETIME
        return objectMapper;
    }
}
