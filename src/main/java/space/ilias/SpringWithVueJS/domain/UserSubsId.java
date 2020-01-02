package space.ilias.SpringWithVueJS.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubsId implements Serializable {
    @JsonView(Views.Id.class)
    private String channelId;

    @JsonView(Views.Id.class)
    private String subscriberId;

}
