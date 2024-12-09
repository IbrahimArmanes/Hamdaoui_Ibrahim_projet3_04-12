package com.openclassrooms.projet3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(nullable = false)
    private String message;
    
    private LocalDateTime created_at;
    
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
    
    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }
    
    public void setRental(Rental rental) {
        this.rental = rental;
    }
}