package space.ilias.SpringWithVueJS.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name"})
@SuppressWarnings("all")
public class User implements Serializable, PrincipalExtractor {

    @Id
    @JsonView(Views.IdName.class)
    private String id;

    @JsonView(Views.IdName.class)
    private String name;

    @JsonView(Views.IdName.class)
    private String userpic;

    private String email;

    @JsonView(Views.FullProfile.class)
    private String gender;

    @JsonView(Views.FullProfile.class)
    private String locale;

    @JsonView(Views.FullProfile.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastVisit;

    @JsonView(Views.FullProfile.class)
    @OneToMany(mappedBy = "subscriberId", orphanRemoval = true)
    private Set<UserSubs> subscription = new HashSet<>();

    @JsonView(Views.FullProfile.class)
    @OneToMany(mappedBy = "channelId", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<UserSubs> subscribers = new HashSet<>();

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        return null;
    }
}
