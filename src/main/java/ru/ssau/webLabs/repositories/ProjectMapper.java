package ru.ssau.webLabs.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.ssau.webLabs.models.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        project.setPr_id(rs.getInt("pr_id"));
        project.setName_pr(rs.getString("name_pr"));
        project.setProject_description(rs.getString("project_description"));
        project.setStart_data(rs.getDate("start_date").toLocalDate());
        project.setEnd_data(rs.getDate("end_date").toLocalDate());
        return project;
    }
}
