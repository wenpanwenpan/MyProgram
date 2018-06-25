function validateName(name){
	if(document.getElementById("name").value == ""){
		document.getElementById("name_msg").innerHTML = "<font color = \"red\">商品名称不能为空！</font>";
		return false;
	}else{
		document.getElementById("name_msg").innerHTML = "<font color = \"green\">商品名称输入正确！</font>";
		return true;
	}	
}
function validatePrice(price){
	if(!(/^\d+(\.\d+)?$/.test(price.value))){
		document.getElementById("price_msg").innerHTML = "<font color = \"red\">商品价格输入错误！</font>";
		return false;
	}else{
		document.getElementById("price_msg").innerHTML = "<font color = \"green\">商品价格输入正确！</font>";
		return true;
	}	
}
function validateAmount(amount){
	if(!(/^\d+$/.test(amount.value))){
		document.getElementById("amount_msg").innerHTML = "<font color = \"red\">商品价格输入错误！</font>";
		return false;
	}else{
		document.getElementById("amount_msg").innerHTML = "<font color = \"green\">商品价格输入正确！</font>";
		return true;
	}	
}
function validate(f){
	return validateName(f.name) && validatePrice(f.price) && validateAmount(f.amount);
}




/*function validatePassword(password){
	if(!(/\w+/.test(password))){
		document.getElementById("password_msg").innerHTML = "<font color = \"red\">密码不能为空！</font>";
		return false;
	}else{
		document.getElementById("password_msg").innerHTML = "<font color = \"green\">密码输入正确！</font>";
		return true;
	}	
}
function validateConf(conf){
	if(document.getElementById("password").value != conf){
		document.getElementById("conf_msg").innerHTML = "<font color = \"red\">密码不一致！</font>";
		return false;
	}else{
		if(conf == ""){
			document.getElementById("conf_msg").innerHTML = "<font color = \"red\">密码不能为空！</font>";
			return false;
		}else{
			document.getElementById("conf_msg").innerHTML = "<font color = \"green\">密码验证通过！</font>";
			return true;
		}	
	}	
}
function validateName(name){
	if(document.getElementById("name").value == ""){
		document.getElementById("name_msg").innerHTML = "<font color = \"red\">姓名不能为空！</font>";
		return false;
	}else{
		document.getElementById("name_msg").innerHTML = "<font color = \"green\">姓名验证通过！</font>";
		return true;
	}	
}
function validateAddress(address){
	if(!(/\w+/.test(address))){
		document.getElementById("address_msg").innerHTML = "<font color = \"red\">地址不能为空！</font>";
		return false;
	}else{
		document.getElementById("address_msg").innerHTML = "<font color = \"green\">地址输入正确！</font>";
		return true;
	}	
}
function validateTelephone(telephone){
	if(telephone == ""){
		document.getElementById("telephone_msg").innerHTML = "<font color = \"red\">电话不能为空！</font>";
		return false;
	}else{
		document.getElementById("telephone_msg").innerHTML = "<font color = \"green\">联系电话输入正确！</font>";
		return true;
	}	
}
function validateZipcode(zipcode){
	if(!(/^\d{6}\b/.test(zipcode))){
		document.getElementById("zipcode_msg").innerHTML = "<font color = \"red\">邮编格式错误！</font>";
		return false;
	}else{
		document.getElementById("zipcode_msg").innerHTML = "<font color = \"green\">邮编输入正确！</font>";
		return true;
	}	
}

function validate(f){
	return validateMid(f.mid.value) && validatePassword(f.password.value) && validateConf(f.conf.value) && validateAddress(f.address.value)
	&& validateTelephone(f.telephone.value) && validateZipcode(f.zipcode.value);
}
*/












