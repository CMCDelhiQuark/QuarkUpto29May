package com.cmcdelhi.cmcdelhiquark;

import android.os.Parcel;
import android.os.Parcelable;

public class UserFBData implements Parcelable {

	long id;
	String name;
	String fname;
	String lname;
	String locationName;
	String locale;
	String email;
	String gender;
	String link;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public UserFBData() {
		super();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(id);
		out.writeString(name);
		out.writeString(fname);
		out.writeString(lname);
		out.writeString(locationName);
		out.writeString(locale);
		out.writeString(email);
		out.writeString(gender);
		out.writeString(link);
	}

	// this is used to regenerate your object. All Parcelables must have a
	// CREATOR that implements these two methods
	public static final Parcelable.Creator<UserFBData> CREATOR = new Parcelable.Creator<UserFBData>() {
		public UserFBData createFromParcel(Parcel in) {
			return new UserFBData(in);
		}

		public UserFBData[] newArray(int size) {
			return new UserFBData[size];
		}
	};

	// example constructor that takes a Parcel and gives you an object populated
	// with it's values
	public UserFBData(Parcel in) {
		id = in.readLong();

		name = in.readString();
		fname = in.readString();
		lname = in.readString();
		locationName = in.readString();
		locale = in.readString();
		email = in.readString();
		gender = in.readString();
		link = in.readString();

	}

}
