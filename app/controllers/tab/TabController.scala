package controllers.tab

import javax.inject.Singleton

import models.Tab
import play.api.mvc._

@Singleton
class TabController() extends Controller {

  def search = Action {
    Ok(views.html.tab.search(Tab.findAll))
  }

  def upload = Action {
    Ok(views.html.tab.upload())
  }

  def editor = Action {
    Ok(views.html.tab.editor())
  }

}
