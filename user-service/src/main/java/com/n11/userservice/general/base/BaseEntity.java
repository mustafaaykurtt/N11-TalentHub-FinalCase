package com.n11.userservice.general.base;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Created By Mustafa Aykurt
 * Date:24.02.2024
 * Time:21:25
 */

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Embedded
    private BaseAdditionalFields baseAdditionalFields;
}
