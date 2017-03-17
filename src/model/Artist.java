package model;

import javafx.beans.property.SimpleStringProperty;

public class Artist {
	
	private int id;
	private int album_id;
	private SimpleStringProperty name = new SimpleStringProperty();
	
	public Artist() 
	{
		this.name.set("");
		this.id = 0;
		this.album_id = 0;
		
	}
	
	public Artist(String name, int album_id, int id)
	{
		this();
		this.name.set(name);
		this.id = id;
		this.album_id = album_id;
		
	}
	
	public void setName(String newname) {
		this.name.set(newname);
	}
	
	public String getName()
	{
		return this.name.get();
	}
	
	public String toString() {
		return getName();
	}

}
