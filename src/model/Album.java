package model;

import javafx.beans.property.SimpleStringProperty;

public class Album 
{
	
	private int id;
	public SimpleStringProperty artist = new SimpleStringProperty();
	private SimpleStringProperty name = new SimpleStringProperty();
	
	public Album() 
	{
		this.name.set("");
		this.artist.set("");
		this.id = 0;
		
	}
	
	public Album(String name, String artist, int id)
	{
		this.name.set(name);
		this.artist.set(artist);
		this.id = id;
		
	}
	
	public void setName(String newname) {
		this.name.set(newname);
	}
	
	public String getName()
	{
		return this.name.get();
	}
	
	public String getArtist(){
		return this.artist.get();
	}
	
	
	public boolean equals(Object o)
	{
		Album album = (Album)o;
		if(album.getName().equals(this.name.get())){
			return true;
		}

		return false;
	}
		 
	@Override
	public int hashCode() 
	{
		int hash = 3;
		hash = 7 * hash + this.name.get().hashCode();
		return hash;
	}

}
