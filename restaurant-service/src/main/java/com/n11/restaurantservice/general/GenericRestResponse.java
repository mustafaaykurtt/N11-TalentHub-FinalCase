package com.n11.restaurantservice.general;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created By Mustafa Aykurt
 * Date:26.02.2024
 * Time:17:52
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRestResponse<T> {
    private T payload;
    private LocalDateTime responseDate;
    private boolean isSuccess;
    private String message;

    private GenericRestResponse(T payload, boolean isSuccess, String message) {
        this.payload = payload;
        this.isSuccess = isSuccess;
        this.responseDate = LocalDateTime.now();
        this.message = message;
    }

    private GenericRestResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.responseDate = LocalDateTime.now();
        this.message = message;
    }


    public static <T>GenericRestResponse<T> of(T t, String message) {
        return new GenericRestResponse<>(t,true, message);
    }

    public static <T>GenericRestResponse<T> of(String message) {
        return new GenericRestResponse<>(true, message);
    }

    public static <T>GenericRestResponse<T> error(T t, String message) {
        return new GenericRestResponse<>(t,false,message);
    }

    public static <T>GenericRestResponse<T> empty(String message) {
        return new GenericRestResponse<>(null,true, message);
    }

}
