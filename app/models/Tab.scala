package models

import org.joda.time.DateTime
import scalikejdbc.{WrappedResultSet, _}

case class Tab
(
  id: Long,
  song: String,
  artist: String,
  version: Integer,
  album: String,
  composer: String,
  genre: String,
  year: Integer,
  fileType: String,
  comment: String,
  createdAt: DateTime,
  updatedAt: DateTime
)

object Tab extends SQLSyntaxSupport[Tab] {

  override val tableName = "tab"

  def apply(rs: WrappedResultSet) = new Tab(
    rs.long("id"),
    rs.string("song"),
    rs.string("artist"),
    rs.int("version"),
    rs.string("album"),
    rs.string("composer"),
    rs.string("genre"),
    rs.int("year"),
    rs.string("file_type"),
    rs.string("comment"),
    rs.jodaDateTime("created_at"),
    rs.jodaDateTime("updated_at")
  )

  def findAll(implicit session: DBSession = autoSession): List[Tab] =
    sql"SELECT * FROM tab".map(rs => Tab(rs)).list.apply()

  def insert(tab: Tab)(implicit session: DBSession = autoSession): Long =
    sql"INSERT INTO tab (song, artist, version, album, composer, genre, year, file_type, comment, created_at, updated_at) VALUES (${tab.song}, ${tab.artist}, ${tab.version}, ${tab.album}, ${tab.composer}, ${tab.genre}, ${tab.year}, ${tab.fileType}, ${tab.comment}, current_timestamp, current_timestamp)"
      .update()
      .apply()
}
