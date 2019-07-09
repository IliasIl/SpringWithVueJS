package space.ilias.SpringWithVueJS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.repo.MessageRepo;

import java.util.HashMap;


@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String retMainPage(@AuthenticationPrincipal User user, Model model) {
        HashMap<Object, Object> values = new HashMap<>();
        values.put("profiles", user);
        values.put("mes", messageRepo.findAll());
        model.addAttribute("values", values);
        return "index";
    }
}
