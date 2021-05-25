package com.greenparkingbook.desiredevparkingmap.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "charging_points")
public class ChargingPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charging_points_id_seq")
    @SequenceGenerator(name = "charging_points_id_seq", sequenceName = "charging_points_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "typeOfCurrent is mandatory")
    @Column(name = "type_of_current")
    private String typeOfCurrent;

    @NotBlank(message = "power is mandatory")
    private String power;

    @NotNull(message = "latitude is mandatory")
    @Column(precision = 4, scale = 2)
    private BigDecimal latitude;

    @NotNull(message = "longitude is mandatory")
    @Column(precision = 4, scale = 2)
    private BigDecimal longitude;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingPoint that = (ChargingPoint) o;
        return id.equals(that.id) &&
                typeOfCurrent.equals(that.typeOfCurrent) &&
                power.equals(that.power) &&
                latitude.equals(that.latitude) &&
                longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeOfCurrent, power, latitude, longitude);
    }
}
