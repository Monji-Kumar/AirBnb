package com.project.airbnb.entity.hotel;

import com.project.airbnb.entity.contactinfo.ContactInfo;
import com.project.airbnb.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Hotel {

    @Id
    @GeneratedValue(generator = "hotel_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "hotel_seq_gen", sequenceName = "hotel_seq")
    private Long id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "city")
    private String city;

    @Embedded
    private ContactInfo contactInfo;

    @Column(name = "photoes", columnDefinition = "TEXT[]")
    private List<String> photoes;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "amenities", columnDefinition = "TEXT[]")
    private List<String> amenities;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    @ManyToOne()
    @JoinColumn(name = "owner")
    private User owner;
}
