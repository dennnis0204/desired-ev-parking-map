package com.greenparkingbook.desiredevparkingmap.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="polish_cities")
public class City {

    @Id
    private Long id;

    private float latitude;

    private float longitude;

    private String name;
}
