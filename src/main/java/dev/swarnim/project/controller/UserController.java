package dev.swarnim.project.controller;

import dev.swarnim.project.model.Customer;
import dev.swarnim.project.model.request.SessionRequest;
import dev.swarnim.project.model.response.Session;
import dev.swarnim.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserController extends V1BaseController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/session/create")
    private Session createSession(@Valid @RequestBody SessionRequest sessionRequest){
        log.info("sessionRequest {}", sessionRequest);
        return userService.createSession(sessionRequest);
    }

    @PostMapping("/signup")
    private void createUser(@Valid @RequestBody Customer customer){
        userService.createUser(customer);
    }
}
