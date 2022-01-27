package fftl.lookupBoard.repository;

import fftl.lookupBoard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContains(String title);

    List<Board> findByContentContains(String title);

    List<Board> findByRegdateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
