package com.project.airbnb.entity.contactinfo;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class ContactInfo {

    private String address;
    private String location;
    private String email;
    private String phoneNo;
}
