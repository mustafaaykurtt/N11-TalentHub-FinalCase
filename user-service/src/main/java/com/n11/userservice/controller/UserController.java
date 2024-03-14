package com.n11.userservice.controller;

import com.n11.userservice.dto.request.UserSaveRequest;
import com.n11.userservice.dto.request.UserUpdateRequest;
import com.n11.userservice.dto.response.UserDto;
import com.n11.userservice.general.GenericRestResponse;
import com.n11.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<GenericRestResponse> createUser(@Valid @RequestBody UserSaveRequest request) {
        userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericRestResponse.of("n11.create.success"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericRestResponse<UserDto>> updateUser(@Valid @PathVariable Long id,
                                                                   @RequestBody UserUpdateRequest request) {
        var userDto = userService.updateUser(request);
        return ResponseEntity.status(200).body(GenericRestResponse.of(userDto, "n11.update.user.success"));
    }

    //password için yaz 0lama ve değiştirme için
    //activate için yaz


    @GetMapping
    public ResponseEntity<GenericRestResponse<List<UserDto>>> getAllUsers() {
        var userDto = userService.getAllUsers();
        return ResponseEntity.status(200).body(GenericRestResponse.of(userDto,"success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericRestResponse<UserDto>> getUserById(@PathVariable Long id) {
        var userDto = userService.getUserByIdWithUserDto(id);
        return ResponseEntity.status(200).body(GenericRestResponse.of(userDto,"Success"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<GenericRestResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(GenericRestResponse.of("User is deleted"));
    }

}