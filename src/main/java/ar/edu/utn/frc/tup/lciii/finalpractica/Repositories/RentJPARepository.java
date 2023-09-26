package ar.edu.utn.frc.tup.lciii.finalpractica.Repositories;

import ar.edu.utn.frc.tup.lciii.finalpractica.Entities.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentJPARepository extends JpaRepository<RentEntity,Long> {

    @Query("select re from RentEntity re where re.carId= :cardIdRequest and re.startRent>= :endRentRequest and re.endRent<= :startRentRequest")
    Optional<List<RentEntity>> findAvailableRent(
            @Param("cardIdRequest") Long carIdRequest,
            @Param("endRentRequest")LocalDateTime endRentRequest,
            @Param("startRentRequest")LocalDateTime startRentRequest
    );

}
