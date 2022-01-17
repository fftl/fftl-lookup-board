package fftl.lookupBoard.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
