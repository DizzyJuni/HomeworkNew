package com.homework.Parking.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reserve")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reserve {

    @Id
    @Column(name = "reserve_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private SpotParking spot;

    @Column(name = "start_reserve")
    private LocalDateTime startReserve;

    @Column(name = "end_reserve")
    private LocalDateTime endReserve;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserve reserve = (Reserve) o;
        return Objects.equals(id, reserve.id) && Objects.equals(client, reserve.client) && Objects.equals(startReserve, reserve.startReserve) && Objects.equals(endReserve, reserve.endReserve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, startReserve, endReserve);
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", client=" + client +
                ", startReserve=" + startReserve +
                ", endReserve=" + endReserve +
                '}';
    }
}
