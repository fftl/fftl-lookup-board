package fftl.lookupBoard.repository;

import fftl.lookupBoard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
