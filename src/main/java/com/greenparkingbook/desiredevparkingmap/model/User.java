package com.greenparkingbook.desiredevparkingmap.model;

import com.greenparkingbook.desiredevparkingmap.exception.ChargingPointDoesNotExist;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "provider_id")
    private String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChargingPoint> chargingPoints = new ArrayList<>();

    public void addChargingPoint(ChargingPoint chargingPoint) {
        chargingPoints.add(chargingPoint);
        chargingPoint.setUser(this);
    }

    public void removeChargingPoint(ChargingPoint chargingPoint) {
        chargingPoints.remove(chargingPoint);
        chargingPoint.setUser(null);
    }

    public void updateChargingPoint(ChargingPoint newChargingPoint) {
        Long id = newChargingPoint.getId();

        if (findChargingPointById(id).isPresent()) {
            removeChargingPoint(findChargingPointById(id).get());
            addChargingPoint(newChargingPoint);
        } else {
            throw new ChargingPointDoesNotExist(
                    String.format("User have no point with id = %s", id));
        }
    }

    public Optional<ChargingPoint> findChargingPointById(Long id) {

        Optional<ChargingPoint> chargingPointOptional = chargingPoints.stream()
                .filter(point -> point.getId().equals(id))
                .findFirst();
        return chargingPointOptional;
    }
}
