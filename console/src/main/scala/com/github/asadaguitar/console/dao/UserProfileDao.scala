package com.github.asadaguitar.console.dao

import cats.effect.IO
import com.github.asadaguitar.console.payload.db.field.user.UserId
import com.github.asadaguitar.console.payload.db.row.UserProfile

object UserProfileDao {

  def create(userProfile: UserProfile): IO[Unit] = ???

  def findById(userId: UserId): IO[Option[UserProfile]] = ???

  def findAll: IO[Vector[UserProfile]] = ???

  def update(userProfile: UserProfile): IO[Unit] = ???

  def closeById(userId: UserId): IO[Unit] = ???
}
