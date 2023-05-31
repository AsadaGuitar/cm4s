package com.github.asadaguitar.console.entry_point.controller

import akka.http.scaladsl.server.Route
import com.github.asadaguitar.console.entry_point.auth.AuthDirective
import com.typesafe.config.Config

private[entry_point] abstract class ControllerBase(config: Config)
    extends AuthDirective {

  val route: Route
}
