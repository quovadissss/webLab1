package ru.ssau.webLabs.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.ssau.webLabs.models.Project;
import ru.ssau.webLabs.repositories.ProjectRepo;
import ru.ssau.webLabs.services.ProjectService;
import java.time.LocalDate;
import java.util.List;

@Primary
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectDAO;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectDAO){
        this.projectDAO = projectDAO;
    }
    public Project create(String name, String description, LocalDate start, LocalDate end){
         return projectDAO.create(name, description, start, end);
    }

    public int update(int id, String name, String description,  LocalDate start, LocalDate end){
        return projectDAO.updateProject(id, name, description, start, end);
    }

    public int delete(int id){

        return projectDAO.deleteProject(id);
    }

    public Project getProject(int id){
        return projectDAO.getProject(id);
    }

    public List<Project> getListProjects(){return projectDAO.getListProject();}

    public List<Project> getListProjectFilter(LocalDate start, LocalDate end){ return projectDAO.getListProjectFilter(start,end); }

}
