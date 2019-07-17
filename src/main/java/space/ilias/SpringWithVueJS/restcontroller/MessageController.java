package space.ilias.SpringWithVueJS.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.domain.dto.EventClass;
import space.ilias.SpringWithVueJS.domain.dto.ObjectType;
import space.ilias.SpringWithVueJS.exceptions.NotFoundException;
import space.ilias.SpringWithVueJS.repo.MessageRepo;
import space.ilias.SpringWithVueJS.util.WsSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final BiConsumer<EventClass, Message> wsSender;

    private final MessageRepo messageRepo;

    private final NotFoundException notFound;

    public MessageController(WsSender wsSender, MessageRepo messageRepo, NotFoundException notFound) {
        this.wsSender = wsSender.sendMessage(ObjectType.MESSAGE, Views.IdName.class);
        this.messageRepo = messageRepo;
        this.notFound = notFound;
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
    public Message createMes(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());

        Message save = messageRepo.save(message);
        wsSender.accept(EventClass.CREATE, message);

        return save;
    }

    @JsonView(Views.Full.class)
    @PutMapping("/{id}")
    public Message editMes(@PathVariable("id") Message message, @RequestBody Message messageNew) {
        BeanUtils.copyProperties(messageNew, message, "id");
        Message message1 = messageRepo.save(message);
        wsSender.accept(EventClass.UPDATE, message1);
        return message1;
    }

    @DeleteMapping("/{id}")
    public void deleteMap(@PathVariable("id") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventClass.REMOVE, message);
    }

    @MessageMapping("/changeMes")
    @SendTo("/topic/activity")
    public Message changeMes(Message message) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }
    @MessageMapping("/deleteMes")
    @SendTo("/topic/delete")
    public Message deleteMes(Message message){
        messageRepo.delete(message);
        return message;
    }


}
