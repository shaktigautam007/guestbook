package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.GuestEntryDto;
import com.guestbook.guestbook.model.GuestEntry;

import java.util.Collection;
import java.util.List;

public interface GuestEntryService {
    List<GuestEntry> getAllGuestEntries();
    String saveGuestEntry(GuestEntryDto employee);
    GuestEntry getGuestEntryById(long id);
    void deleteGuestEntryById(long id);

    void modifyGuestEntry(GuestEntryDto guestEntryDto);

    void approveGuestEntryById(long id);
}
