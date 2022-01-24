package fftl.lookupBoard.controller;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.request.SaveBoardRequest;
import fftl.lookupBoard.response.Response;
import fftl.lookupBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{board_id}")
    public Response findById(@PathVariable Long board_id){
        Board board = boardService.findById(board_id);

        return new Response(true, null, board);
    }

    @GetMapping
    public Response findAll(){
        List<Board> boards = boardService.findAll();

        return new Response(true, null, boards);
    }
}
