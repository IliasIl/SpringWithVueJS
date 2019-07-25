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
@RequestMapping("/comment")
public class CommentsController {
    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping
    @JsonView(Views.Full.class)
    public Comments createComment(@RequestBody Comments comments, @AuthenticationPrincipal User user) {

        comments.setMessageId(comments.getMessage().getId());
        return commentsService.addComments(comments, user);
    }
}
