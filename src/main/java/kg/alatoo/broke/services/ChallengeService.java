package kg.alatoo.broke.services;

import kg.alatoo.broke.dto.ChallengeDTO;
import kg.alatoo.broke.entities.Challenge;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.entities.UserChallenge;
import kg.alatoo.broke.repositories.ChallengeRepository;
import kg.alatoo.broke.repositories.UserChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserChallengeRepository userChallengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository,
                            UserChallengeRepository userChallengeRepository) {
        this.challengeRepository = challengeRepository;
        this.userChallengeRepository = userChallengeRepository;
    }

    public Challenge save(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public List<Challenge> getAll() {
        return challengeRepository.findAll();
    }

    public List<Challenge> getChallengesByUser(User user) {
        return challengeRepository.findByUser(user);
    }

    public Challenge findById(Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + id));
    }


}
