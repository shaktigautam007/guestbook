package com.guestbook.guestbook.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name =  "guest_entry")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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



}
