package com.cmcdelhi.cmcdelhiquark;

public class TypeARowItem {
	private int imageId;
	private String courseTitle;
	private String[] courseTypes;
	private String courseDesc;

	public TypeARowItem(int imageId, String courseTitle, String[] courseTypes,
			String courseDesc) {

		this.imageId = imageId;
		this.courseTitle = courseTitle;
		this.courseTypes = courseTypes;
		this.courseDesc = courseDesc;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String[] getCourseTypes() {
		return courseTypes;
	}

	public void setCourseTypes(String[] courseTypes) {
		this.courseTypes = courseTypes;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

}
