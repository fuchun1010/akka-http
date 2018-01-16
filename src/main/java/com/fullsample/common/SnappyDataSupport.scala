package com.fullsample.common

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue, LinkedBlockingQueue}

import org.apache.spark.sql.{SnappySession, SparkSession}

trait SnappyDataSupport {

  def createSnappySession(): SnappySession = {
    val sparkSession = SparkSession.builder().appName("akka-http").master("local[*]").getOrCreate()
    val session = new SnappySession(sparkSession.sparkContext)
    session
  }

  val sessionQueue: BlockingQueue[SnappySession] = new ArrayBlockingQueue[SnappySession](10)



  //
  //  def size = 10
  //
  //  def globalSessionQueue = initSession
  //
  //  def initSession(): BlockingQueue[SnappySession] = {
  //    var i = 0
  //    val snappySession: SnappySession = createSnappySession
  //    val blockingQueue: BlockingQueue[SnappySession] = new ArrayBlockingQueue[SnappySession](size)
  //    while(i < 10) {
  //      blockingQueue.add(snappySession.newSession)
  //      i += 1
  //    }
  //
  //    blockingQueue
  //  }
  //
  //  def getSession(): SnappySession = {
  //    globalSessionQueue.take()
  //  }
  //
  //  def close(snappySession: SnappySession): Unit = {
  //    globalSessionQueue.put(snappySession)
  //  }

}
