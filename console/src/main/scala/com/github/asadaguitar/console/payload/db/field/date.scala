package com.github.asadaguitar.console.payload.db.field

import cats.effect.IO

import java.time.{LocalDateTime, ZoneId}

object date {

  final val zoneId = ZoneId.of("Asia/Tokyo")

  type ApplicationDatetime = LocalDateTime

  object ApplicationDatetime {
    def now: IO[ApplicationDatetime] = IO.realTimeInstant.map { instant =>
      LocalDateTime.ofInstant(instant, zoneId)
    }
  }
}
