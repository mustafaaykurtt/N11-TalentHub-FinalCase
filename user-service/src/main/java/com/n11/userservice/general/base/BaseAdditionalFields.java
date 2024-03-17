package com.n11.userservice.general.base;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created By Mustafa Aykurt
 * Date:24.02.2024
 * Time:21:25
 */

@Getter
@Setter
@Embeddable
public class BaseAdditionalFields {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

