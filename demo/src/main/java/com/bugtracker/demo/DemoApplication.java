package com.bugtracker.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bugtracker.demo.model.User;
import com.bugtracker.demo.model.Ticket;
import com.bugtracker.demo.model.Project;
import com.bugtracker.demo.repository.ProjectRepository;
import com.bugtracker.demo.repository.TicketRepository;
import com.bugtracker.demo.repository.UserRepository;
import com.bugtracker.demo.repository.RoleRepository;
import com.bugtracker.demo.model.Role;

import java.util.HashSet;
import java.util.Set;

import com.bugtracker.demo.model.ERole;
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
//to run , write ./mvnw spring-boot:run
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String...args) throws Exception {
        this.roleRepository.save(new Role(ERole.ROLE_USER));
        this.roleRepository.save(new Role(ERole.ROLE_ADMIN));
        this.roleRepository.save(new Role(ERole.ROLE_MODERATOR));

        User user1= new User("demoUser", "demoUser@gmail.com", "$2a$12$yEpd.TRMSlsHcq8Geth8p.BSqu6YSPcWgZhoNxsdYQL4Xi3Pbz2QG");
		Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found1."));
roles.add(userRole);
        user1.setRoles(roles);
		userRepository.save(user1);
        this.userRepository.save(user1);

        User user2 = new User("demoAdmin", "demoAdmin@gmail.com", "$2a$12$yEpd.TRMSlsHcq8Geth8p.BSqu6YSPcWgZhoNxsdYQL4Xi3Pbz2QG");
		Set<Role> roles2 = new HashSet<>();
        Role userRole2 = roleRepository.findByName(ERole.ROLE_ADMIN)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found1."));
        roles2.add(userRole2);
        user2.setRoles(roles2);
		userRepository.save(user2);
        this.userRepository.save(user2);

        User user3 = new User("Tom Cruise", "TomCruise@gmail.com", "$2a$12$yEpd.TRMSlsHcq8Geth8p.BSqu6YSPcWgZhoNxsdYQL4Xi3Pbz2QG");
		Set<Role> roles3 = new HashSet<>();
        Role userRole3 = roleRepository.findByName(ERole.ROLE_MODERATOR)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found1."));
        roles3.add(userRole3);
        user3.setRoles(roles3);
		userRepository.save(user3);
        this.userRepository.save(user3);

        User user4 = new User("TonyStark", "TonyStark@gmail.com", "$2a$12$yEpd.TRMSlsHcq8Geth8p.BSqu6YSPcWgZhoNxsdYQL4Xi3Pbz2QG");
		Set<Role> roles4 = new HashSet<>();
        Role userRole4 = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found1."));
        roles4.add(userRole4);
        user4.setRoles(roles4);
		userRepository.save(user4);
        this.userRepository.save(user4);

        this.ticketRepository.save(new Ticket("Changing Profile Picture", "When profile picture changed thumbnails of old comments does not change and shows the previous profile picture of account","2021/08/11 21:09:31","2021/08/12 21:08:31","TonyStark",1l));
        this.ticketRepository.save(new Ticket("Trade button", "Using trade button twice in the same round add resources twice","2021/08/11 21:09:33","2021/08/12 21:08:33","TomCruise",2l));
        this.ticketRepository.save(new Ticket("Bottom Buttons Disappear", "Bottom buttons does not show up in Android 10","2021/08/11 21:09:35","2021/08/12 21:08:35","demoAdmin",1l));
        this.ticketRepository.save(new Ticket("Wrath of Gods", "Wrath of Gods can be played without \"Gods Stage\" card. It should not be possible","2021/08/11 21:09:37","2021/08/12 21:08:37","demoUser",2l));
        this.ticketRepository.save(new Ticket("DashBoard Update", "Dashboard does not update when a new project is created.","2021/08/11 21:09:31","2021/08/12 21:08:39","TonyStark",3l));
        
        this.projectRepository.save(new Project("Bilconnect Project(Android)", "Project for connecting all bilkent students in a platform where they can share their complaints.","2021/08/11 21:08:32"));
        this.projectRepository.save(new Project("7WONDERS Online Board Game", "An online version of popular board game seven wonders. Java used for both backend and frontend. Deployed to Amazon AWS","2021/08/11 21:08:35"));
        this.projectRepository.save(new Project("Simple Bug Tracker", "Simple bug tracker that users can post and remove tickets and admins can create projects and assign tickets to developers.","2021/08/11 21:08:37"));
        this.projectRepository.save(new Project("VISP(Unity,Mobile)", "AR application for attaching media to the real world objects","2021/08/11 21:08:39"));
    
    }
}