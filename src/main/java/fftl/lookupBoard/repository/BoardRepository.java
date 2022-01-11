package fftl.lookupBoard.repository;

import fftl.lookupBoard.entitiy.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Long, Board> {
}
