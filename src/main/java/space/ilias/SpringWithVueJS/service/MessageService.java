package space.ilias.SpringWithVueJS.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import space.ilias.SpringWithVueJS.domain.Message;
import space.ilias.SpringWithVueJS.domain.dto.MessagesMetaDto;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {
    private static String REGEX_URL = "https?:\\/\\/?[\\w\\d\\.\\-%\\/\\?=&#]+";
    private static String REGEX_IMAGE = "\\.(jpeg|jpg|gif|png)$";
    private static Pattern PATTERN_URL = Pattern.compile(REGEX_URL, Pattern.CASE_INSENSITIVE);
    private static Pattern PATTERN_IMAGE = Pattern.compile(REGEX_IMAGE, Pattern.CASE_INSENSITIVE);

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
}
