package uk.ac.uwl.blueyeti.service;

import android.os.Parcel;
import android.os.Parcelable;

public class Information implements Parcelable{
	
	private String title;
	private String description;
	private String sender;
	
	public Information(){
		title="";
		description="";
		sender="";
		
	}
	
	public Information(String aTitle, String aDescription, String aSender){
		title=aTitle;
		description=aDescription;
		sender=aSender;
		
	}
	
	public Information(Parcel in) {
		this.title=in.readString();
		this.description=in.readString();
		this.sender=in.readString();
	}

	public static final Parcelable.Creator<Information> CREATOR = new Parcelable.Creator<Information>(){
		public Information createFromParcel(Parcel in){
			return new Information(in);
		}
		
		public Information[] newArray(int size){
			return new Information[size];
		}
	};
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String toString(){
		return this.title;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
