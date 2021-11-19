package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;

import javax.validation.Valid;

@RestController()
@RequestMapping("/health")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }


}
