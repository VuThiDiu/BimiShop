package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw  new UsernameNotFoundException(username);
        }else return new CustomUserDetails(user);
    }

    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public UserDetails loadUserById(Long userId) {
        User user = userRepository.getById(userId);
        return new CustomUserDetails(user);
    }
    public User createNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public User getUserById(String id){
        User existedUser = userRepository.findUserById(id).orElseThrow(
                ()-> new ResourceNotFoundException("not found", "id", id)
        );
        return existedUser;
    }
}
