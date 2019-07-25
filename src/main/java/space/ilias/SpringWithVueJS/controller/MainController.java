package space.ilias.SpringWithVueJS.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.repo.MessageRepo;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {
    private final ObjectWriter writer;
    private final MessageRepo messageRepo;
    @Value("${spring.profiles.active}")
    private String mod;

    public MainController(ObjectMapper mapper, MessageRepo messageRepo) {
        this.writer = mapper.setConfig(mapper.getSerializationConfig()).writerWithView(Views.Full.class);
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public String retMainPage(@AuthenticationPrincipal User user, Model model) throws JsonProcessingException {
        HashMap<Object, Object> values = new HashMap<>();
        String mesStr="";
        if (user != null) {
            User user1 = user != null ? user : null;
            values.put("profile", (Object) user1);
            List<Message> mes = messageRepo.findAll() != null ? messageRepo.findAll() : null;
            mesStr= writer.writeValueAsString(mes);
            model.addAttribute("messages", mesStr);

        } else{
            values.put("profile", null);
            model.addAttribute("messages", "[]");
        }

        model.addAttribute("values", values);
        model.addAttribute("isDeveloperMode", "dev".equals(mod));
        return "index";
    }

}
