/*
 * Copyright 2011-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vertx.scala.core.streams

import org.vertx.scala.core.Handler
import org.vertx.scala.core.buffer._
import org.vertx.java.core.streams.{ ReadStream => JReadStream }
import org.vertx.java.core.streams.{ ExceptionSupport => JExceptionSupport }

/**
 * Represents a stream of data that can be read from.<p>
 * Any class that implements this interface can be used by a {@link Pump} to pump data from it
 * to a {@link WriteStream}.<p>
 * This interface exposes a fluent api and the type T represents the type of the object that implements
 * the interface to allow method chaining
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 * @author swilliams
 * @author <a href="http://www.campudus.com/">Joern Bernhardt</a>
 */
trait ReadStream extends ExceptionSupport {
  override type InternalType <: JReadStream[_] with JExceptionSupport[_]

  /**
   * Set a data handler. As data is read, the handler will be called with the data.
   */
  def dataHandler(handler: Handler[Buffer]): this.type

  /**
   * Pause the {@code ReadStream}. While the stream is paused, no data will be sent to the {@code dataHandler}
   */
  def pause(): this.type

  /**
   * Resume reading. If the {@code ReadStream} has been paused, reading will recommence on it.
   */
  def resume(): this.type

  /**
   * Set an end handler. Once the stream has ended, and there is no more data to be read, this handler will be called.
   */
  def endHandler(endHandler: Handler[Void]): this.type

  def dataHandler(handler: Buffer => Unit): this.type
  def endHandler(handler: => Unit): this.type
}