package com.fullsample.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.fullsample.common.{JsonSupport, SessionQueueSupport, SnappyDataSupport, SparkSessionSupport}
import com.fullsample.message.{Data, Welcome}

import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.util.{Failure, Success}

object Main extends App with JsonSupport with SnappyDataSupport with SessionQueueSupport {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  val route = path("hello") {
    get {
      getSession() match {
        case Success(session) => {
          println(session)
          val existed = session.sqlContext.tableNames().filter(_.equalsIgnoreCase("test")).length > 0
//          if(!existed) {
          var df = session.read.parquet("/Users/liaoyuwen/work/mort/parquet/bigeye_160f77fda14KY9fl0A8MD")
//              .select("mort0", "mort7")
//            df.createOrReplaceTempView("test")
////          }
//
//          df = session.sql("select mort0 as edu, sum(mort7) as cost from test group by mort0")

          //.groupBy("mort0").sum("mort7")


          //          df.sqlContext.sql("select mort0 as edu, sum(mort7) as cost from test group mort0")

//          println("****")
          //          val rows = ListBuffer[List[String]]()
          //          df.collect.foreach(r => {
          //            val list = ListBuffer[String]()
          //            for (i <- 0 to r.size - 1) {
          //              list += r.get(i).toString
          //            }
          //            val rs = list.toList
          //            list.clear()
          //            rows += rs
          //          })
          //          close(session)
          //          val response = Data(rows.toList)
          //          complete(response)
          session.clear()
          close(session)
          complete("")
        }
        case Failure(e) => {
          println(e)
          e.printStackTrace()
          ???
        }
      }

    }
  }
  val port = 6001
  initSession()
  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port)
  println(s"""server listening port:${port}""")
  println("input  command stop  to terminate")
  val line = StdIn.readLine()
  line.toLowerCase match {
    case "stop" => bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
    case _ =>
  }

}
