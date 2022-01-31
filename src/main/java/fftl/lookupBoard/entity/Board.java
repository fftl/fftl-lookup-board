package fftl.lookupBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "Boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boards_id")
    private Long id;

    @Column(name = "boards_title")
    private String title;

    @Column(name = "boards_content")
    private String content;

    @Column(name = "boards_regdate")
    private LocalDateTime regdate;

    @Column(name = "boards_search_cnt")
    private int searchCnt;

    @Column(name = "boards_delete_yn")
    @GeneratedValue
    private boolean deleteYn;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public void searchCntUp(){
        this.searchCnt += 1;
    }

    public void deleteY(){
        this.deleteYn = true;
    }

    @Override
    public String toString() {
        return "Board{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", regdate=" + regdate +
            ", searchCnt=" + searchCnt +
            ", deleteYn=" + deleteYn +
            ", user=" + user +
            '}';
    }
}

