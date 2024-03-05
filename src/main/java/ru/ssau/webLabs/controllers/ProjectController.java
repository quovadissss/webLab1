package ru.ssau.webLabs.controllers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.webLabs.models.Project;
import ru.ssau.webLabs.services.ProjectService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {
    private final ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService p){
        this.projectService=p;
    }

    @PostMapping
    public ResponseEntity<?>create( @RequestBody @Valid Project project) {
        Project pr = projectService.create(project.getName_pr(), project.getProject_description(),
                project.getStart_data(), project.getEnd_data());
        return new ResponseEntity<>(pr, HttpStatus.CREATED);
    }
    @PutMapping( "/{pr_id}")
    public ResponseEntity<?> update(@Valid @RequestBody Project project, @PathVariable("pr_id") int id){
       if (projectService.update(id, project.getName_pr(), project.getProject_description(),
               project.getStart_data(), project.getEnd_data())!=0){
           return new ResponseEntity<>(HttpStatus.OK);
        }
       else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }
    @DeleteMapping("/{pr_id}")
    public ResponseEntity<?> deleteProject(@PathVariable("pr_id") int id){
            if(projectService.delete(id)!=0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
    @GetMapping("/{pr_id}")
    public ResponseEntity<?> getProject(@PathVariable("pr_id") int id){
        Project pr  = projectService.getProject(id);
        if(pr == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(pr, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getListProjects(){
        List<Project> list_pr = projectService.getListProjects();
        return new ResponseEntity<>(list_pr, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getListProjectsFilter(@RequestParam("start_date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                  @RequestParam("end_date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        List<Project> list_pr = projectService.getListProjectFilter(start, end);
        return new ResponseEntity<>(list_pr, HttpStatus.OK);
    }


}
