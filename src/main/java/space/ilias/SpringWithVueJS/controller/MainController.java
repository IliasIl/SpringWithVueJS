package space.ilias.SpringWithVueJS.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.repo.MessageRepo;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class MainController {
    @Value("${spring.profiles.active}")
    private String mod;


    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String retMainPage(@AuthenticationPrincipal User user, Model model) {
        HashMap<Object, Object> values = new HashMap<>();
        User user1 = user != null ? user : null;
        values.put("profile", (Object) user1);
        List<Message> mes = messageRepo.findAll() != null ? messageRepo.findAll() : null;
        values.put("comp", mes);
        model.addAttribute("values", values);
        model.addAttribute("isDeveloperMode", "dev".equals(mod));
        return "index";
    }

}
