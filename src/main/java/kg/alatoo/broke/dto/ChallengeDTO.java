package kg.alatoo.broke.dto;

public class ChallengeDTO {
    private String title;
    private String description;
    private int rewardPoints;

    public ChallengeDTO() {}

    public ChallengeDTO(String title, String description, int rewardPoints) {
        this.title = title;
        this.description = description;
        this.rewardPoints = rewardPoints;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getRewardPoints() { return rewardPoints; }
    public void setRewardPoints(int rewardPoints) { this.rewardPoints = rewardPoints; }
}
