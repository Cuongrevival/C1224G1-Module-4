package org.example.tudien;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {
    private static final Map<String, String> dictionary = new HashMap<>();
    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("book", "quyển sách");
        dictionary.put("computer", "máy tính");
        dictionary.put("java", "ngôn ngữ lập trình Java");
        dictionary.put("spring", "mùa xuân hoặc Spring Framework");
    }

    @GetMapping("/dictionary")
    public String dictionary() {
     return "dictionary";
 }
    @PostMapping("/search")
    public String search(@RequestParam String word,
                         Model model) {

        String result = dictionary.get(word.toLowerCase());

        if (result != null) {
            model.addAttribute("word", word);
            model.addAttribute("result", result);
        } else {
            model.addAttribute("word", word);
            model.addAttribute("result", "Không tìm thấy từ.");
        }

        return "translator"; // kết quả tra từ
    }
}
