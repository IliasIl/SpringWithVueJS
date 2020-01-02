package space.ilias.SpringWithVueJS.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.User;
import space.ilias.SpringWithVueJS.domain.UserSubs;
import space.ilias.SpringWithVueJS.domain.Views;
import space.ilias.SpringWithVueJS.domain.dto.EventClass;
import space.ilias.SpringWithVueJS.domain.dto.MessagePortionDto;
import space.ilias.SpringWithVueJS.domain.dto.MessagesMetaDto;
import space.ilias.SpringWithVueJS.domain.dto.ObjectType;
import space.ilias.SpringWithVueJS.repo.MessageRepo;
import space.ilias.SpringWithVueJS.util.WsSender;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private static String REGEX_URL = "https?:\\/\\/?[\\w\\d\\.\\-%\\/\\?=&#]+";
    private static String REGEX_IMAGE = "\\.(jpeg|jpg|gif|png)$";
    private static Pattern PATTERN_URL = Pattern.compile(REGEX_URL, Pattern.CASE_INSENSITIVE);
    private static Pattern PATTERN_IMAGE = Pattern.compile(REGEX_IMAGE, Pattern.CASE_INSENSITIVE);
    private final MessageRepo messageRepo;
    private final BiConsumer<EventClass, Message> wsSender;
    private final UserSubsService userSubsService;

    @Autowired
    public MessageService(MessageRepo messageRepo, WsSender sender, UserSubsService userSubsService) {
        this.messageRepo = messageRepo;
        this.wsSender = sender.sendMessage(ObjectType.MESSAGE, Views.IdName.class);
        this.userSubsService = userSubsService;
    }

    public void metaFill(Message message) throws IOException {
        String text = message.getText();
        Matcher matcher = PATTERN_URL.matcher(text);
        if (matcher.find()) {
            String link = text.substring(matcher.start(), matcher.end());
            message.setLink(link);
            matcher = PATTERN_IMAGE.matcher(link);
            if (matcher.find()) {
                message.setLinkCover(link);
            } else if (!link.contains("youtu")) {
                MessagesMetaDto meta = getLinkMeta(link);
                message.setLinkCover(meta.getLinkCover());
                message.setLinkDescription(meta.getLinkDescription());
                message.setLinkTitle(meta.getLinkTitle());
            }
        }
    }

    private MessagesMetaDto getLinkMeta(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        Elements title = doc.select("meta[name$=title], meta[property$=title]");
        Elements description = doc.select("meta[name$=description], meta[property$=description]");
        Elements cover = doc.select("meta[name$=image], meta[property$=image]");
        return new MessagesMetaDto(getStringFromElements(title.first()),
                getStringFromElements(description.first()),
                getStringFromElements(cover.first()));
    }

    private String getStringFromElements(Element element) {
        return element != null ? element.attr("content") : "";
    }

    public MessagePortionDto findAll(User user, Pageable pageable) {

        List<User> users = userSubsService
                .findBySubscriberId(user)
                .stream().filter(UserSubs::isActive)
                .map(UserSubs::getChannelId)
                .collect(Collectors.toList());
        users.add(user);

        Page<Message> page = messageRepo.findByAuthorIn(users, pageable);
        return new MessagePortionDto(page.getContent(), pageable.getPageNumber(), page.getTotalPages());
    }

    public Message createMessage(User author, Message message) throws IOException {
        metaFill(message);
        message.setCreationDate(LocalDateTime.now());
        message.setAuthor(author);
        Message save = messageRepo.save(message);
        wsSender.accept(EventClass.CREATE, save);
        return save;
    }

    public void deleteMessage(Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventClass.REMOVE, message);
    }

    public Message editMessage(Message message, Message messageNew) throws IOException {
        message.setText(messageNew.getText());
        Message message1 = messageRepo.save(message);
        metaFill(message1);
        wsSender.accept(EventClass.UPDATE, message1);
        return message1;
    }
}
