package models

import io.getquill._
import io.getquill.EntityQuery
import configs.QuillContext.ctx
case class Student(sid:Option[Int],name:String,rollno:Int)
class Students{

  import ctx._

  val studentList = quote {
    querySchema[Student]("students",
      _.sid -> "sid",
      _.name -> "name",
      _.rollno -> "rollno",
    )
  }
  def addStud(stud:Student): Option[Int] ={
    val query=quote{
      studentList.insert(lift(stud)).returningGenerated(_.sid)
    }
    ctx.run(query)
  }
  def upStud(stud:Student)={
    val query=quote{
      studentList.filter(_.sid==lift(stud.sid)).update(lift(stud))
    }
    ctx.run(query)
  }
  /*def delStud(sid:Int) ={
    val query=quote{
      studentList.filter(_.sid==lift(sid)).delete
    }
    ctx.run(query)
  }
*/

}
