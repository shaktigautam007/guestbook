package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.GuestEntryDto;
import com.guestbook.guestbook.model.GuestEntry;
import com.guestbook.guestbook.repository.GuestEntryRepo;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.guestbook.guestbook.dto.GuestBookConstants.*;

@Service
public class GuestEntryServiceImpl implements GuestEntryService{

    @Autowired
    private GuestEntryRepo guestEntryRepo ;
    @Override
    public List<GuestEntry> getAllGuestEntries() {
        return guestEntryRepo.findAll();
    }

    @Override
    public String saveGuestEntry(GuestEntryDto guestEntryDto) {
        GuestEntry entry = new GuestEntry();
        entry.setNotesText(guestEntryDto.getText());
        entry.setCreatedBy(guestEntryDto.getCreatedBy());
        entry.setStatus("SUBMITTED");
        // file code
        MultipartFile file = guestEntryDto.getFile();
        long fileSizeKB = file.getSize() / 1024;
        if(fileSizeKB!=0L){
            String validationStatus = this.validateFileUpload(file);
            if(!validationStatus.equalsIgnoreCase(SUCCESS))return validationStatus;
            try {
                entry.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.guestEntryRepo.save(entry);
        return "success";
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
    public String modifyGuestEntry(GuestEntryDto guestEntryDto) {
        GuestEntry guestEntry = this.guestEntryRepo.findById(guestEntryDto.getId()).get();
        guestEntry.setNotesText(guestEntryDto.getText());
        guestEntry.setCreatedBy(guestEntryDto.getCreatedBy());
        MultipartFile file = guestEntryDto.getFile();
        long fileSizeKB = file.getSize() / 1024;
        if(fileSizeKB!=0L){
            String validationStatus = this.validateFileUpload(file);
            if(!validationStatus.equalsIgnoreCase(SUCCESS))return validationStatus;
            try {
                guestEntry.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.guestEntryRepo.save(guestEntry);
        return SUCCESS;
    }

    @Override
    public void approveGuestEntryById(long id) {
        this.guestEntryRepo.approveGuestEntry("APPROVED",id);
    }

    private String validateFileUpload(MultipartFile file){
        try {
            Tika tika = new Tika();
            String detectedType = tika.detect(file.getBytes());
            long fileSizeKB = file.getSize() / 1024;
            if(fileSizeKB!=0L){
                if(!detectedType.equalsIgnoreCase(IMAGE_TYPE_JPEG)){
                    return NOT_JPEG_IMAGE;
                } else if (fileSizeKB > 250L) {
                    return IMAGE_TOO_BIG;
                }
                else return SUCCESS;
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return  SUCCESS;
    }

}
