package com.guestbook.guestbook.dto;

import org.springframework.web.multipart.MultipartFile;

public class GuestEntryDto {
    private String text;
    private MultipartFile file;
    private String createdBy;
    private Long id ;
    private String status;

    public GuestEntryDto(String text, MultipartFile file, String createdBy, Long id, String status) {
        this.text = text;
        this.file = file;
        this.createdBy = createdBy;
        this.id = id;
        this.status = status;
    }
    public GuestEntryDto() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
