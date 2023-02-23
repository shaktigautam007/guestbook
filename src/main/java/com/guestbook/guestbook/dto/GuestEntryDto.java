package com.guestbook.guestbook.dto;

import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestEntryDto {
    private String text;
    private MultipartFile file;
    private String createdBy;
    private Long id ;
    private String status;

    private String image;

}
