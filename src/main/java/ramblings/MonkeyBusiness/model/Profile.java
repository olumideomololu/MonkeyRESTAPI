package ramblings.MonkeyBusiness.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Profile {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String bio;
    @OneToOne
    @JoinColumn(
            name = "monkey_id"
    )
    private Monkey monkey;

    public Profile(String bio) {
        this.bio = bio;
    }

    public Profile() {}

    public Integer getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
