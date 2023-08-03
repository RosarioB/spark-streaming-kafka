package com.rosariob.kafka

import org.apache.spark.sql.types.{DateType, DoubleType, IntegerType, LongType, StringType, StructField, StructType, TimestampType}

object Schema {
  val schema: StructType = StructType(Array(
    StructField("VP", StructType(Array(
      StructField("desi", StringType),
      StructField("dir", StringType),
      StructField("oper", IntegerType),
      StructField("veh", IntegerType),
      StructField("tst", TimestampType),
      StructField("tsi", LongType),
      StructField("spd", DoubleType),
      StructField("hdg", IntegerType),
      StructField("lat", DoubleType),
      StructField("long", DoubleType),
      StructField("acc", DoubleType),
      StructField("dl", IntegerType),
      StructField("odo", StringType),
      StructField("drst", StringType),
      StructField("oday", DateType),
      StructField("jrn", IntegerType),
      StructField("line", IntegerType),
      StructField("start", StringType),
      StructField("loc", StringType),
      StructField("stop", LongType),
      StructField("route", StringType),
      StructField("occu", IntegerType),
    )))
  ))
}
