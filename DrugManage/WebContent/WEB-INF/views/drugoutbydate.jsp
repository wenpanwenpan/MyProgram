<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>按日期出库</title>
</head>
<body>
<center>
	<div style="align-content: center;background-color:#D6D5B7;width: 600px;text-align: center;">
		<font color="black">
		<p align="left">药品有效期简介：<p>
		 药品有效期是指该药品被批准的使用期限，表示该药品在规定的贮存条件下能够保证质量的期限。
		药品有效期是衡量药品安全性和有效性的重要指标，过期药品对正常的药品市场及人民群众的身
		体健康和生命安全有严重影响。因此，认清过期药品的危害性，采取有效措施回收过期药品，是
		确保人体用药安全、维护人民健康的客观要求。
		<p>出库须知：</p>
		<p style="color: red">若出库未过期的药品，是对国家药品资源的一种极大的浪费</p>
		<p style="color: red">若过期的药品不出库，则是对广大任命群众的健康不负责任</p>
		<h5>所以基于以上规定，您应该谨慎出库药品！</h5>
		</font>
		
		<form action="drugOutByDate" method="post">
			药品入库时间：<input type="text" name = "date" placeholder="日期格式：yyyy-MM-dd"><br>
			<input type="submit" value="确认出库">
			<input type="reset" value="取消出库">
		</form>
		
	</div>
	

	
	
</center>	
</body>
</html>