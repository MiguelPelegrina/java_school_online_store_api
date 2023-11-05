package com.javaSchool.FinalTask3.domain.user.userAddress;

import com.javaSchool.FinalTask3.domain.user.userAddress.postalCode.PostalCodeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "user_addresses", schema = "public", catalog = "online_store")
public class UserAddressEntity {
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
