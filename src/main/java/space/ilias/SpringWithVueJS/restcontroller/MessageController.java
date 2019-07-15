package space.ilias.SpringWithVueJS.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.exceptions.NotFoundException;
import space.ilias.SpringWithVueJS.repo.MessageRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private NotFoundException notFound;

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
        return messageRepo.save(message);
    }

    @JsonView(Views.Full.class)
    @PutMapping("/{id}")
    public Message editMes(@PathVariable("id") Message message, @RequestBody Message messageNew) {
        BeanUtils.copyProperties(messageNew, message, "id");
        return messageRepo.save(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMap(@PathVariable("id") Message message) {
        messageRepo.delete(message);
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
