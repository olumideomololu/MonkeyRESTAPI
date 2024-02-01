package ramblings.MonkeyBusiness.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Troop {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    @NotBlank
    private String name;
    @OneToMany(
            mappedBy = "troop"
    )
    @JsonManagedReference
    private List<Monkey> monkeys;

    public Troop(String name) {
        this.name = name;
    }

    public Troop() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //??
    public List<Monkey> getMonkeys() {
        return monkeys;
    }

    public void setMonkeys(List<Monkey> monkeys) {
        this.monkeys = monkeys;
    }
}