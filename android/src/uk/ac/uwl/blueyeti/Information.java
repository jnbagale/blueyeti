package uk.ac.uwl.blueyeti;

public class Information {
	
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

}
