package com.naite.bookingTour.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "num_of_guests")
    private Integer numOfGuests;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public enum BookingStatus {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELLED
    }

    public Booking() {
        super();
    }

    public Booking(Long id, String fullName, String email, String phone, Date createdAt, BigDecimal price,
            Integer numOfGuests, BookingStatus status, User user, Tour tour) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
        this.price = price;
        this.numOfGuests = numOfGuests;
        this.status = status;
        this.user = user;
        this.tour = tour;
    }
}