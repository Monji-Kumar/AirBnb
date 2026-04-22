package com.project.airbnb.entity.hotel;

import com.project.airbnb.entity.contactinfo.ContactInfo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(generator = "hotel_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "hotel_seq_gen", sequenceName = "hotel_seq")
    private Long id;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "contact_info_id")
    private ContactInfo contactInfo;

    @Column(name = "photoes", columnDefinition = "TEXT[]")
    private List<String> photoes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "amenities", columnDefinition = "TEXT[]")
    private List<String> amenities;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    public String getCity() {
        return city;
    }

    public Long getId() {
        return id;
    }

    public List<String> getPhotoes() {
        return photoes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhotoes(List<String> photoes) {
        this.photoes = photoes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
