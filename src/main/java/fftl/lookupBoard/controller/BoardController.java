package fftl.lookupBoard.controller;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.request.SaveBoardRequest;
import fftl.lookupBoard.response.Response;
import fftl.lookupBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {

    public final BoardService boardService;

    @PostMapping
    public Response save(@RequestBody SaveBoardRequest saveBoardRequest){
        Board board = boardService.save(saveBoardRequest);

        return new Response(true, null, board);
    }
}
