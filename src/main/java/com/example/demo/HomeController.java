package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

public class HomeController {
    @Autowired
    MessageRepository messageRepository;



    @RequestMapping("/")
    public String coverPage(){
        return "index";
    }

    @RequestMapping("/messages")
    public String listMessages(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "messagelist";
    }


    @GetMapping("/add")
    public String messageForm(Model model) {
        model.addAttribute("messages" , new Message());
        return "messageform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Message message,
                              BindingResult result) {

        if(result.hasErrors()){
            return "messageform";
        }
        messageRepository.save(message);
        return "redirect:/";
    }




}
