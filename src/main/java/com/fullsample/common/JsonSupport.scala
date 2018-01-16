package com.fullsample.common

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.fullsample.message.{Data, Person, Welcome}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val personFormat = jsonFormat2(Person)

  implicit val welComeFormat = jsonFormat1(Welcome)

  implicit val dataFormat = jsonFormat1(Data[String])

}
