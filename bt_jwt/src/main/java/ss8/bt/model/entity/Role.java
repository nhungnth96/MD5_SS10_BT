package ss8.bt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
