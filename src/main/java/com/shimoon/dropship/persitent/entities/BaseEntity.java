package com.shimoon.dropship.persitent.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Time;

@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_date")
    @Getter(AccessLevel.PUBLIC) @Setter
    private Time createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    @Getter(AccessLevel.PUBLIC) @Setter
    private Time updatedDate;
}
