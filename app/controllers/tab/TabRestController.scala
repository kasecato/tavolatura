package controllers.tab

import javax.inject.{Inject, Singleton}

import com.github.tototoshi.play2.json4s.Json4s
import models.Tab
import org.json4s.{DefaultFormats, Extraction}
import play.api.mvc._

@Singleton
class TabRestController @Inject()(json4s: Json4s) extends Controller {

  import json4s._

  implicit val formats = DefaultFormats

  def search(title: String) = Action { implicit request =>
    Ok(Extraction.decompose(Tab.findAll))
  }

  def upload = Action(parse.multipartFormData) { request =>
    val artist = request.body.dataParts.get("artist")
    val song = request.body.dataParts.get("song")

    request.body.file("tabs").map { tab =>
      import java.io.File
      val filename = tab.filename
      val contentType = tab.contentType
      tab.ref.moveTo(new File(s"/tmp/$filename"))

      val newTab = new Tab(
        id = 0,
        song = song.get.mkString,
        artist = artist.get.mkString,
        version = 1,
        album = "",
        composer = "",
        genre = "",
        year = 0,
        fileType = "",
        comment = "",
        createdAt = null,
        updatedAt = null
      )
      Extraction.decompose(Tab.insert(newTab))

      Redirect(routes.TabController.upload()).flashing(
        "success" -> "File uploaded")
    }.getOrElse {
      Redirect(routes.TabController.upload()).flashing(
        "error" -> "Missing file")
    }
  }

}
