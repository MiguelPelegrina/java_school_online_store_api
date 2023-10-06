package com.javaSchool.FinalTask3.entities;

import jakarta.persistence.Column;
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
@Table(name = "order_statuses", schema = "public", catalog = "online_store")
public class OrderStatus {
    @Id
    @Column(name = "name", length = 45)
    private String name;
}
