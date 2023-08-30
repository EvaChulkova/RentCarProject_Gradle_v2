package jane.rentcarproject_gradle.database.entity;

import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NamedEntityGraph(
        name = "withClient",
        attributeNodes = {
                @NamedAttributeNode("client")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "client")
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User implements BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Client client;
}
