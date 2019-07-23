package space.ilias.SpringWithVueJS.restcontroller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.ilias.SpringWithVueJS.domain.Comments;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.service.CommentsService;

@RestController
@RequestMapping("comment")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping
    public Comments createComment(@RequestBody Comments comments, @AuthenticationPrincipal User user){
        return commentsService.addComments(comments, user);
    }
}
