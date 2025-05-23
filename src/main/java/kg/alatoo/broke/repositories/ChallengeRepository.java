package kg.alatoo.broke.repositories;

import kg.alatoo.broke.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
