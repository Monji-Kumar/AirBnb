package com.project.airbnb.entity.payment;

import com.project.airbnb.entity.booking.Booking;
import com.project.airbnb.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(generator = "payment_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1,sequenceName = "payment_seq_gen", name = "payment_seq", allocationSize = 1)
    private Long id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "price", columnDefinition = "DOUBLE PRECISION")
    private Double price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private PaymentStatus status;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
