package configs

import io.getquill.{MysqlJdbcContext, SnakeCase}

object QuillContext {
  implicit lazy val ctx = new MysqlJdbcContext(SnakeCase, "quillCtx")
}