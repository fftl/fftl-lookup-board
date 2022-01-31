package fftl.lookupBoard.request;

import fftl.lookupBoard.entity.Board;
import fftl.lookupBoard.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveBoardRequest {

    private String title;
    private String content;
    private LocalDateTime regdate;
    private Long user_id;
    private User user;

    public Board toEntity(){
        return Board.builder()
            .title(title)
            .content(content)
            .regdate(regdate)
            .searchCnt(0)
            .deleteYn(false)
            .user(user)
            .build();
    }

    @Override
    public String toString() {
        return "SaveBoardRequest{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", regdate=" + regdate +
            ", user_id=" + user_id +
            ", user=" + user +
            '}';
    }
}
