package springapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import springapp.domain.Task


trait TaskRepository extends JpaRepository[Task, java.lang.Long] { }