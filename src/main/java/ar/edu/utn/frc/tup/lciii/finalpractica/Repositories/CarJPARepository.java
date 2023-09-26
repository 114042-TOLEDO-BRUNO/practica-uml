package ar.edu.utn.frc.tup.lciii.finalpractica.Repositories;

import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarJPARepository extends JpaRepository<CarEntity,Long> {
    Optional<CarEntity>findByBrandAndModel(String brand,String model);

}
