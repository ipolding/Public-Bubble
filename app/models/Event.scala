package models

import anorm.{Row, SQL, SqlQuery}
import play.api.db.DB
import play.api.Play.current

/**
 * Created by ian on 24/02/15.
 */
case class Event (id : Long, title: String, location: String, description: String)

object Event {

  val GET_ALL_EVENTS_SQL : SqlQuery = SQL("select * from EVENTS order by id desc")
  val GET_EVENT_BY_ID_SQL : SqlQuery = SQL("select * from EVENTS where id = {id}")

  // TODO incorporate publish date
  val GET_LATEST_EVENT : SqlQuery = SQL("select * from EVENTS order by id desc LIMIT 1")

  def getLatest : Event = DB.withConnection{
        implicit connection =>
          val row : Row = GET_LATEST_EVENT.apply().head;
          Event(
            row[Long] ("id"),
            row[String] ("title"),
            row[String] ("location"),
            row[String] ("description")
          )

  }

  def getById(eventId : Long) : Event = DB.withConnection {
    implicit connection =>
      val row = GET_EVENT_BY_ID_SQL.on("id" -> eventId).apply().head;
      Event(
          row[Long] ("id"),
          row[String] ("title"),
          row[String] ("location"),
          row[String] ("description")
        )
  }

}




