package model;

import javafx.beans.property.SimpleStringProperty;

public class Artist {
	
	private int id;
	private SimpleStringProperty name = new SimpleStringProperty();
	
	public Artist() 
	{
		this.name.set("");
		this.id = 0;
		
	}
	
	public Artist(String name, int id)
	{
		this();
		this.name.set(name);
		this.id = id;
		
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
