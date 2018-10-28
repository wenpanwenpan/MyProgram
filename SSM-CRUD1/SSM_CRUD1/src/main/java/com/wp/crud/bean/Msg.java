package com.wp.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ͨ�õķ����࣬����������ݰ�json�ĸ�ʽ���ظ�ǰ̨���󣬲��ҿ��������Լ���Ҫ���ص�����
 * ��������Լ���װ�ģ�����ֱ����ǰ̨����json��ʽ�Ķ��󣬲����پ�����ͼ�������ˡ�
 * @author Administrator
 *
 */
public class Msg {
	
	//״̬��100 ��ʾ�ɹ�   200��ʾʧ��
	private int code;
	
	//��ʾ��Ϣ
	private String msg;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	//Ҫ���ظ������������,��Ҫ���ظ�����ͻ��˵����ݷ�װ��map�з���
	private Map<String, Object> extend = new HashMap<>();
	
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("����ɹ���");
		return result;
	}
	
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("����ʧ�ܣ�");
		return result;
	}
	
	public Msg add(String key,Object value) {
		//����ö����map���������Ҫ���ظ������������
		this.getExtend().put(key, value);
		return this;		//���ص��õĶ���
	}

}
