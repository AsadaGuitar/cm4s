package com.github.asadaguitar.console.payload.db.field.user

import cats.effect.IO
import com.github.asadaguitar.console.payload.db.field.client.ClientId

import scala.util.Random

case class UserId(value: String) extends AnyVal

object UserId {

  def generate(clientId: ClientId): IO[UserId] = IO {
    UserId(s"${clientId}_USER_${Random.alphanumeric.take(8).mkString}")
  }
}
