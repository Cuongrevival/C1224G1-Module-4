package com.codegym.controller;

import com.codegym.model.Watch;
import com.codegym.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/watches")
public class WatchController {

    @Autowired
    private WatchService watchService;

    @GetMapping
    public String listWatches(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword) {
        Page<Watch> watches;

        if (keyword.isEmpty()) {
            watches = watchService.findAllPaged(page, 8);
        } else {
            // Nếu có từ khóa → không dùng phân trang
            List<Watch> filtered = watchService.searchByBrand(keyword);
            model.addAttribute("watches", filtered);
            model.addAttribute("keyword", keyword);
            return "watch/list";
        }

        model.addAttribute("watches", watches.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", watches.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "watch/list";
    }
}

