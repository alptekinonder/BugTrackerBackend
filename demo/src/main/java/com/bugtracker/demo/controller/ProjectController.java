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

import com.bugtracker.demo.model.Project;
import com.bugtracker.demo.repository.ProjectRepository;
import org.springframework.security.access.prepost.PreAuthorize;
   
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List < Project > getProjects() {
        return this.projectRepository.findAll();
    }
    @GetMapping("/{id}}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Project getProject(@PathVariable Long id) {
        return projectRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?>  createProject(@RequestBody Project project) throws URISyntaxException {
        System.out.println("helo\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        project.setStartDate(dtf.format(now));
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.created(new URI("/projects/" + savedProject.getId())).body(savedProject);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectRepository.findById(id)
        .map(projectNew -> {
          projectNew.setDescription(project.getDescription());
          projectNew.setTitle(project.getTitle());
          return projectRepository.save(projectNew);
        })
        .orElseGet(() -> {
          project.setId(id);
          return projectRepository.save(project);
        });
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?>  deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}