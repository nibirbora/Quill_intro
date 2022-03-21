package controllers

import models.{Stcourse, Student, StudentCourse, Students}
import play.api.libs.json.{JsObject, Json, OFormat}
//import Services.StudentServices
import play.api.mvc.{AbstractController, BaseController, ControllerComponents}

import javax.inject.Inject

class StudentController @Inject()(val controllerComponents: ControllerComponents,stu:Students,cou:StudentCourse)extends BaseController {

  def createstud()=Action(parse.json){
    val ad = Student(Some(2),"proytush",78)
    stu.addStud(ad)
    Ok("")
  }
  def update=Action(parse.json){
    val up=Student(Some(2),"richad",24)
      stu.upStud(up)
      Ok("")

  }
  /*def delete=Action(parse.json){
    val del=Student(Some(1))

      stu.delStud(del)
    Ok("")

  }
*/
  def createcour()=Action(parse.json) {
    val adcour = Stcourse(Some(1), "chemistry", 25000)
    cou.addCour(adcour)
    Ok("")
  }
  def show()=Action(parse.json){
    val finalResult:List[(Stcourse,Student)]=cou.findAll
    implicit val booksToJson: OFormat[Student] = Json.format[Student]
    implicit val booksToJsonTwo: OFormat[Stcourse] = Json.format[Stcourse]

    val outPut:List[JsObject]= finalResult.map{ case (cats,bookname)=>

      val jsonToo = Json.obj("catagory" -> cats.course,"name"->bookname.name)

      jsonToo
    }
    val jsonObj = Json.toJson(outPut)
    Ok(jsonObj)
  }
}
