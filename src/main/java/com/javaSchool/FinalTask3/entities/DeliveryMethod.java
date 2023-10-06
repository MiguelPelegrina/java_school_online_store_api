package com.javaSchool.FinalTask3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "delivery_methods", schema = "public", catalog = "online_store")
public class DeliveryMethod {
    @Id
    private String name;
}
