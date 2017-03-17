package model;

import javafx.beans.property.SimpleStringProperty;

public class Album 
{
	
	private int id;
	public int artist_id;
	private SimpleStringProperty name = new SimpleStringProperty();
	
	public Album() 
	{
		this.name.set("");
		this.artist_id = 0;
		this.id = 0;
		
	}
	
	public Album(String name, int artist_id, int id)
	{
		this.name.set(name);
		this.artist_id = artist_id;
		this.id = id;
		
	}
	
	public void setName(String newname) {
		this.name.set(newname);
	}
	
	public String getName()
	{
		return this.name.get();
	}
	
	public int getArtistId(){
		return this.artist_id;
	}
	
	public boolean equals(Object obj)
    {
        if(obj == null || getClass() != obj.getClass())
            return false;
        Album a = (Album) obj;
        return getName().equals(a.getName());
    }

}
