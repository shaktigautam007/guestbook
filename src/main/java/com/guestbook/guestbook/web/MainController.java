package com.guestbook.guestbook.web;


import com.guestbook.guestbook.repository.GuestEntryRepo;
import com.guestbook.guestbook.service.GuestEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private GuestEntryService guestEntryService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/list", "/"})
    public ModelAndView getAllEmployees() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("entries", guestEntryService.getAllGuestEntries());
        return mav;
    }


}
