package ru.ssau.webLabs.services;

import ru.ssau.webLabs.models.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectService {
    Project create(String name, String description, LocalDate start, LocalDate end);
    int update(int id, String name, String description, LocalDate start, LocalDate end);
    int delete(int id);

    Project getProject(int id);
    List<Project> getListProjects();
    List<Project> getListProjectFilter(LocalDate start, LocalDate end);
}
