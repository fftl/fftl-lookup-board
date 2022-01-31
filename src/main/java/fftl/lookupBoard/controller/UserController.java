package fftl.lookupBoard.controller;

import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.response.Response;
import fftl.lookupBoard.response.UserResponse;
import fftl.lookupBoard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response save(@RequestParam(value = "username") String username){
        return new Response(true, null, userService.save(username));
    }

    @GetMapping("/{user_id}")
    public Response findById(@PathVariable Long user_id){
        return new Response(true, null, userService.findById(user_id));
    }

    @GetMapping
    public Response findAll(){
        return new Response(true, null, userService.findAll());
    }

}
