package kg.alatoo.broke.controllers;

import kg.alatoo.broke.dto.ChallengeDTO;
import kg.alatoo.broke.entities.Challenge;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.security.JwtService;
import kg.alatoo.broke.services.ChallengeService;
import kg.alatoo.broke.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;
    private final JwtService jwtService;
    private final UserService userService;

    public ChallengeController(ChallengeService challengeService,
                               JwtService jwtService,
                               UserService userService) {
        this.challengeService = challengeService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Challenge> addChallenge(@RequestHeader("Authorization") String authHeader,
                                                  @RequestBody ChallengeDTO dto) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();

        Challenge challenge = new Challenge();
        challenge.setTitle(dto.getTitle());
        challenge.setDescription(dto.getDescription());
        challenge.setRewardPoints(dto.getRewardPoints());
        challenge.setUser(user); // 👈 присваиваем создателя

        Challenge saved = challengeService.save(challenge);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeService.getAll();
    }

    @GetMapping("/user")
    public List<Challenge> getChallengesByUser(@RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();
        return challengeService.getChallengesByUser(user);
    }


}
