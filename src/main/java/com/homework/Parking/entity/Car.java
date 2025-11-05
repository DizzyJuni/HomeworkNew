package com.homework.Parking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Entity
@Table (name = "cars", uniqueConstraints = @UniqueConstraint (columnNames = "number"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    @NotBlank
    private String brand;

    @Column(name = "model")
    @NotBlank
    private String model;

    @Column(name = "colour")
    @NotBlank
    private String colour;

    @Column(name = "number", unique = true)
    @NotBlank
    private String number;

    @Column (name = "year")
    @NotNull
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Client owner;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(colour, car.colour) && Objects.equals(number, car.number) && Objects.equals(year, car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, colour, number, year);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", numberPhone='" + number + '\'' +
                ", year=" + year +
                '}';
    }
}
