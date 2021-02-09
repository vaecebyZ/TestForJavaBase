package com.sybinal.shop.model;

public class SlideShow {

	private Integer id;
	private String name;
	private String introduction;
	private String adder;
	private String image;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAdder() {
		return adder;
	}
	public void setAdder(String adder) {
		this.adder = adder;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "SlideShow [id=" + id + ", name=" + name + ", introduction=" + introduction + ", adder=" + adder
				+ ", image=" + image + "]";
	}

			
}
