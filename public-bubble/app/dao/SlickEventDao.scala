package models

import java.sql.Date

import dao.EventDao
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.lifted.Tag
import slick.driver.PostgresDriver.api._

trait EventsComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  class Blogs(tag: Tag) extends Table[Event](tag, "event") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def author = column[String]("location")
    def intro = column[String]("description")
    def displayFrom = column[Date]("display_from")
    def displayUntil = column[Date]("display_until")
    def image1Url = column[Option[String]]("image_1_url")

    def * = (id.?, title, author, intro, displayFrom, displayUntil, image1Url) <> ((Event.apply _).tupled, Event.unapply)
 }

}

class SlickEventDao extends HasDatabaseConfig[JdbcProfile] with EventDao with EventsComponent  {
  override protected val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  override def addImage(id: Long, url: String): Event = ???

  override def update(event: Event): Unit = ???

  override def delete(id: Int): Unit = ???

  override def getById(eventId: Long): Event = ???

  override def extract(event: Event): Unit = ???

  override def create(event: Event): Option[Long] = ???

  override def getLatest: Option[Event] = ???

  override def getAll: List[Event] = ???
}


