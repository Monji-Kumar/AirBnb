package com.project.airbnb.entity.guest;

import com.project.airbnb.entity.user.User;
import com.project.airbnb.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(generator = "guest_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1,sequenceName = "guest_seq", allocationSize = 1,name = "guest_seq_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "gender")
    private Gender gender;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Gender getGender() {
        return gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
