package com.fullsample.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.fullsample.common.{JsonSupport, SnappyDataSupport}
import com.fullsample.message.Welcome
import scala.io.StdIn

object Main extends App with JsonSupport with SnappyDataSupport {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  val route = path("hello") {
    get {
      complete(Welcome("hello akka http"))
    }
  }
  val port = 6001
  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)
  println(s"""server listening port:${port}""")
  println("input  command stop  to terminate")
  val line = StdIn.readLine()
  line.toLowerCase match {
    case "stop" => bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
    case _ =>
  }

}
