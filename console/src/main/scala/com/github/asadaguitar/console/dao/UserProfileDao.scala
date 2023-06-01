package com.github.asadaguitar.console.dao

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.github.asadaguitar.console.payload.db.field.date.ApplicationDatetime
import com.github.asadaguitar.console.payload.db.field.user.{EncryptedUserEmailAddress, EncryptedUserName, HashedUserPassword, UserId}
import com.github.asadaguitar.console.payload.db.row.UserProfile

object UserProfileDao extends DataAccessory {
  import legacy.instant.JavaTimeInstantMeta

  def create(userProfile: UserProfile): IO[Unit] = {
    val UserProfile(
      userId,
      encryptedUsername,
      encryptedUserEmailAddress,
      hashedUserPassword,
      createdAt,
      closedAt
    ) = userProfile
    val now = IO.realTimeInstant.unsafeRunSync()
    sql"""INSERT INTO user_profile(user_id, encrypted_user_name, encrypted_user_email_address, hashed_user_password, created_at, closed_at)
          |VALUES($userId, $encryptedUsername,$encryptedUserEmailAddress, $hashedUserPassword, $now, ${Option(now)});
         """.stripMargin
      .update
      .run
      .transact(xa)
      .void
  }

  def findById(userId: UserId): IO[Option[UserProfile]] = ???

  def findAll: IO[Vector[UserProfile]] = ???

  def update(userProfile: UserProfile): IO[Unit] = ???

  def closeById(userId: UserId): IO[Unit] = ???
}
