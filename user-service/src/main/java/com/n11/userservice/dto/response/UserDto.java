package com.n11.userservice.dto.response;



/**
 * Created By Mustafa Aykurt
 * Date:9.03.2024
 * Time:22:37
 */

public record UserDto(

        Long id,

        String name,

        String surname,

        String email,

        Double latitude,

        Double longitude

) {
}
