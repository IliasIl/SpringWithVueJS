package space.ilias.SpringWithVueJS.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessagesMetaDto {
    private String linkTitle;
    private String linkDescription;
    private String linkCover;
}
