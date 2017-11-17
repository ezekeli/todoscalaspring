package springapp.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._
import springapp.domain.Task
import springapp.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import springapp.util.{DBUtil, JSONUtil}

import scala.util.{Failure, Success}

@Controller
@RequestMapping(Array("/rest"))
class RestController (val taskRepository: TaskRepository) {
  private val dbUtil = new DBUtil(taskRepository)
  @GetMapping(value = Array("/{id}"))
  def get(@PathVariable("id") id: Long) =  dbUtil.findAndExecute(id, task => ResponseEntity.ok(task.toJson))

  @PostMapping
  def add(model: Model, @RequestBody request: String): ResponseEntity[String]  = {
    JSONUtil.fromJson(request, classOf[Task]) match {
      case Success(result) =>
        val task = result.asInstanceOf[Task]
        task.title.length match {
          case length if length > 0 => ResponseEntity.ok(taskRepository.saveAndFlush(task).toJson)
          case _ => ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title couldn't be empty.")
        }
      case Failure(e) => ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect body format.")
    }
  }

  @PutMapping(value = Array("/status/{id}"))
  def toggleStatus(@PathVariable("id") id: Long) = dbUtil.findAndExecute(id, task =>
  {
    task.complited = !task.complited
    ResponseEntity.ok(taskRepository.saveAndFlush(task).toJson)
  })


  @DeleteMapping(value = Array("/{id}"))
  def remove(@PathVariable("id") id: Long) = dbUtil.findAndExecute(id, task =>
  {
    taskRepository.delete(id)
    ResponseEntity.ok.build()
  })
}
