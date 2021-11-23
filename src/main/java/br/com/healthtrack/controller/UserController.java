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
import java.util.Optional;

@RestController()
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer userId) throws NotFoundException {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado para esse id: " + userId));

        return new ResponseEntity<>(UserResponseDto.transformInDto(foundUser), HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Integer userId, @Valid @RequestBody User user) throws NotFoundException {
        User userToBeUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado para esse id: " + userId));

        userToBeUpdate.setName(user.getName());
        userToBeUpdate.setEmail(user.getEmail());
        userToBeUpdate.setAge(user.getAge());
        userToBeUpdate.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(userToBeUpdate);
        return new ResponseEntity<>(UserResponseDto.transformInDto(userToBeUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") int id) throws NotFoundException {
        User userToBeDeleted = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Usuario não encontrado para esse id: " + id));

        userRepository.delete(userToBeDeleted);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserLoginResponseDto> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {

        if (userRepository.findByEmail(userLoginRequestDto.getEmail()).isPresent()) {
            User userFromDB = userRepository.findByEmail(userLoginRequestDto.getEmail()).get();
            if (encoder.matches(userLoginRequestDto.getPassword(), userFromDB.getPassword())) {

                return new ResponseEntity<>(UserLoginResponseDto.transformInDto(userFromDB), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }


}
