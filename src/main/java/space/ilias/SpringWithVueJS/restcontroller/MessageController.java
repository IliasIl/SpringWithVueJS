package restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mes")
public class MessageController {
    private static List<Map<String, String>> simpleDB = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "first");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "second");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "third");
        }});
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("text", "forth");
        }});
    }};

    @GetMapping
    public String allMessages(){
        return "sffs";
    }

}
