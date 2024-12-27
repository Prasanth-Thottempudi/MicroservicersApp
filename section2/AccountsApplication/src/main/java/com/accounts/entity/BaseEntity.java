package com.accounts.entity;


import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Locale;

@Data
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private String updatedBy;

    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
