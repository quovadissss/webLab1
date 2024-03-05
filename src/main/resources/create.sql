drop table task;
drop table project;

CREATE SEQUENCE IF NOT EXISTS project_ID;
CREATE SEQUENCE IF NOT EXISTS task_ID;

create table project(
                        pr_id bigint NOT NULL DEFAULT nextval('project_ID') PRIMARY KEY,
                        name_pr varchar(30) NOT NULL,
                        project_description TEXT NOT NULL,
                        start_date date NOT NULL,
                        end_date timestamp without time zone NOT NULL
);

create table task(
                     pr_id bigint NOT NULL REFERENCES project(pr_id),
                     task_id bigint NOT NULL DEFAULT nextval('task_ID'),
                     name_task varchar(30),
                     task_description TEXT,
                     plan_comp_date date NOT NULL,
                     flag_t bool,
                     PRIMARY KEY(pr_id, task_id)
);

