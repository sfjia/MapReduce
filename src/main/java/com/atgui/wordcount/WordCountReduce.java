package com.atgui.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author 贾
 * @Date 2020/8/2214:50
 *
 *1.用户自定义的reduce要继承自己的父类
 * 2.reduce的输入数据类型对应的mapper的输出数据类型也是KV
 * 3.reduce的业务逻辑写在reduce（）方法中
 * 4.reduceTask进程对每一组相同的K的<K,V>组调用一次reduce（）方法
 *
 */
public class WordCountReduce extends Reducer<Text, IntWritable,Text,IntWritable> {
    /**
     * <atguigu,1><atguigu,1><atguigu,1><atguigu,1><atguigu,1>
     * <hadoop,1><hadoop,1><hadoop,1><hadoop,1><hadoop,1><hadoop,1>
     * @param key:单词
     * @param values：单词个数的集合
     * @param context：上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //1.累加
        int sum= 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        context.write(key,new IntWritable(sum));
    }
}
