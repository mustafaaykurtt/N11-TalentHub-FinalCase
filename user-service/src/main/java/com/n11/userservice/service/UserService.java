package com.n11.userservice.service;

import com.n11.userservice.client.RestaurantServiceClient;
import com.n11.userservice.dto.client.RestaurantDto;
import com.n11.userservice.dto.request.UserSaveRequest;
import com.n11.userservice.dto.request.UserUpdateRequest;
import com.n11.userservice.dto.response.UserDto;
import com.n11.userservice.exception.NotFoundException;
import com.n11.userservice.general.GenericRestResponse;
import com.n11.userservice.general.base.BaseAdditionalFields;
import com.n11.userservice.model.User;
import com.n11.userservice.repository.UserRepository;
import com.n11.userservice.util.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Mustafa Aykurt
 * Date:28.02.2024
 * Time:23:11
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RestaurantServiceClient restaurantServiceClient;
    private final static Integer DISTANCE_TO_THE_USER=10;

    public UserService(UserRepository userRepository, UserMapper userMapper, RestaurantServiceClient restaurantServiceClient) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.restaurantServiceClient = restaurantServiceClient;
    }

    public void saveUser(UserSaveRequest request) {
        var user = userMapper.convertToUser(request);
        var baseAdditionalFields = new BaseAdditionalFields();
        baseAdditionalFields.setUpdateDate(LocalDateTime.now());
        baseAdditionalFields.setCreateDate(LocalDateTime.now());
        user.setBaseAdditionalFields(baseAdditionalFields);
        userRepository.save(user);
    }

    public UserDto updateUser(UserUpdateRequest request) {
        var userDB = getUserById(request.id());
        userMapper.updateUserFromDTO(request,userDB);
        userDB.getBaseAdditionalFields().setUpdateDate(LocalDateTime.now());
        return userMapper.convertToUserDTO(userRepository.save(userDB));
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertToUserDTO)
                .collect(Collectors.toList());
    }

    private User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public UserDto getUserByIdWithUserDto(long id) {
        var user = getUserById(id);
        return userMapper.convertToUserDTO(user);
    }

    public List<UserDto> restaurantRecommendation(Long id) {
        var userInDB = getUserById(id);

        var restaurantDto =restaurantServiceClient.findRestaurantsNearUser(
                userInDB.getLatitude(), userInDB.getLongitude(), DISTANCE_TO_THE_USER)
                .getBody()
                .getPayload();


        return null;
    }

    public void deleteUser(Long id) {
        var userDB = getUserById(id);
        userRepository.delete(userDB);
    }

}
