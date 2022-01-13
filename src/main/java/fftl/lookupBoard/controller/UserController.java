package fftl.lookupBoard.controller;

import fftl.lookupBoard.entitiy.User;
import fftl.lookupBoard.response.Response;
import fftl.lookupBoard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response save(@RequestParam(value = "username") String username){
        User user = userService.save(username);
        return new Response(true, null, user);
    }

}
