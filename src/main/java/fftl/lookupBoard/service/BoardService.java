package fftl.lookupBoard.service;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.repository.BoardRepository;
import fftl.lookupBoard.repository.UserRepository;
import fftl.lookupBoard.request.SaveBoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
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

    @Transactional //조회수 증가 기능 때문에 읽기전용이 아니게됩니다.
    public Board findById(Long id){
        Board board = boardRepository.findById(id).orElse(null);
        if(board == null){
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }
        board.searchCntUp();
        return board;
    }

    public List<Board> findAll(){
        List<Board> boards = boardRepository.findAll();
        if(boards == null){
            throw new RuntimeException("작성된 게시글이 하나도 없습니다.");
        }

        return boards;
    }
}
