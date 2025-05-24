package kg.alatoo.broke.controllers;

import kg.alatoo.broke.dto.ChallengeDTO;
import kg.alatoo.broke.entities.Challenge;
import kg.alatoo.broke.services.ChallengeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping
    public Challenge addChallenge(@RequestBody ChallengeDTO dto) {
        return challengeService.add(dto);
    }

    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeService.getAll();
    }
}
