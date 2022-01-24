package fftl.lookupBoard.service;

import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(String username){
        User user = User.builder().username(username).build();

        return userRepository.save(user);
    }

    public User findById(Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new RuntimeException("올바르지 않은 id 입니다.");
        }

        return user;
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();

        return users;
    }
}
