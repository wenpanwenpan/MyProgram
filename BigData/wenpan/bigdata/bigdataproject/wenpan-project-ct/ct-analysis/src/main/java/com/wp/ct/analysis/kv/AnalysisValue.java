package com.wp.ct.analysis.kv;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/17.
 * 自定义分析数据value，因为value不需要排序，所以不实现WritableComparable接口
 * 而只需要实现Writable接口就可以了
 */
public class AnalysisValue implements Writable {

    private String sumCall;
    private String sumDuration;

    public AnalysisValue(){}

    public AnalysisValue(String sumCall,String sumDuration){
        this.sumCall = sumCall;
        this.sumDuration = sumDuration;
    }

    public String getSumCall() {
        return sumCall;
    }

    public void setSumCall(String sumCall) {
        this.sumCall = sumCall;
    }

    public String getSumDuration() {
        return sumDuration;
    }

    public void setSumDuration(String sumDuration) {
        this.sumDuration = sumDuration;
    }

    /**
     * 写数据
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(sumCall);
        out.writeUTF(sumDuration);
    }

    /**
     * 读数据
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        this.sumCall = in.readUTF();
        this.sumDuration = in.readUTF();
    }
}
