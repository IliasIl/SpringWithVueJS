package space.ilias.SpringWithVueJS.domain.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import space.ilias.SpringWithVueJS.domain.Views;

@Data
@AllArgsConstructor
@JsonView(Views.IdName.class)
public class MessageSenderDto {
    private ObjectType objectType;
    private EventClass eventClass;
    @JsonRawValue
    private String payload;
}
