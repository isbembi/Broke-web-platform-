package kg.alatoo.broke.dto;

import java.math.BigDecimal;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private BigDecimal initialAmount;

    public UserDTO(String username, String email, String password, BigDecimal initialAmount) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.initialAmount = initialAmount;
    }

    public UserDTO() {
    }

    public BigDecimal getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
