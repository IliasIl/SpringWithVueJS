package space.ilias.SpringWithVueJS.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@NoArgsConstructor
public class UserSubs {

    @EmbeddedId
    @JsonIgnore
    private UserSubsId id;

    @JsonView(Views.IdName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class)
    @MapsId("channelId")
    @ManyToOne
    private User channelId;

    @JsonView(Views.IdName.class)
    @JsonIdentityReference
    @JsonIdentityInfo(property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class)
    @MapsId("subscriberId")
    @ManyToOne
    private User subscriberId;

    private boolean active;

    public UserSubs(User channelId, User subscriberId) {
        this.channelId = channelId;
        this.subscriberId = subscriberId;
        this.id = new UserSubsId(channelId.getId(), subscriberId.getId());
    }
}
