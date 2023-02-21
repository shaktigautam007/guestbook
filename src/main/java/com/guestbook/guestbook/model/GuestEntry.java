package com.guestbook.guestbook.model;


import javax.persistence.*;

@Entity
@Table(name =  "guest_entry")
public class GuestEntry {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notes_text")
    private String notesText;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "status")
    private String status;

    public GuestEntry() {

    }

    public GuestEntry(Long id, String notesText, String image, String createdBy, String status) {
        this.id = id;
        this.notesText = notesText;
        this.image = image;
        this.createdBy = createdBy;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotesText() {
        return notesText;
    }

    public void setNotesText(String notesText) {
        this.notesText = notesText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
