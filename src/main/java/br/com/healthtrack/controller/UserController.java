package br.com.healthtrack.controller;

import br.com.healthtrack.dto.UserLoginRequestDto;
import br.com.healthtrack.dto.UserLoginResponseDto;
import br.com.healthtrack.dto.UserResponseDto;
import br.com.healthtrack.entity.User;
import br.com.healthtrack.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(UserResponseDto.transformInDto(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") int id) throws NotFoundException {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado para esse id: " + id));

        return new ResponseEntity<>(UserResponseDto.transformInDto(foundUser), HttpStatus.OK);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") int id, @Valid @RequestBody User user) throws NotFoundException {
        User userToBeUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado para esse id: " + id));

        userToBeUpdate.setName(user.getName());
        userToBeUpdate.setEmail(user.getEmail());
        userToBeUpdate.setAge(user.getAge());
        userToBeUpdate.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(userToBeUpdate);
        return new ResponseEntity<>(UserResponseDto.transformInDto(userToBeUpdate), HttpStatus.OK);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") int id) throws NotFoundException {
        User userToBeDeleted = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Usuario não encontrado para esse id: " + id));

        userRepository.delete(userToBeDeleted);
    }

    @PostMapping("user/login")
    public ResponseEntity<UserLoginResponseDto> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        User userFromDB = userRepository.findByEmail(userLoginRequestDto.getEmail());

        if (encoder.matches(userFromDB.getPassword(), userLoginRequestDto.getPassword())) {

            return new ResponseEntity<>(UserLoginResponseDto.transformInDto(User.fromDto(userLoginRequestDto)), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
