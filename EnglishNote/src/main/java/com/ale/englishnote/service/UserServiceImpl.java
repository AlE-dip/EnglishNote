package com.ale.englishnote.service;

import com.ale.englishnote.entity.User;
import com.ale.englishnote.repository.UserRepository;
import com.ale.englishnote.security.UserInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserInfo(user);
    }

    public UserDetails loadUserById(Long id) {
        UserInfo userInfo = new UserInfo();
        userRepository.findById(id).ifPresent(userInfo::setUser);
        if(userInfo.getUser() != null){
            return userInfo;
        }
        return null;
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
