package db.entity

import org.joda.time._
import scalikejdbc._

case class Member
(
  id: Long,
  name: Option[String],
  createdAt: DateTime
)

object Member extends SQLSyntaxSupport[Member] {

  //override val connectionPoolName = 'testdb // http://stackoverflow.com/questions/32404916/scalikejdbc-wont-connect-to-nameddb-for-dsl-queries-in-scalatest-test-cases
  override val tableName = "members"

  def apply(o: SyntaxProvider[Member])(rs: WrappedResultSet): Member = apply(o.resultName)(rs)

  def apply(o: ResultName[Member])(rs: WrappedResultSet): Member = {
    new Member(
      rs.long("id"),
      rs.stringOpt("name"),
      rs.jodaDateTime("created_at")
    )
  }

  def apply(rs: WrappedResultSet) = new Member(
    rs.long("id"),
    rs.stringOpt("name"),
    rs.jodaDateTime("created_at")
  )
}
