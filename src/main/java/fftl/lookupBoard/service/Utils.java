package fftl.lookupBoard.service;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.entity.User;
import fftl.lookupBoard.response.BoardResponse;
import fftl.lookupBoard.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Utils {

    public UserResponse convertUserResponse(User user){
        return UserResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .boardResponses(convertBoardsResponses(user.getBoards()))
            .build();
    }

    public List<BoardResponse> convertBoardsResponses(List<Board> boards){

        if(boards == null){
            return null;
        }

        List<BoardResponse> boardResponses = new ArrayList<>();
        for( Board board : boards ){
            if(!board.isDeleteYn()){
                boardResponses.add(BoardResponse.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .regdate(board.getRegdate())
                    .username(board.getUser().getUsername())
                    .searchCnt(board.getSearchCnt())
                    .id(board.getId())
                    .build());
            }
        }
        return boardResponses;
    }
}
