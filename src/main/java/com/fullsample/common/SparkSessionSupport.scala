package com.fullsample.common

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}

import org.apache.spark.sql.{SnappySession, SparkSession}

trait SparkSessionSupport {

  val sessionQueue: BlockingQueue[SparkSession] = {
    println("init block queue")
    new ArrayBlockingQueue[SparkSession](10)
  }

  def createSparkSession(): SparkSession = SparkSession.builder().appName("xx").master("local[*]").getOrCreate()

}
