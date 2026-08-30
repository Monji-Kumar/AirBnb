package com.project.airbnb.entity.contactinfo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo {

    private String address;
    private String location;
    private String email;
    private String phoneNo;
}
