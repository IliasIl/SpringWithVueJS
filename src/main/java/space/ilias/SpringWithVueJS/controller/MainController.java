package space.ilias.SpringWithVueJS.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.domain.dto.MessagePortionDto;
import space.ilias.SpringWithVueJS.restcontroller.MessageController;
import space.ilias.SpringWithVueJS.service.MessageService;

import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {
    private final ObjectWriter writer;
    private final ObjectWriter writerProfile;
    private final MessageService messageService;
    @Value("${spring.profiles.active}")
    private String mod;

    @Autowired
    public MainController(ObjectMapper mapper, MessageService messageService) {
        ObjectMapper objectMapper = mapper.setConfig(mapper.getSerializationConfig());
        this.writer = objectMapper.writerWithView(Views.FullComments.class);
        this.writerProfile = objectMapper.writerWithView(Views.Full.class);
        this.messageService = messageService;
    }

    @GetMapping
    public String retMainPage(@AuthenticationPrincipal User user, Model model) throws JsonProcessingException {
        HashMap<Object, Object> values = new HashMap<>();
        if (user != null) {
            User user1 = user != null ? user : null;
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageable = PageRequest.of(0, MessageController.MESSAGESPERPAGE, sort);
            MessagePortionDto mes = messageService.findAll(pageable) != null ? messageService.findAll(pageable) : null;

            values.put("profile", (Object) user1);
            values.put("currentPage", mes.getCurrentPage());
            values.put("totalPages", mes.getTotalPages());
            String mesStr = writer.writeValueAsString(mes.getMessages());
            model.addAttribute("messages", mesStr);

        } else {
            values.put("profile", null);
            model.addAttribute("messages", "[]");
        }

        model.addAttribute("values", values);
        model.addAttribute("isDeveloperMode", "dev".equals(mod));
        return "index";
    }

}
