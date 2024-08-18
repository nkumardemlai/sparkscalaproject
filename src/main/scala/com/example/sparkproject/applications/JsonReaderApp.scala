package com.example.sparkproject.applications

import com.example.sparkproject.inits.SparkAppInitializer
import org.apache.spark.sql.DataFrame

object JsonReaderApp extends App {

  val init = new SparkAppInitializer(args(0), args(1))
  val spark = init.spark

  // Read the JSON file into a DataFrame
  private val file_json_input = init.getProperty("file_json_input")

  private val jsonData: DataFrame = spark.read.format("json")
    .option("inferSchema", "true").option("multiline", "true")
    .option("mode", "DROPMALFORMED")
    .load(file_json_input)

  jsonData.createOrReplaceTempView("bisket")

  // Display the DataFrame content
  spark.sql("select * from bisket").show(false)

  // Stop the SparkSession
  spark.stop()
}

