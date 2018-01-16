package com.fullsample.common

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.fullsample.message.{Person, Welcome}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val personFormat = jsonFormat2(Person)

  implicit val welComeFormat = jsonFormat1(Welcome)

}
