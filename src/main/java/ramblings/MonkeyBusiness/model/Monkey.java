package ramblings.MonkeyBusiness.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Monkey {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    private Rank rank;
    private Status status;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @OneToOne(
            mappedBy = "monkey",
            cascade = CascadeType.ALL
    )
    private Profile profile;
    @ManyToOne
    @JoinColumn(
            name = "troop_id"
    )
    @JsonBackReference
    private Troop troop;


    public Monkey(String name, Rank rank, Status status) {
        this.name = name;
        this.rank = rank;
        this.status = status;
    }

    public Monkey() {}

    public Integer getId() {
        return id;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }
}
