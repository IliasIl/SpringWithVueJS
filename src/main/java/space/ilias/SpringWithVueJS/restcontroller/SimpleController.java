package space.ilias.SpringWithVueJS.restcontroller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.ilias.SpringWithVueJS.domain.Comments;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.service.CommentsService;

@Slf4j
@RestController
@RequestMapping("/simp")
public class SimpleController {
    @Autowired
    private CommentsService commentsService;

    @PostMapping
    @JsonView(Views.FullComments.class)
    public Comments finalSimple(@RequestBody Comments comment,
                                @AuthenticationPrincipal User user) {
        return commentsService.addComments(comment, user);
    }
}
