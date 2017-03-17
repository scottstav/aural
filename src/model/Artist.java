package model;

import javafx.beans.property.SimpleStringProperty;

public class Artist {
	
	private int id;
	public int album_id;
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
	
	public boolean equals(Object obj)
	{
	    if(obj == null || getClass() != obj.getClass())
            return false;
	    Artist a = (Artist) obj;
	    return getName().equals(a.getName());
	}
	
	public String toString() {
		return getName();
	}

}
