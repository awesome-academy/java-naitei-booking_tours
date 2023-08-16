package com.naite.bookingTour.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tours")
public class Tour implements Serializable {
	/**
	 * 
	 * @author PHUONG MINH
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Size(max = 200)
	@Column(name = "name", length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
	private String description;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;
    
    @Column(name = "location", columnDefinition = "TEXT")
    private String location;
    
    @Column(name= "duration")
    private Integer duration;
    
    @Transient
    private String imageUrl;

    @ManyToMany
    @JoinTable(name = "tour_categories",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Transient
    private List<Long> categoryIds;

}
