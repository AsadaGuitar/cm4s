package com.github.asadaguitar.console.payload.db.field

import java.time.LocalDate

object article {
  type ArticleId = String
  type ArticleTitle = String
  type ArticleContent = String
  type ArticleStartDate = LocalDate
  type ArticleEndDate = LocalDate
}
