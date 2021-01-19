package com.owntech.taskmanagement.audit;

import com.owntech.taskmanagement.converter.LocalDateTimeConverterAttribute;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable<U> {

    @CreatedBy
    protected U createdBy;
    @CreatedDate
    @Convert(converter = LocalDateTimeConverterAttribute.class)
    protected LocalDateTime createdDate;
    @LastModifiedBy
    protected U lastModifiedBy;
    @Convert(converter = LocalDateTimeConverterAttribute.class)
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}
