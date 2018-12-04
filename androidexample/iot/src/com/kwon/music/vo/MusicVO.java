package com.kwon.music.vo;

public class MusicVO {
	private String num;
	private String title;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "MusicVO [num=" + num + ", title=" + title + ", getNum()=" + getNum() + ", getTitle()=" + getTitle()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
