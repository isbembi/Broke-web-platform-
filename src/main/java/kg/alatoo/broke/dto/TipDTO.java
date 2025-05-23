package kg.alatoo.broke.dto;

public class TipDTO {
    private String text;

    public TipDTO() {}

    public TipDTO(String text) {
        this.text = text;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
