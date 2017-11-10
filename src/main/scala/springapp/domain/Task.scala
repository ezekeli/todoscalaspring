package springapp.domain

import javax.persistence._
import org.json4s.jackson.JsonMethods.{pretty, render}
import org.json4s.JsonDSL._
import scala.beans.BeanProperty


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

  def toJson: String = pretty(render(("title" -> this.title) ~ ("content" -> this.content)))
}