package com.greenparkingbook.desiredevparkingmap.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="provider_id")
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
}
