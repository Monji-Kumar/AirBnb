package com.project.airbnb.entity.contactinfo;

import jakarta.persistence.*;

@Entity
@Table(name = "contact_info")
public class ContactInfo {
    @Id
    @GeneratedValue(generator = "contact_info_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, sequenceName = "contact_info_seq", allocationSize = 1, name = "contact_info_seq_gen")
    private Long id;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "location")
    private String location;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
