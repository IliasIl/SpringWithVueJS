package space.ilias.SpringWithVueJS.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "usr")
@Data
public class User implements Serializable, PrincipalExtractor {

    @Id
    @JsonView(Views.IdName.class)
    private String id;

    @JsonView(Views.IdName.class)
    private String name;

    @JsonView(Views.IdName.class)
    private String userpic;

    private String email;

    private String gender;

    private String locale;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastVisit;


    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        return null;
    }
}
