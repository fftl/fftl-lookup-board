package fftl.lookupBoard.repository;

import fftl.lookupBoard.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
