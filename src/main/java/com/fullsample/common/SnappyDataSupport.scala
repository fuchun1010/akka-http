package com.fullsample.common

import org.apache.spark.sql.{SnappySession, SparkSession}

trait SnappyDataSupport {

  def snappySession(): SnappySession = {
    val sparkSession = SparkSession.builder().appName("akka-http").master("local[*]").getOrCreate()
    val session = new SnappySession(sparkSession.sparkContext)
    session
  }

}
