package com.restapiforlibrary.library.controller;

import com.restapiforlibrary.library.domain.User;
import com.restapiforlibrary.library.domain.UserDto;
import com.restapiforlibrary.library.mapper.UserMapper;
import com.restapiforlibrary.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("getUser")
    public UserDto getUser(@RequestParam Long userId) throws NotFoundException {
        return userMapper.mapToUserDto(userService.getUser(userId).orElseThrow(NotFoundException::new));
    }

    @GetMapping("getUsers")
    public List<UserDto> getUsers() {
        List<User> users = userService.getUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
    }

    @PutMapping(value = "editUser")
    public UserDto edituser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userService.saveUser(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
    }

}
