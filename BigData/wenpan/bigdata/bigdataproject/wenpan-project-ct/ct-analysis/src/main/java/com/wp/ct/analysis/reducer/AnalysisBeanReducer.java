package com.wp.ct.analysis.reducer;

import com.wp.ct.analysis.kv.AnalysisKey;
import com.wp.ct.analysis.kv.AnalysisValue;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Administrator on 2019/1/10.
 * 分析数据的reducer
 */
public class AnalysisBeanReducer extends Reducer<AnalysisKey,Text,AnalysisKey,AnalysisValue> {
    @Override
    protected void reduce(AnalysisKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        int sumCall = 0;
        int sumDuration = 0;

        for (Text value : values) {
            int duration = Integer.parseInt(value.toString());
            sumDuration = sumDuration + duration;

            sumCall++;
        }
        //System.out.println("key的值：  " + key.toString());

        context.write(key,new AnalysisValue("" + sumCall, "" + sumDuration));

    }
}
