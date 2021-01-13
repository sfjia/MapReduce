package com.atgui.wordcount;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileOutputStream;

/**
 * @Author 贾
 * @Date 2020/8/2214:59
 *
 * 相当于yarn的客户端，负责提交MapReduce程序，
 *
 * 1.获取配置信息，获取job实例
 * 2.执定本程序的jar包所在的本地路径
 * 3.关联Mapper/Reduce业务类
 * 4.指定mapper输出数据的KV
 * 5.指定最终输出的数据的KV
 * 6.指定job的输入原始文件所在目录
 * 7.指定job的输出文件结果所在目录
 * 8.提交
 *
 */
public class WordCountDriver {

    public static void main(String[] args) throws Exception {
        //1.获取job对象
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
       // JobConf job = new JobConf();
        //2.指定jar包路径
        job.setJarByClass(WordCountDriver.class);
        //3.关联Mapper/Reduce业务类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);
        //4,指定mapper输出数据的KV
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5.指定最终输出的数据的KV
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //6.指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        //7.指定job的输入原始文件所在目录
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //8.提交 任务完成返回状态
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);


    }
}
