package com.homework.Parking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "spot_parking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpotParking {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "floor")
    @NotNull
    @Max(value = 4, message = "Последний этаж 4.")
    private Integer floor;

    @Column(name = "spot_number")
    @NotNull
    @Positive
    @Max(value = 10, message = "Максимальное количество мест на этаже 10.")
    private Integer spotNumber;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "client_id")
    private Client client;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "price_id")
    private Price price;

    @Column (name = "is_busy")
    private boolean isBusy = false;

    @OneToMany(mappedBy = "spot")
    private List<Reserve> reserves = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SpotParking that = (SpotParking) o;
        return isBusy == that.isBusy && Objects.equals(id, that.id) && Objects.equals(floor, that.floor) && Objects.equals(spotNumber, that.spotNumber) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, floor, spotNumber, client, isBusy);
    }

    @Override
    public String toString() {
        return "SpotParking{" +
                "id=" + id +
                ", floor=" + floor +
                ", spotNumber=" + spotNumber +
                ", client=" + client +
                ", isBusy=" + isBusy +
                '}';
    }
}
