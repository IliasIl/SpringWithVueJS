package space.ilias.SpringWithVueJS.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.Views;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonView(Views.IdName.class)
public class MessagePortionDto {
    private List<Message> messages;
    private int currentPage;
    private int totalPages;
}
