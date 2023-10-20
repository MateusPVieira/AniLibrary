package com.mathsly.animelibrary.services;

import com.mathsly.animelibrary.domain.entities.Title;
import com.mathsly.animelibrary.domain.entities.User;
import com.mathsly.animelibrary.repositories.UserSQLiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserSQLiteRepository userRepository;
    @Autowired
    public UserService(UserSQLiteRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getOne(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }
    public List<User> getAll(){ return userRepository.findAll(); }
    public long save(User user){
        return userRepository.saveAndFlush(user).getId();
    }


    public Optional<Long> update(User user, Long id){
        user.setId(id);
        return userRepository.findById(id).map(
                userToUpdate ->
                {
                    userRepository.flush();
                    userToUpdate.setId(id);
                    userToUpdate.setLibrary(user.getLibrary());
                    return save(userToUpdate);
                });
    }

    public void delete (Long id){
        userRepository.deleteById(id);
    }

}