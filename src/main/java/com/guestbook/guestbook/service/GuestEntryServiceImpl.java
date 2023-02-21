package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.GuestEntryDto;
import com.guestbook.guestbook.model.GuestEntry;
import com.guestbook.guestbook.repository.GuestEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GuestEntryServiceImpl implements GuestEntryService{

    @Autowired
    private GuestEntryRepo guestEntryRepo ;
    @Override
    public List<GuestEntry> getAllGuestEntries() {
        return guestEntryRepo.findAll();
    }

    @Override
    public void saveGuestEntry(GuestEntryDto guestEntryDto) {
        GuestEntry entry = new GuestEntry();
        entry.setNotesText(guestEntryDto.getText());
        entry.setCreatedBy(guestEntryDto.getCreatedBy());
        entry.setStatus("SUBMITTED");
        // file code
        String fileName = StringUtils.cleanPath(guestEntryDto.getFile().getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            entry.setImage(Base64.getEncoder().encodeToString(guestEntryDto.getFile().getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.guestEntryRepo.save(entry);
    }

    @Override
    public GuestEntry getGuestEntryById(long id) {
        Optional<GuestEntry> optional = guestEntryRepo.findById(id);
        GuestEntry guestEntry = null;
        if (optional.isPresent()) {
            guestEntry = optional.get();
        } else {
            throw new RuntimeException(" GuestEntry not found for id :: " + id);
        }
        return guestEntry;
    }

    @Override
    public void deleteGuestEntryById(long id) {
        this.guestEntryRepo.deleteById(id);
    }

    @Override
    public void modifyGuestEntry(GuestEntryDto guestEntryDto) {
        GuestEntry guestEntry = this.guestEntryRepo.findById(guestEntryDto.getId()).get();
        guestEntry.setNotesText(guestEntryDto.getText());
        guestEntry.setStatus(guestEntryDto.getStatus());
        guestEntry.setCreatedBy(guestEntryDto.getCreatedBy());
        this.guestEntryRepo.save(guestEntry);
    }

    @Override
    public void approveGuestEntryById(long id) {
        this.guestEntryRepo.approveGuestEntry("APPROVED",id);
    }
}
