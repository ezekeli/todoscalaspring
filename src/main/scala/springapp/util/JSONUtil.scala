package springapp.util

import com.fasterxml.jackson.databind.ObjectMapper
import springapp.domain.Task
import scala.util.Try

object JSONUtil {
  private val mapper = new ObjectMapper()
  def toJson(task: Task) = mapper.writeValueAsString(task)
  def fromJson(string: String, T:Class[_]) = Try(mapper.readValue(string, T))
}
