package ru.ssau.webLabs.models;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @NotNull
    private int pr_id;
    @NotNull(message = "Name project should be NOT NULL")
    private String name_pr;
    @NotNull
    private String project_description;
    @NotNull(message = "Data start should be NOT NULL")
    private LocalDate start_data;
    @NotNull(message = "Data end should be NOT NULL")
    private LocalDate end_data;

}
