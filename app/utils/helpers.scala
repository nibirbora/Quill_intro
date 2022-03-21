package utils

import io.getquill.MappedEncoding
import io.getquill.mirrorContextWithQueryProbing.{InfixInterpolator, quote}
import org.joda.time.DateTime

object QuillUtil {
  val mysqlDateTimeFormat = "yyyy-MM-dd HH:mm:ss"
  val mysqlDateFormat = "yyyy-MM-dd"
  val patternDate = "[0-9]{4}-[0-9]{2}-[0-9]{2}$".r

  implicit val decodeDateTimeQuill: MappedEncoding[String, DateTime] = MappedEncoding[String, DateTime](dtStr => strToJodaDateTime(dtStr))
  implicit val encodeDateTimeQuill: MappedEncoding[DateTime, String] = MappedEncoding[DateTime, String](dateTime => jodaDateTimeStr(dateTime))

  implicit class DateTimeQuotes(left: DateTime) {
    def >(right: DateTime) = quote(infix"$left > $right".as[Boolean])

    def <(right: DateTime) = quote(infix"$left < $right".as[Boolean])

    def >=(right: DateTime) = quote(infix"$left >= $right".as[Boolean])

    def <=(right: DateTime) = quote(infix"$left <= $right".as[Boolean])
  }

  def jodaDateTimeStr(dateTime: org.joda.time.DateTime): String = {
    val fmt = org.joda.time.format.DateTimeFormat.forPattern(mysqlDateTimeFormat)
    dateTime.toString(fmt)
  }

  def strToJodaDateTime(dateTimeStr: String): org.joda.time.DateTime = {
    val patternMaxLength: Int = 19
    val dtStr = dateTimeStr.trim.take(patternMaxLength)
    val dateTimeFormat = if (patternDate.findFirstMatchIn(dtStr).isDefined) {
      mysqlDateFormat
    } else mysqlDateTimeFormat

    org.joda.time.format.DateTimeFormat.forPattern(dateTimeFormat).parseDateTime(dateTimeStr.take(patternMaxLength))
  }
}