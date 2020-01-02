package space.ilias.SpringWithVueJS.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@JsonIdentityReference
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @Column(updatable = false)
    @JsonView(Views.Full.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.IdName.class)
    private User author;

    @OneToMany(mappedBy = "message", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonView(Views.IdName.class)
    private List<Comments> comments;

    @JsonView(Views.IdName.class)
    private String link;
    @JsonView(Views.IdName.class)
    private String linkTitle;
    @JsonView(Views.IdName.class)
    private String linkDescription;
    @JsonView(Views.IdName.class)
    private String linkCover;


}
