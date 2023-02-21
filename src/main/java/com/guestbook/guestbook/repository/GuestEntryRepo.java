package com.guestbook.guestbook.repository;


import com.guestbook.guestbook.model.GuestEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface GuestEntryRepo extends JpaRepository<GuestEntry, Long> {
    @Modifying
    @Query("update GuestEntry ge set ge.status = :status where ge.id = :id")
    int approveGuestEntry(@Param("status") String status, @Param("id") Long id);
}