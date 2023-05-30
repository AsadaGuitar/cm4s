package com.github.asadaguitar.console.entry_point.marshaller

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.github.asadaguitar.console.payload.db.field.user.UserId
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait UserInfoMarshaller extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val userIdMarshaller: RootJsonFormat[UserId] = jsonFormat1(UserId)
}
