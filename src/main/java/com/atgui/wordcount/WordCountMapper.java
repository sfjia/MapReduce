package com.atgui.wordcount;



import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.zookeeper.txn.Txn;

import java.io.IOException;

/**
 * @Author 贾
 * @Date 2020/8/2214:22
 *
 *Mapper阶段
 *1.用户自定义的Mapper要继承父类的Mapper
 *2.mapper的输入数据是KV对的形式
 * 3.mapper的业务要写在map()方法中
 * 4.mapper的输出数据是KV对的形式
 * 5.map（）方法对每一个<K,V>调用一次
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text k = new Text();
    IntWritable v = new IntWritable(1);
    /**
     *
     * map（）方法对每一个<K,V>调用一次
     * @param key:数据的偏移量
     * @param value：要处理的一行数据
     * @param context：上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.将一行数据装换成string类型
        String line = value.toString();
        //2.切割一行数据
        String[] words = line.split(" ");
        //3.循环写出
        for (String word : words) {
            k.set(word);
            context.write(k,v);
        }
    }
}
