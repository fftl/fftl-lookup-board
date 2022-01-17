package fftl.lookupBoard.controller;

import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.response.Response;
import fftl.lookupBoard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{user_id}")
    public Response findById(@PathVariable Long user_id){
        User user = userService.findById(user_id);
        return new Response(true, null, user);
    }

    @GetMapping()
    public Response findAll(){
        List<User> users = userService.findAll();
        return new Response(true, null, users);
    }

}
