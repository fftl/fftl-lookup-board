package fftl.lookupBoard.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class BoardResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime regdate;
    private int searchCnt;
    private String username;

}
