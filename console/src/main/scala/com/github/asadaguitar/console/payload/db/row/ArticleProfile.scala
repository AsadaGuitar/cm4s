package com.github.asadaguitar.console.payload.db.row

import com.github.asadaguitar.console.payload.db.field.article._
import com.github.asadaguitar.console.payload.db.field.date.ApplicationDatetime
import .UserId

case class ArticleProfile(
    articleId: ArticleId,
    articleTitle: ArticleTitle,
    articleContent: ArticleContent,
    articleStartDate: ArticleStartDate,
    articleEndDate: ArticleEndDate,
    createdBy: UserId,
    closedBy: Option[UserId],
    createdAt: ApplicationDatetime,
    closedAt: Option[ApplicationDatetime]
)
