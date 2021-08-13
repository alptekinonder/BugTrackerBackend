package com.bugtracker.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugtracker.demo.model.Ticket;
import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    List<Ticket> findByProjectId(Long projectId);
}