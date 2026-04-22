package com.project.airbnb.entity.payment;

import com.project.airbnb.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(generator = "payment_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1,sequenceName = "payment_seq_gen", name = "payment_seq", allocationSize = 1)
    private Long id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "price", columnDefinition = "DOUBLE PRECISION")
    private Double price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private PaymentStatus status;

    public Long getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
