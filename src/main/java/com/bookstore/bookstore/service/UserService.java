package com.bookstore.bookstore.service;

import com.bookstore.bookstore.dto.UserDTO;
import com.bookstore.bookstore.model.User;
import com.bookstore.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO dto) {
        User user = new User(dto.getUsername(), dto.getEmail());
        user = userRepository.save(user);

        return new UserDTO(user.getUsername(), user.getEmail());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
