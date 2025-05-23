package kg.alatoo.broke.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private int rewardPoints;

    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Challenge() {}

    public Challenge(String title, String description, int rewardPoints, boolean isDone, User user) {
        this.title = title;
        this.description = description;
        this.rewardPoints = rewardPoints;
        this.isDone = isDone;
        this.user =user;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getRewardPoints() { return rewardPoints; }

    public void setRewardPoints(int rewardPoints) { this.rewardPoints = rewardPoints; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user;}

}
