package space.ilias.SpringWithVueJS.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import space.ilias.SpringWithVueJS.domain.dto.EventClass;
import space.ilias.SpringWithVueJS.domain.dto.MessageSenderDto;
import space.ilias.SpringWithVueJS.domain.dto.ObjectType;

import java.util.function.BiConsumer;

@Component
public class WsSender {
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;


    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public <T> BiConsumer<EventClass, T> sendMessage(ObjectType objectType, Class view) {
        ObjectWriter writer = mapper.
                setConfig(mapper.getSerializationConfig()).writerWithView(view);

        return (a, b) -> {
            String payload = null;
            try {
                payload = writer.writeValueAsString(b);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            template.convertAndSend("/topic/activity", new MessageSenderDto(objectType, a, payload));
        };
    }


}
