package fftl.lookupBoard.controller;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.request.SaveBoardRequest;
import fftl.lookupBoard.response.BoardResponse;
import fftl.lookupBoard.response.Response;
import fftl.lookupBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    /**
     * id로 조회
     * */
    @GetMapping("/id/{board_id}")
    public Response findById(@PathVariable Long board_id){
        return new Response(true, null, boardService.findById(board_id));
    }

    /**
     * 전부 조회
     * */
    @GetMapping
    public Response findAll(){
        return new Response(true, null, boardService.findAll());
    }

    /**
     * 제목으로 조회
     * */
    @GetMapping("/title/{title}")
    public Response findByTitle(@PathVariable String title){
        return new Response(true, null, boardService.findByTitle(title));
    }

    /**
     * 내용으로 조회
     * */
    @GetMapping("/content/{content}")
    public Response findByContent(@PathVariable String content){
        return new Response(true, null, boardService.findByContent(content));
    }

    /**
     * 제목과 내용으로 조회
     * */
    @GetMapping("/titleContent/{titleContent}")
    public Response findByTitleContent(@PathVariable String titleContent){
        return new Response(true, null, boardService.findByTitleContent(titleContent));
    }

    /**
     * 날짜로 조회
     * */
    @GetMapping("/regdate")
    public Response findByDates(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate){
        return new Response(true, null, boardService.findByDates(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate)));
    }

    @DeleteMapping("/{board_id}")
    public Response delete(@PathVariable Long board_id){
        Board board = boardService.delete(board_id);

        return new Response(true, null, board);
    }
}
