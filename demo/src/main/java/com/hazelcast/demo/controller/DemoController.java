package com.hazelcast.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hazelcast.demo.data.CacheData;
import com.hazelcast.demo.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
public class DemoController {

    @Autowired
    CacheService cacheService;

    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping("/view")
    public String viewPage(Model model) {
        CacheData cacheData = cacheService.findAllByValue("cache");
        if(Objects.isNull(cacheData))
            model.addAttribute("data", null);
        else
            model.addAttribute("data", cacheData.getData());
        return "view";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        return "add";
    }

    @GetMapping("/clear")
    public String clearPage(Model model) {
        return "clear";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String create(CacheData data, RedirectAttributes redirectAttributes) throws JsonProcessingException {
        cacheService.savePage("cache", data);
        return "redirect:/";
    }

    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    public String clear() throws JsonProcessingException {
        cacheService.clearAll();
        return "redirect:/";
    }


}
