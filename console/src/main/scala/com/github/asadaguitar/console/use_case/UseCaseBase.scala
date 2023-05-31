package com.github.asadaguitar.console.use_case

import cats.effect.IO

private[use_case] trait UseCaseBase {

  type Command
  type Result

  def run(command: Command): IO[Result]
}
