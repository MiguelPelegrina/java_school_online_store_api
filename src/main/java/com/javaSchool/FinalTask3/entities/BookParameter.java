package com.javaSchool.FinalTask3.entities;

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
@Table(name = "book_parameters", schema = "public", catalog = "online_store")
public class BookParameter {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "author", nullable = false, length = 60)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "format", referencedColumnName = "name", nullable = false)
    private BookParametersFormat format;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}