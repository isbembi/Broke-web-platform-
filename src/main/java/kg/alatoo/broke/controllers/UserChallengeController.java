package kg.alatoo.broke.controllers;

import kg.alatoo.broke.entities.Challenge;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.entities.UserChallenge;
import kg.alatoo.broke.repositories.ChallengeRepository;
import kg.alatoo.broke.security.JwtService;
import kg.alatoo.broke.services.ChallengeService;
import kg.alatoo.broke.services.UserChallengeService;
import kg.alatoo.broke.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/debug")
public class UserChallengeController {

    private final UserChallengeService userChallengeService;
    private final ChallengeRepository challengeRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final ChallengeService challengeService;

    public UserChallengeController(UserChallengeService userChallengeService,
                                   ChallengeRepository challengeRepository,
                                   UserService userService,
                                   JwtService jwtService,
                                   ChallengeService challengeService) {
        this.userChallengeService = userChallengeService;
        this.challengeRepository = challengeRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.challengeService = challengeService;
    }

    @PostMapping("/start/{id}")
    public ResponseEntity<?> startChallenge(@PathVariable Long id,
                                            @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();
        Challenge challenge = challengeRepository.findById(id).orElseThrow();

        UserChallenge started = userChallengeService.startChallenge(user, challenge);
        return ResponseEntity.ok(started);
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<?> completeChallenge(@PathVariable Long id,
                                               @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();
        Challenge challenge = challengeRepository.findById(id).orElseThrow();

        UserChallenge completed = userChallengeService.completeChallenge(user, challenge);
        return ResponseEntity.ok(completed);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveChallenge(@RequestHeader("Authorization") String authHeader) {
        System.out.println("🔥🔥🔥 /api/user/challenges/active CALLED");

        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();

        Optional<UserChallenge> active = userChallengeService.getActiveChallengeByUser(user);
        return ResponseEntity.of(active);
    }

    @PostMapping("/challenges/start")
    public String startChallenge(@RequestParam Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();
        Challenge challenge = challengeService.findById(id);
        userChallengeService.startChallenge(user, challenge);
        return "redirect:/dashboard";
    }


}
