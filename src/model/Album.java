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
	
<<<<<<< HEAD
	
	public boolean equals(Object o)
	{
		Album album = (Album)o;
		if(album.getName().equals(this.name.get())){
			System.out.println("already exists");
			return true;
		}
		System.out.println("DOESNT already exists");

		return false;
	}
		 
	@Override
	public int hashCode() 
	{
		int hash = 3;
		hash = 7 * hash + this.name.get().hashCode();
		return hash;
	}
=======
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
>>>>>>> e8d52cb87315b80b3d09d76ed8c5c8e7253dbfab

}
