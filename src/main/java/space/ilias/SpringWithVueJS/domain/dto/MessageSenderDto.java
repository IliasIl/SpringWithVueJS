package space.ilias.SpringWithVueJS.domain.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@JsonView(Views.IdName.class)
public class MessageSenderDto {
    private ObjectType objectType;
    private EventClass eventClass;

    @JsonRawValue
    private String payload;
}
