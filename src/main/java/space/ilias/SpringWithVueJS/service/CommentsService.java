package space.ilias.SpringWithVueJS.service;

import org.springframework.stereotype.Service;
import space.ilias.SpringWithVueJS.domain.Comments;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.repo.CommentsRepo;

@Service
public class CommentsService {
    private final CommentsRepo commentsRepo;

    public CommentsService(CommentsRepo commentsRepo) {
        this.commentsRepo = commentsRepo;
    }


    public Comments addComments(Comments comments, User author) {
        comments.setAuthor(author);
        return commentsRepo.save(comments);
    }

}
