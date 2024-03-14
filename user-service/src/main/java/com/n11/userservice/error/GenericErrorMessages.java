package com.n11.userservice.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created By Mustafa Aykurt
 * Date:26.02.2024
 * Time:19:00
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class GenericErrorMessages {
    private String path;
    private Map<String, String> validationErrors;

}
