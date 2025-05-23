package kg.alatoo.broke.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tips")
public class Tip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public Tip() {}

    public Tip(String text) {
        this.text = text;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }
}
