package fftl.lookupBoard.service;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.repository.BoardRepository;
import fftl.lookupBoard.repository.UserRepository;
import fftl.lookupBoard.request.SaveBoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Board save(SaveBoardRequest saveBoardRequest){
        User user = userRepository.findById(saveBoardRequest.getUser_id()).orElse(null);
        saveBoardRequest.setUser(user);

        return boardRepository.save(saveBoardRequest.toEntity());
    }

}
