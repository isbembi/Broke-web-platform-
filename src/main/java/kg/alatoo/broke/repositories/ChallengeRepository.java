package kg.alatoo.broke.repositories;

import kg.alatoo.broke.entities.Challenge;
import kg.alatoo.broke.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByUser(User user);

}
