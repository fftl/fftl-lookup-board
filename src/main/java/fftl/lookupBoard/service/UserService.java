package fftl.lookupBoard.service;

import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.repository.UserRepository;
import fftl.lookupBoard.response.UserResponse;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Utils utils;

    @Transactional
    public UserResponse save(String username){
        User user = userRepository.save(User.builder().username(username).build());

        return utils.convertUserResponse(user);
    }

    public UserResponse findById(Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new RuntimeException("올바르지 않은 id 입니다.");
        }

        return utils.convertUserResponse(user);
    }

    public List<UserResponse> findAll(){
        List<User> users = userRepository.findAll();

        if(users == null) {
            throw new RuntimeException("올바르지 않은 id 입니다.");
        }

        List<UserResponse> userResponses = new ArrayList<>();

        for(User user : users){
            userResponses.add(UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .boardResponses(utils.convertBoardsResponses(user.getBoards()))
                .build());
        }

        return userResponses;
    }


}
