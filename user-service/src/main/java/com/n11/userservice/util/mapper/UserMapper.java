package com.n11.userservice.util.mapper;

import com.n11.userservice.dto.request.UserSaveRequest;
import com.n11.userservice.dto.request.UserUpdateRequest;
import com.n11.userservice.dto.response.UserDto;
import com.n11.userservice.model.User;
import org.mapstruct.*;

/**
 * Created By Mustafa Aykurt
 * Date:28.02.2024
 * Time:23:46
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User convertToUser(UserSaveRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "id", ignore = true)
    void updateUserFromDTO(UserUpdateRequest request, @MappingTarget User user);


    UserDto convertToUserDTO(User user);
}
