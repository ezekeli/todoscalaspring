package springapp.domain

import javax.persistence._

import com.fasterxml.jackson.databind.ObjectMapper
import org.json4s.jackson.JsonMethods.{pretty, render}
import org.json4s.JsonDSL._
import springapp.util.JSONUtil

import scala.beans.BeanProperty
import scala.util.Try


@Entity
@Table(name="tasks")
class Task(t:String, c:String) {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq_gen")
  @SequenceGenerator(name = "tasks_seq_gen", sequenceName = "tasks_id_seq")
  @BeanProperty
  var id: java.lang.Long = _

  @BeanProperty
  var title: String = t

  @BeanProperty
  var complited: Boolean = false

  @BeanProperty
  var content: String = c

  def this() = this (null, null)

  override def toString: String = s"($title: $content)"
  def toJson: String = JSONUtil.toJson(this)
}

object Task {
//  def apply(): Task = new Task(null, null)

//  private val mapper = new ObjectMapper()
//  def toJson(task: Task) = mapper.writeValueAsString(task)
//  def fromJson(string: String):Try[Task] = Try(mapper.readValue(string, classOf[Task]))
}