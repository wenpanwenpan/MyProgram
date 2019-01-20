package com.wp.ct.analysis;

import com.wp.ct.analysis.tool.AnalysisBeanTool;
import com.wp.ct.analysis.tool.AnalysisTextTool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by Administrator on 2019/1/10.
 * 分析数据
 */
public class AnalysisData  {
    public static void main(String[] args) throws Exception{

        //int result = ToolRunner.run(new AnalysisTextTool(), args);
        int result = ToolRunner.run(new AnalysisBeanTool(), args);

    }
}
