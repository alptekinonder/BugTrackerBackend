package com.bugtracker.demo.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugtracker.demo.model.Ticket;
import com.bugtracker.demo.repository.TicketRepository;
import org.springframework.security.access.prepost.PreAuthorize;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List < Ticket > getTickets() {
        return this.ticketRepository.findAll();
    }
    @GetMapping("/{ProjectId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Ticket> getTicket(@PathVariable Long ProjectId) {
        //return ticketRepository.findById(id).orElseThrow(RuntimeException::new);
        return ticketRepository.findByProjectId(ProjectId);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?>  createTicket(@RequestBody Ticket ticket) throws URISyntaxException {
        System.out.println("h\n"+ ticket.getProjectId()+"  " + ticket.getTicketDescription()+" "+ticket.getTicketTitle());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        ticket.setCreateDate(dtf.format(now));
        Ticket savedTicket = ticketRepository.save(ticket);
        return ResponseEntity.created(new URI("/tickets/" + savedTicket.getId())).body(savedTicket);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketRepository.findById(id)
        .map(ticketNew -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            ticketNew.setAssignedDeveloper(ticket.getAssignedDeveloper());
            ticketNew.setAssignDate(dtf.format(now));
          return ticketRepository.save(ticketNew);
        })
        .orElseGet(() -> {
            ticket.setId(id);
          return ticketRepository.save(ticket);
        });
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?>  deleteTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
