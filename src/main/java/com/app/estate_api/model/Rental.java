package com.app.estate_api.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="RENTALS")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private Double surface;

    @Column
    private Double price;

    @Column
    private String picture;

    @Column
    private String description;

    
    @Column(name="created_at")
    private Date createdAt;
    
    @Column(name="updated_at")
    private Date updatedAt;
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private User owner;

}
