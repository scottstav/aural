package controller;

import model.Profile;

public class PreferencesViewController
{
	
	private Profile profile;
	
    public PreferencesViewController(Profile val)
    {
    	this.setProfile(val);
    }

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
