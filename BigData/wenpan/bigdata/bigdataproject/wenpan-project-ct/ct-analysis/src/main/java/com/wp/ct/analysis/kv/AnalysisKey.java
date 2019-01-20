package com.wp.ct.analysis.kv;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/17.
 * 自定义分析数据的key
 * 因为key需要进行比较排序，所以需要实现WritableComparable接口
 */
public class AnalysisKey implements WritableComparable<AnalysisKey> {

    private String tel;
    private String date;

    public AnalysisKey(){}

    public AnalysisKey(String tel,String date){
        this.tel = tel;
        this.date = date;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 比较
     * @param key
     * @return
     */
    @Override
    public int compareTo(AnalysisKey key) {
        int result = this.tel.compareTo(key.getTel());

        //若Tel相同则比较date
        if(result == 0){
            result = this.date.compareTo(key.getDate());
        }

        return result;
    }

    /**
     * 写数据
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(tel);
        out.writeUTF(date);
    }

    /**
     * 读数据
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        tel = in.readUTF();
        date = in.readUTF();
    }
}
