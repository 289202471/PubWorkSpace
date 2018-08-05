package com.it18zhang.scala.ML;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;


//java版的机器学习算法
public class MLDemo {
    public static void main(String[] args) {
//        JavaSparkContext jsc=JavaSparkContext.fromSparkContext();
       SparkConf conf=new SparkConf();
       conf.setMaster("local[4]");
       conf.setAppName("ml");
       JavaSparkContext jsc=new JavaSparkContext(conf);
       SparkSession sess=SparkSession.builder().config(conf)
//               .appName("SQLJava")
//               .config("spark.master","local")
               .getOrCreate();


        JavaRDD<String> rdd1=jsc.textFile("");
        JavaRDD<Row> rdd2 = rdd1.map(new Function<String,Row>() {
            public Row call(String s) throws Exception{
                String[] arr=s.split(";");
                double label=Double.parseDouble(arr[11]);
                Vector v=Vectors.dense(Double.parseDouble(arr[0]),
                        Double.parseDouble(arr[1]),
                        Double.parseDouble(arr[2]),
                        Double.parseDouble(arr[3]),
                        Double.parseDouble(arr[4]),
                        Double.parseDouble(arr[5]),
                        Double.parseDouble(arr[6]),
                        Double.parseDouble(arr[7]),
                        Double.parseDouble(arr[8]),
                        Double.parseDouble(arr[9]),
                        Double.parseDouble(arr[10])
                );
                Row row= RowFactory.create(label,v);
//                return new Tuple2<Double, Vector>(label,v);
                //return null;
                return row;
            }
        });


       // sess.createDataFrame(rdd2, StructType.fromAttributes("label","features"));

//       String url="jdbc:mysql://192.168.231.1:3306/mybatis";
//       String table="persons";
//        SparkContext jsc=sess.sparkContext();
//        RDD<String> rdd1=jsc.textFile("file:///");
//        rdd1.map(new MapFunction<String, Tuple2<Double,Vector>>() {
//            public Tuple2<Double,Vector> call(String value)throws Exception{
//                String[] arr=value.split(";");
//                Vector v=Vectors.dense(Double.parseDouble(arr[0]),
//                        Double.parseDouble(arr[1]),
//                        Double.parseDouble(arr[2]),
//                        Double.parseDouble(arr[3]),
//                        Double.parseDouble(arr[4]),
//                        Double.parseDouble(arr[5]),
//                        Double.parseDouble(arr[6]),
//                        Double.parseDouble(arr[7]),
//                        Double.parseDouble(arr[8]),
//                        Double.parseDouble(arr[9]),
//                        Double.parseDouble(arr[10])
//                );
//                return new Tuple2<Double, Vector>(Double.parseDouble(arr[11]),Ver);
//            }
//        });
    }
}
