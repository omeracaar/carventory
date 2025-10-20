package com.omeracar.carventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallerist_car",
uniqueConstraints = {@UniqueConstraint
        (columnNames = {"gallerist_id" , "car_id"},
        name = "uq_gallerist_car")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleristCar extends BaseEntity{

    @ManyToOne
    private Gallerist gallerist;

    @OneToOne
    private Car car;

}
