package com.guestbook.guestbook.web;


import com.guestbook.guestbook.dto.GuestEntryDto;
import com.guestbook.guestbook.model.GuestEntry;
import com.guestbook.guestbook.repository.GuestEntryRepo;
import com.guestbook.guestbook.service.GuestEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GuestEntryController {

    @Autowired
    private GuestEntryService guestEntryService;

    @GetMapping("/showNewGuestEntryForm/{id}")
    public String showNewEmployeeForm(@PathVariable(value = "id") String id,Model model) {
        // create model attribute to bind form data
        GuestEntryDto guestEntryDto = new GuestEntryDto();
        guestEntryDto.setCreatedBy(id);
        model.addAttribute("guestEntryDto", guestEntryDto);
        return "new_guestentry";
    }

    @PostMapping("/saveGuestEntry")
    public String saveGuestEntry(@ModelAttribute("guestEntryDto") GuestEntryDto guestEntryDto) {
        // save entry to database
        String entry = guestEntryService.saveGuestEntry(guestEntryDto);
        return "redirect:/";
    }

    @PostMapping("/modifyGuestEntry")
    public String modifyGuestEntry(@ModelAttribute("guestEntryDto") GuestEntryDto guestEntryDto) {
        // save entry to database
        guestEntryService.modifyGuestEntry(guestEntryDto);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        GuestEntry guestEntry = guestEntryService.getGuestEntryById(id);
        GuestEntryDto  guestEntryDto = new GuestEntryDto();
        guestEntryDto.setId(guestEntry.getId());
        guestEntryDto.setStatus(guestEntry.getStatus());
        guestEntryDto.setCreatedBy(guestEntry.getCreatedBy());
        guestEntryDto.setText(guestEntry.getNotesText());
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("guestEntryDto", guestEntryDto);
        return "update_guestbookentry";
    }

    @GetMapping("/deleteEntry/{id}")
    public String deleteEntry(@PathVariable(value = "id") long id) {

        this.guestEntryService.deleteGuestEntryById(id);
        return "redirect:/";
    }
    @GetMapping("/approveEntry/{id}")
    public String approveEntry(@PathVariable(value = "id") long id) {

        this.guestEntryService.approveGuestEntryById(id);
        return "redirect:/";
    }
}