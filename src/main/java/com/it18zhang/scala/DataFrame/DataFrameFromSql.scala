package com.it18zhang.scala.DataFrame

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.linalg.{Vector, Vectors}

/**
  * 从数据库中获取DataFrame
  * @author zong
  */

object DataFrameFromSql {
  def main (args: Array[String ]) {
    val sparkConf = new SparkConf().setAppName("Spark SQL DataFrame Operations").setMaster("local[2]")
    val sparkContext = new SparkContext(sparkConf)

    val sqlContext = new SQLContext(sparkContext)
    //192.168.190.1
    val url = "jdbc:mysql://localhost:3306/ai"

    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> url,
        "user" -> "root",
        "password" -> "1234",
        "dbtable" -> "wine")).load()
     jdbcDF.show();

    val sess = SparkSession.builder().appName("Spark SQL DataFrame Operations").master("local[4]").getOrCreate();
    val sc = sess.sparkContext;

    import sess.implicits._
    //数据文件目录，一会儿不要忘记写
    val dataDir="W:\\bigdatalearn\\数据\\wine\\winequality-red1.csv";
    //定义样例类
    case class Wine(FixedAcidity: Double, VolatileAcidity: Double,
                    CitricAcid: Double, ResidualSugar: Double, Chlorides: Double,
                    FreeSulfurDioxide: Double, TotalSulfurDioxide: Double, Density: Double, PH:
                    Double, Sulphates: Double, Alcohol: Double, Quality: Double)
    //创建RDD
    //把每一行根据“；”切割，并且映射为wine对象（这个对象就是上面那个样例类的实例化），每个表头都是double类型
    val wineDataRDD = sc.textFile(dataDir).map(_.split(";")).map(w => Wine(w(0).toDouble, w(1).toDouble,
      w(2).toDouble, w(3).toDouble, w(4).toDouble, w(5).toDouble, w(6).toDouble, w(7).toDouble, w(8).toDouble
      , w(9).toDouble, w(10).toDouble, w(11).toDouble))

    //将rdd转化成DataFrame
    //转换RDD成DataFrame

    val trainingDF = wineDataRDD.map(w => (if (w.Quality < 7) 0D else 1D,
      Vectors.dense(w.FixedAcidity, w.VolatileAcidity, w.CitricAcid,
        w.ResidualSugar, w.Chlorides, w.FreeSulfurDioxide, w.TotalSulfurDioxide,
        w.Density, w.PH, w.Sulphates, w.Alcohol))).toDF("label", "features")

    trainingDF show();
  }
}
