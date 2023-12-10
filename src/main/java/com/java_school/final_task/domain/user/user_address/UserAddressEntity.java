package com.java_school.final_task.domain.user.user_address;

import com.java_school.final_task.domain.user.user_address.postal_code.PostalCodeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Schema(description = "Address of a user")
@Table(name = "user_addresses", schema = "public", catalog = "online_store")
public class UserAddressEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postal_code", referencedColumnName = "code", nullable = false)
    private PostalCodeEntity postalCode;

    @Column(name = "street", nullable = false, length = 60)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;
}
