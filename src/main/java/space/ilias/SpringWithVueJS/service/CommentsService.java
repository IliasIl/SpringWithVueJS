package space.ilias.SpringWithVueJS.service;

import org.springframework.stereotype.Service;
import space.ilias.SpringWithVueJS.domain.Comments;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.domain.dto.EventClass;
import space.ilias.SpringWithVueJS.domain.dto.ObjectType;
import space.ilias.SpringWithVueJS.repo.CommentsRepo;
import space.ilias.SpringWithVueJS.util.WsSender;

import java.util.function.BiConsumer;

@Service
public class CommentsService {
    private final CommentsRepo commentsRepo;

    private final BiConsumer<EventClass, Comments> websocketSender;

    public CommentsService(CommentsRepo commentsRepo, WsSender wsSender) {
        this.commentsRepo = commentsRepo;
        websocketSender=wsSender.sendMessage(ObjectType.COMMENTS, Views.FullComments.class);
    }


    public Comments addComments(Comments comments, User author) {
        comments.setAuthor(author);
        Comments saveMessage = commentsRepo.save(comments);
        websocketSender.accept(EventClass.CREATE, saveMessage);
        return saveMessage;
    }

}
