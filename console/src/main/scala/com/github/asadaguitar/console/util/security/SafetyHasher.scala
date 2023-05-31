package com.github.asadaguitar.console.util.security

import cats.effect.IO
import com.github.asadaguitar.console.util.security.types.{HashedValue, LiteralValue}

trait SafetyHasher {

  def hash(value: LiteralValue): IO[HashedValue]

  def verify(literal: LiteralValue, hashed: HashedValue): IO[Boolean]
}
