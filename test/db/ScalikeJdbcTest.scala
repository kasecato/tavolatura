package db

import db.entity.Member
import org.specs2.mutable.Specification
import scalikejdbc._
import scalikejdbc.config._
import scalikejdbc.specs2.mutable.AutoRollback


object MemberSpecTest extends Specification {

  // Load Play Framework Settings (application.conf)
  DBs.setup();

  sequential

  "Map" in new AutoRollbackWithFixture {

    // arrange
    val findAll = sql"select * from members"

    // act
    val entities: List[Map[String, Any]] = findAll.map(_.toMap).list.apply()

    // assert
    val e1 = entities must haveSize(3)
  }

  "Entity" in new AutoRollbackWithFixture {

    // arrange
    val findAll = sql"select * from members"

    // act
    val members: List[Member] = findAll.map(rs => Member(rs)).list.apply()

    // assert
    val e1 = members must haveSize(3)
  }


  "Query API" in new AutoRollbackWithFixture {

    // arrange
    val name = "Alice"

    def findByName(name: String): Option[Member] = {
      sql"select * from members where name = ${name}"
        .map { rs => Member(rs) }
        .single
        .apply()
    }

    // act
    val member: Option[Member] = findByName(name)

    // assert
    val e1 = member.get.id must equalTo(1)
    val e2 = member.get.name.get must equalTo("Alice")
  }

}

trait AutoRollbackWithFixture extends AutoRollback {

  override def fixture(implicit session: DBSession) {

    // arrange
    sql"""drop table if exists members;""".execute.apply()

    sql"""create table members (id serial not null primary key, name varchar(64), created_at timestamp not null)""".execute.apply()

    Seq("Alice", "Bob", "Chris") foreach { name =>
      sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
    }
  }
}
