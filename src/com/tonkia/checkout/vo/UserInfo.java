package com.tonkia.checkout.vo;

import java.sql.Date;

public class UserInfo {
	private Integer id;
	private String phone;
	private String pwd;
	// 获取验证码
	private String identifyCode;
	private Date identifyTime;
	// 用户数据相关
	private String dataPath;
	private Date dataTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getIdentifyTime() {
		return identifyTime;
	}

	public void setIdentifyTime(Date identifyTime) {
		this.identifyTime = identifyTime;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

}
