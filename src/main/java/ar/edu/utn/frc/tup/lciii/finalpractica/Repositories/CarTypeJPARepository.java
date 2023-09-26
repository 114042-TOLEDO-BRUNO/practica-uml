package ar.edu.utn.frc.tup.lciii.finalpractica.Repositories;


import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.CarTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CarTypeJPARepository extends JpaRepository<CarTypeEntity,Long> {


}
