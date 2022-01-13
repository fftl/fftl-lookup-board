package fftl.lookupBoard.service;

import fftl.lookupBoard.entitiy.User;
import fftl.lookupBoard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(String username){
        User user = User.builder().username(username).build();

        return userRepository.save(user);
    }

    @Transactional
    public User findById(Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new RuntimeException("올바르지 않은 id 입니다.");
        }

        return user;
    }

}
