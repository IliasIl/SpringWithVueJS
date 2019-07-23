package space.ilias.SpringWithVueJS.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.domain.dto.EventClass;
import space.ilias.SpringWithVueJS.domain.dto.ObjectType;
import space.ilias.SpringWithVueJS.repo.MessageRepo;
import space.ilias.SpringWithVueJS.service.MessageService;
import space.ilias.SpringWithVueJS.util.WsSender;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    private final BiConsumer<EventClass, Message> wsSender;
    private final MessageRepo messageRepo;


    public MessageController(MessageService messageService, WsSender wsSender, MessageRepo messageRepo) {
        this.messageService = messageService;
        this.wsSender = wsSender.sendMessage(ObjectType.MESSAGE, Views.IdName.class);
        this.messageRepo = messageRepo;
    }

    @JsonView(Views.IdName.class)
    @GetMapping
    public List<Message> allMessages() {
        return messageRepo.findAll();
    }

    @JsonView(Views.Full.class)
    @GetMapping("/{id}")
    public Message showMessage(@PathVariable("id") Message message) {
        return message;
    }

    @JsonView(Views.Full.class)
    @PostMapping
    public Message createMes(@RequestBody Message message, @AuthenticationPrincipal User author) throws IOException {
        message.setCreationDate(LocalDateTime.now());
        message.setAuthor(author);
        Message save = messageRepo.save(message);
        messageService.metaFill(save);
        wsSender.accept(EventClass.CREATE, message);

        return save;
    }

    @JsonView(Views.Full.class)
    @PutMapping("/{id}")
    public Message editMes(@PathVariable("id") Message message, @RequestBody Message messageNew) throws IOException {
        BeanUtils.copyProperties(messageNew, message, "id", "creationDate");
        Message message1 = messageRepo.save(message);
        messageService.metaFill(message1);
        wsSender.accept(EventClass.UPDATE, message1);
        return message1;
    }

    @DeleteMapping("/{id}")
    public void deleteMap(@PathVariable("id") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventClass.REMOVE, message);
    }




}
