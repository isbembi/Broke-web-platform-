package kg.alatoo.broke.repositories;

import kg.alatoo.broke.entities.UserChallenge;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    List<UserChallenge> findByUser(User user);
    Optional<UserChallenge> findByUserAndChallenge(User user, Challenge challenge);
}
