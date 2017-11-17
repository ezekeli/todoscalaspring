package springapp.util

import org.springframework.http.{HttpStatus, ResponseEntity}
import springapp.domain.Task
import springapp.repository.TaskRepository

class DBUtil(val taskRepository:TaskRepository) {
  def findAndExecute (id: Long, callBack: (Task) => ResponseEntity[String]) = {
    val taskWrapper : Option[Task] = Option(taskRepository.findOne(id))

    taskWrapper match {
      case Some(task) => callBack(task)
      case None => ResponseEntity.status(HttpStatus.NOT_FOUND)
    }
  }
}
