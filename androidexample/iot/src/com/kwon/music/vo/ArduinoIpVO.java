package com.kwon.music.vo;

public class ArduinoIpVO {

	private String num;
	private String loc;
	private String ip;
	private String idate;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIdate() {
		return idate;
	}
	public void setIdate(String idate) {
		this.idate = idate;
	}
	@Override
	public String toString() {
		return "ArduinoIVO [num=" + num + ", loc=" + loc + ", ip=" + ip + ", idate=" + idate + "]";
	}

}
