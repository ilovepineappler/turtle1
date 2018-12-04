package com.example.edu.iottermproject.item;

public class TempVO {
	private String num;
	private String loc;
	private String type;
	private String temperature;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getIdate() {
		return idate;
	}
	public void setIdate(String idate) {
		this.idate = idate;
	}
	@Override
	public String toString() {
		return "TempVO [num=" + num + ", loc=" + loc + ", type=" + type + ", temperature=" + temperature + ", idate="
				+ idate + "]";
	}

	
}
