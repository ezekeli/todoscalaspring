package springapp.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._
import springapp.domain.Task
import springapp.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Controller
@RequestMapping(Array("/rest"))
class RestController (val taskRepository: TaskRepository) {

  @GetMapping(value = Array("/{id}"))
  def get(@PathVariable("id") id: Long) = {
    def task : Option[Task] = Option(taskRepository.findOne(id))
    if(task.isDefined) ResponseEntity.ok(task.get.toJson) else ResponseEntity.status(HttpStatus.NOT_FOUND)
  }


  @PostMapping
  def add(model: Model, @RequestBody task: Task): ResponseEntity[String]  = { //будем считать, что в теле всегда лежит объект типа Task, иначе просто будет ошибка 500. // это надо пофиксить!
    if (task.title.length > 0) ResponseEntity.ok(taskRepository.saveAndFlush(task).toJson) else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title couldn't be empty!")
  }

  @PutMapping(value = Array("/status/{id}"))
  def toggleStatus(@PathVariable("id") id: Long) = {
    def taskWrapper : Option[Task] = Option(taskRepository.findOne(id))
    if(taskWrapper.isDefined) {
      var task = taskWrapper.get
      task.complited = !task.complited
      ResponseEntity.ok(taskRepository.saveAndFlush(task).toJson)
    }else ResponseEntity.status(HttpStatus.NOT_FOUND)
  }

  @DeleteMapping(value = Array("/{id}"))
  def remove(@PathVariable("id") id: Long) = {
      taskRepository.delete(id)
      ResponseEntity.ok()
  }


}
