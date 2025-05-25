package kg.alatoo.broke.entities;

import jakarta.persistence.*;
import kg.alatoo.broke.enums.Status;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_challenges")
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    public UserChallenge() {}

    public UserChallenge(User user, Challenge challenge, Status status,
                         LocalDateTime startedAt, LocalDateTime completedAt) {
        this.user = user;
        this.challenge = challenge;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Challenge getChallenge() { return challenge; }
    public void setChallenge(Challenge challenge) { this.challenge = challenge; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}
