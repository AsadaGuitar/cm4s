package com.github.asadaguitar.console.dao

import cats.effect.IO
import doobie.util.transactor.Transactor

private[dao] trait DataAccessory
    extends doobie.free.Instances
    with doobie.syntax.AllSyntax
    with doobie.util.meta.LegacyMeta {

  final protected val xa = Transactor.fromDriverManager[IO](
    "com.mysql.cj.jdbc.Driver",
    "jdbc:mysql://localhost:3306/cm4s?enabledTLSProtocols=TLSv1.2",
    "root",
    "P@ssw0rd"
  )

}
