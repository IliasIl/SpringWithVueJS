package space.ilias.SpringWithVueJS.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
public class Comments implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.IdName.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_id", updatable = false, nullable = false)
    @JsonView(Views.FullComments.class)
    private Message message;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonView(Views.FullComments.class)
    private User author;

    @Transient
    @JsonView(Views.FullComments.class)
    private long messageId;



}
