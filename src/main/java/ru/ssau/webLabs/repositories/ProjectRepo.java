package ru.ssau.webLabs.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ssau.webLabs.models.Project;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ProjectRepo {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final JdbcTemplate jdbc_template;

    @Autowired
    public ProjectRepo(NamedParameterJdbcTemplate template, JdbcTemplate jdbcTemplate){
        this.namedParameterJdbcTemplate = template;
        this.jdbc_template = jdbcTemplate;
    }
    //возвращает id 0
    public Project create(String name, String description, LocalDate start, LocalDate end){
        Integer id = jdbc_template.queryForObject("SELECT nextval('project_ID')", Integer.class);
        jdbc_template.update("INSERT INTO Project VALUES (?,?,?,?,?)", id, name, description, start,end);
        return new Project(id, name, description, start, end);
    }

    public int updateProject(int id, String name, String description, LocalDate start, LocalDate end){
        String sql = "UPDATE project SET name_pr=:name_pr, project_description=:project_description, " +
                "start_date=:start_date, end_date=:end_date where pr_id=:pr_id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name_pr",name);
        map.addValue("project_description", description);
        map.addValue("start_date", start);
        map.addValue("end_date", end);
        map.addValue("pr_id", id);
        return namedParameterJdbcTemplate.update(sql, map);
    }

    public int deleteProject(int id){
        return jdbc_template.update("DELETE FROM project where pr_id=?", id);
    }

    public Project getProject(int id) {
        String sql = "select*from project  where pr_id =?";
        return jdbc_template.query(sql,new Object[]{id}, new ProjectMapper()).stream().findAny().orElse(null);
    }

    public List<Project> getListProject(){
        String sql = "select*from project";
        return jdbc_template.query(sql, new ProjectMapper());
    }

    public List<Project> getListProjectFilter(LocalDate start, LocalDate end){
        String sql = "select* from project where start_date>=? and end_date<=?";
        return jdbc_template.query(sql, new Object[]{start, end}, new ProjectMapper());
    }


}
