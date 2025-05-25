package kg.alatoo.broke.services;

import kg.alatoo.broke.entities.Challenge;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.entities.UserChallenge;
import kg.alatoo.broke.enums.Status;
import kg.alatoo.broke.repositories.UserChallengeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserChallengeService {

    private final UserChallengeRepository userChallengeRepository;

    public UserChallengeService(UserChallengeRepository userChallengeRepository) {
        this.userChallengeRepository = userChallengeRepository;
    }

    public UserChallenge startChallenge(User user, Challenge challenge) {
        // проверка: если уже начат
        Optional<UserChallenge> existing = userChallengeRepository.findByUserAndChallenge(user, challenge);
        if (existing.isPresent()) {
            return existing.get();
        }

        UserChallenge uc = new UserChallenge();
        uc.setUser(user);
        uc.setChallenge(challenge);
        uc.setStatus(Status.ACTIVE);
        uc.setStartedAt(LocalDateTime.now());
        return userChallengeRepository.save(uc);
    }

    public UserChallenge completeChallenge(User user, Challenge challenge) {
        UserChallenge uc = userChallengeRepository.findByUserAndChallenge(user, challenge)
                .orElseThrow(() -> new RuntimeException("Challenge not found or not started"));

        uc.setStatus(Status.COMPLETED);
        uc.setCompletedAt(LocalDateTime.now());
        return userChallengeRepository.save(uc);
    }

    public Optional<UserChallenge> getActiveChallengeByUser(User user) {
        return userChallengeRepository.findByUser(user).stream()
                .filter(c -> c.getStatus() == Status.ACTIVE)
                .findFirst();
    }

    public List<UserChallenge> getAllByUser(User user) {
        return userChallengeRepository.findByUser(user);
    }
}
