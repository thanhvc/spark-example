/*
 * Copyright (C) 2009-2016 Eway Company.
 * 
 * All rights reserved.
 *
 */
package spark;

import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

/**
 * Created by The Eway Company
 * Author : Eway
 *          eway@eway.vn
 * Sep 8, 2016  
 */

public class SimpleApp {
  public static void main(String[] args) {
    // Should be some file on your system
    String logFile = "/usr/local/spark-1.6.1-bin-hadoop2.6/bin/README.md"; 
    SparkConf conf = new SparkConf().setAppName("Simple Application");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> logData = sc.textFile(logFile).cache();

    long numSparks = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) {
        return s.contains("Spark");
      }
    }).count();

    long numApaches = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) {
        return s.contains("Apache");
      }
    }).count();

    System.out.println("Lines with Spark: " + numSparks + ", lines with Apache: " + numApaches);
  }
}