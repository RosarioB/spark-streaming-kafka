package com.rosariob.kafka

import org.apache.spark.sql.functions.{from_json, window}
import org.apache.spark.sql.streaming.{OutputMode, StreamingQuery}
import org.apache.spark.sql.{DataFrame, SparkSession}
import Schema.schema

object Main extends App {

  println("### Starting application PID " + ProcessHandle.current.pid + " ###")

  val spark: SparkSession = SparkSession
  .builder()
  .appName("Spark Structured Streaming example with MQTT client")
  .master("local[*]")
  .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  import spark.implicits._

  val lines = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092") // localhost:9092 local, broker:29092 docker
    .option("subscribe", "vehicle-positions")
    .option("startingOffsets", "earliest")
    .load()
    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")

  val select: DataFrame = lines
    .select(from_json($"value", schema).as("json"))
    .select("json.VP.*")

  select.printSchema()

  val df: DataFrame = select.groupBy(
    window($"tst", "10 minutes", "10 minutes"),
    $"route"
  ).count()

  val query: StreamingQuery = df.writeStream
    .format("console")
    .option("truncate", "false")
    .outputMode(OutputMode.Update())
    .queryName("counts")
    .start()

  query.awaitTermination();
}
