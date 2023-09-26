package ar.edu.utn.frc.tup.lciii.finalpractica.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rentals")
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String customer;

    @ManyToOne
    @JoinColumn(name="car_id",referencedColumnName = "id")
    private CarEntity carId;
    @Column(name="rented_days")
    private Integer rentedDays;
    @Column(name="start_rent")
    private LocalDateTime startRent;
    @Column(name="end_rent")
    private LocalDateTime endRent;
    @Column(name="total_price")
    private BigDecimal totalPrice;
}
