package com.project.airbnb.entity.guest;

import com.project.airbnb.entity.user.User;
import com.project.airbnb.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "guest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "gender")
    private Gender gender;
}
