## Part 1: Test it with SQL
id int PK 
employer varchar(255) 
name varchar(255) 
skills varchar(255)

## Part 2: Test it with SQL
SELECT name
FROM employer
WHERE location = "Saint Louis City";

## Part 3: Test it with SQL
DROP TABLE jobs;

## Part 4: Test it with SQL
SELECT DISTINCT name, description
FROM skill
INNER JOIN job_skills ON job_skills.skills_id = skill.id
ORDER BY name;

## ^^^This works without having to use "is not null" ¯\_ (ツ)_/¯