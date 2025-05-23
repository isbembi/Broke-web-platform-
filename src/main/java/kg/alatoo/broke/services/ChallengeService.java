package kg.alatoo.broke.services;

import kg.alatoo.broke.dto.ChallengeDTO;
import kg.alatoo.broke.entities.Challenge;
import kg.alatoo.broke.repositories.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public Challenge add(ChallengeDTO dto) {
        Challenge challenge = new Challenge();
        challenge.setTitle(dto.getTitle());
        challenge.setDescription(dto.getDescription());
        challenge.setRewardPoints(dto.getRewardPoints());
        return challengeRepository.save(challenge);
    }

    public List<Challenge> getAll() {
        return challengeRepository.findAll();
    }
}
