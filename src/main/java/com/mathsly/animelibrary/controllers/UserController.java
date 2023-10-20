package com.mathsly.animelibrary.controllers;

import com.mathsly.animelibrary.domain.dto.UserDto;
import com.mathsly.animelibrary.domain.entities.User;
import com.mathsly.animelibrary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> list = userService.getAll();
        List<UserDto> listDto = list.stream().map(UserDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        User user = userService.getOne(id);
        return ResponseEntity.ok().body(new UserDto(user));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> insert(@RequestBody UserDto userDto) {
        User user = userDto.toEntity();
        Long idUser = userService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(idUser);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDto userDto, @PathVariable Long id){
        User user = userDto.toEntity();
        userService.update(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
