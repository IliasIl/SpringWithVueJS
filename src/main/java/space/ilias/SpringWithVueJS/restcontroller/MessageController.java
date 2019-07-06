package space.ilias.SpringWithVueJS.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.ilias.SpringWithVueJS.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static int index = 5;
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
    @Autowired
    private NotFoundException notFound;

    @GetMapping
    public List<Map<String, String>> allMessages() {
        return simpleDB;
    }

    @GetMapping("/{id}")
    public Map<String, String> showMessage(@PathVariable("id") String id) {
        Map<String, String> message = getStringStringMap(id);
        return message;
    }

    private Map<String, String> getStringStringMap(@PathVariable("id") String id) {
        return simpleDB
                .stream().filter(elem -> elem.get("id").equals(id)).findFirst().orElseThrow(() -> notFound);
    }

    @PostMapping
    public Map<String, String> createMes(@RequestBody Map<String, String> mes) {
        mes.put("id", String.valueOf(index++));
        simpleDB.add(mes);
        return mes;
    }

    @PutMapping("/{id}")
    public Map<String, String> editMes(@PathVariable String id, @RequestBody Map<String, String> mes) {
        Map<String, String> message = getStringStringMap(id);
        message.putAll(mes);
        message.put("id", id);
        return message;
    }

    @DeleteMapping("/{id}")
    public void deleteMap(@PathVariable String id) {
        Map<String, String> message = getStringStringMap(id);
        simpleDB.remove(message);
    }

}
