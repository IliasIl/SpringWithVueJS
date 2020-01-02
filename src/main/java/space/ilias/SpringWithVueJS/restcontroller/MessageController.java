package space.ilias.SpringWithVueJS.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.domain.dto.MessagePortionDto;
import space.ilias.SpringWithVueJS.service.MessageService;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {
    public static final int MESSAGESPERPAGE = 3;
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @JsonView(Views.Full.class)
    @GetMapping
    public MessagePortionDto allMessages(
            @PageableDefault(size = MESSAGESPERPAGE, sort = {"id"}, direction = Sort.Direction.DESC)
                    Pageable pageable, @AuthenticationPrincipal User user) {
        MessagePortionDto messages = messageService.findAll(user, pageable) != null ? messageService.findAll(user, pageable) : null;
        return messages;
    }

    @JsonView(Views.Full.class)
    @GetMapping("/{id}")
    public Message showMessage(@PathVariable("id") Message message) {
        return message;
    }

    @JsonView(Views.Full.class)
    @PostMapping
    public Message createMes(@RequestBody Message message, @AuthenticationPrincipal User author) throws IOException {
        return messageService.createMessage(author, message);
    }

    @JsonView(Views.Full.class)
    @PutMapping("/{id}")
    public Message editMes(@PathVariable("id") Message message, @RequestBody Message messageNew) throws IOException {
        return messageService.editMessage(message, messageNew);
    }

    @JsonView(Views.Full.class)
    @DeleteMapping("/{id}")
    public void deleteMap(@PathVariable("id") Message message) {
        messageService.deleteMessage(message);
    }

}
