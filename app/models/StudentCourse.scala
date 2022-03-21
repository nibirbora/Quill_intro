package models

import io.getquill._
import io.getquill.EntityQuery
import configs.QuillContext.ctx

import javax.inject.Inject

case class Stcourse(cid:Option[Int],course: String,fee:Int)
class StudentCourse @Inject()(studs:Students){

  import ctx._

  val courseList = quote {
    querySchema[Stcourse]("course",
      _.cid -> "cid",
      _.course -> "course",
      _.fee -> "fee",
    )
  }
  def addCour(cour:Stcourse): Option[Int] ={
    val query=quote{
      courseList.insert(lift(cour)).returningGenerated(_.cid)
    }
    ctx.run(query)
  }
  def findAll:List[(Stcourse,Student)]={
    val q=quote{
      courseList.join(studs.studentList).on(_.cid==_.sid)
    }
    ctx.run(q)
  }
}
