package ss8.bt.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @Column (unique = true)
    private String email;

    @NotNull
    @Size(max = 60)
    @JsonIgnore
    private String password; // Bcrypt max length = 60

    @ManyToMany (fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
