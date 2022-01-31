package fftl.lookupBoard.service;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.repository.BoardRepository;
import fftl.lookupBoard.repository.UserRepository;
import fftl.lookupBoard.request.SaveBoardRequest;
import fftl.lookupBoard.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final Utils utils;

    /**
     * 게시글 저장하기
     * */
    @Transactional
    public BoardResponse save(SaveBoardRequest saveBoardRequest){
        User user = userRepository.findById(saveBoardRequest.getUser_id()).orElse(null);
        saveBoardRequest.setUser(user);
        Board board = boardRepository.save(saveBoardRequest.toEntity());

        BoardResponse boardResponse = BoardResponse.builder()
        .title(board.getTitle())
        .content(board.getContent())
        .regdate(board.getRegdate())
        .username(board.getUser().getUsername())
        .searchCnt(board.getSearchCnt())
        .id(board.getId())
        .build();

        return boardResponse;
    }

    /**
     * 게시글 아이디로 게시글 검색
     * */
    @Transactional //조회수 증가 기능 때문에 읽기전용이 아니어야 합니다.
    public BoardResponse findById(Long id){
        Board board = boardRepository.findById(id).orElse(null);
        if(board == null || board.isDeleteYn()){
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }

        board.searchCntUp();

        BoardResponse boardResponse = BoardResponse.builder()
            .title(board.getTitle())
            .content(board.getContent())
            .regdate(board.getRegdate())
            .username(board.getUser().getUsername())
            .searchCnt(board.getSearchCnt())
            .id(board.getId())
            .build();

        return boardResponse;
    }

    /**
     * 모든 게시글 검색
     * */
    public List<BoardResponse> findAll(){
        List<Board> boards = boardRepository.findAll();
        if(boards == null){
            throw new RuntimeException("작성된 게시글이 하나도 없습니다.");
        }
        return utils.convertBoardsResponses(boards);
    }

    /**
     * 제목으로 게시글 검색
     * */
    public List<BoardResponse> findByTitle(String title){
        List<Board> boards = boardRepository.findByTitleContains(title);
        if(boards == null){
            throw new RuntimeException("조건에 맞는 게시글을 찾을 수 없습니다.");
        }
        return utils.convertBoardsResponses(boards);
    }

    /**
     * 내용으로 게시글 검색
     * */
    public List<BoardResponse> findByContent(String content){
        List<Board> boards = boardRepository.findByContentContains(content);
        if(boards == null){
            throw new RuntimeException("조건에 맞는 게시글을 찾을 수 없습니다.");
        }
        return utils.convertBoardsResponses(boards);
    }

    /**
     * 제목+내용으로 게시글 검색
     * */
    public List<BoardResponse> findByTitleContent(String titleContent){

        List<Board> searchTitle = boardRepository.findByTitleContains(titleContent);
        List<Board> searchContent = boardRepository.findByContentContains(titleContent);

        List<Board> mergeBoards = new ArrayList<>(searchTitle);

        mergeBoards.removeAll(searchContent);
        mergeBoards.addAll(searchContent);

        if(mergeBoards == null){
            throw new RuntimeException("조건에 맞는 게시글을 찾을 수 없습니다.");
        }

        return utils.convertBoardsResponses(mergeBoards);
    }

    /**
     * 유저이름으로 게시글 검색
     * */
    public List<BoardResponse> findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if( user == null ){
            throw new RuntimeException("조건에 맞는 게시글을 찾을 수 없습니다.");
        }

        return utils.convertBoardsResponses(user.getBoards());
    }

    /**
     * 날짜로 게시글 검색
     * */
    public List<BoardResponse> findByDates(LocalDateTime startDate, LocalDateTime endDate){

        if(startDate == null){
            startDate = LocalDateTime.parse("0001-01-01T00:00:00");
        }
        if(endDate == null){
            endDate = LocalDateTime.parse("9999-12-31T23:59:59");
        }

        List<Board> boards = boardRepository.findByRegdateBetween(startDate, endDate);
        if(boards == null){
            throw new RuntimeException("조건에 맞는 게시글을 찾을 수 없습니다.");
        }

        return utils.convertBoardsResponses(boards);
    }

    /**
     * 게시글 삭제 처리
     * */
    @Transactional
    public Board delete(Long id){
        Board board = boardRepository.findById(id).orElse(null);
        if(board == null){
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다.");
        }

        board.deleteY();

        return board;
    }

}
