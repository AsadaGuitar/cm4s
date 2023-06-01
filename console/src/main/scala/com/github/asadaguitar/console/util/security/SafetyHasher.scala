package com.github.asadaguitar.console.util.security

import cats.effect.IO
import com.github.asadaguitar.console.util.security.types.{
  HashedValue,
  LiteralValue
}
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

trait SafetyHasher {

  def hash(value: LiteralValue): IO[HashedValue]

  def verify(literal: LiteralValue, hashed: HashedValue): IO[Boolean]
}

object SafetyHasher {

  def impl: SafetyHasher = new SafetyHasher {

    private val bcrypt = new BCryptPasswordEncoder()

    override def hash(value: LiteralValue): IO[HashedValue] = IO(
      bcrypt.encode(value)
    )

    override def verify(
        literal: LiteralValue,
        hashed: HashedValue
    ): IO[Boolean] = IO(bcrypt.matches(literal, hashed))
  }

}
