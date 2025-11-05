package com.homework.Parking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "clients", uniqueConstraints = @UniqueConstraint(columnNames = "phone_number"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "age")
    @Min(value = 18, message = "Возраст должен быть не меньше 18 лет.")
    private int age;

    @Column(name = "phone_number", unique = true)
    @NotBlank
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<SpotParking> spotParking = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reserve> reserveList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return age == client.age && Objects.equals(id, client.id)
                && Objects.equals(name, client.name) && Objects.equals(phoneNumber, client.phoneNumber)
                && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", numberPhone='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
