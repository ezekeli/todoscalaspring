package springapp.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import springapp.repository.TaskRepository


@Controller
class ViewController(val taskRepository: TaskRepository) {

  @RequestMapping(Array("/"))
  def addTask(model: Model): String  = {
    model.addAttribute("tasks", taskRepository.findAll())
    "tasktmpl"
  }
}
