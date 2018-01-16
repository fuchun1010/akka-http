package com.fullsample.common

import java.util.concurrent.BlockingQueue

import org.apache.spark.sql.{SnappySession, SparkSession}

import scala.util.Try

trait SessionQueueSupport {
  self: SnappyDataSupport =>

    def initSession(): BlockingQueue[SnappySession] = {
      val session = this.createSnappySession()
      for (i <- 1 to 10) {
        this.sessionQueue.add(session.newSession())
      }
      this.sessionQueue
    }

    def getSession(): Try[SnappySession] = Try(this.sessionQueue.take())


    def close(session: SnappySession): Boolean = this.sessionQueue.add(session)

//  def initSession(): BlockingQueue[SparkSession] = {
//    val session = this.createSparkSession()
//    for (i <- 1 to 10) {
//      this.sessionQueue.add(session.newSession())
//    }
//    this.sessionQueue
//  }
//
//
//  def getSession(): Try[SparkSession] = Try(this.sessionQueue.take())
//
//  def close(session: SparkSession): Boolean = this.sessionQueue.add(session)


}
