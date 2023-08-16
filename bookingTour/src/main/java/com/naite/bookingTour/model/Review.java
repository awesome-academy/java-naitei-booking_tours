package com.naite.bookingTour.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    
	public Review() {
		super();
	}

	public Review(Long id, String content, Integer rating, LocalDateTime createdAt, User user, Tour tour) {
		super();
		this.id = id;
		this.content = content;
		this.rating = rating;
		this.createdAt = createdAt;
		this.user = user;
		this.tour = tour;
	}

    
}
