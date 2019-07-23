package space.ilias.SpringWithVueJS.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.IdName.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonView(Views.Full.class)
    private User author;

    @ManyToOne
    @JoinColumn(name = "message_id", updatable = false, nullable = false)
    @JsonView(Views.Full.class)
    private Message message;

}
