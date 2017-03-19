package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import launch.MasterController;
import model.Playlist;

public class SidebarController {

	private ObservableList<Playlist> playlists;

	public SidebarController() {
		playlists = FXCollections.observableArrayList(MasterController.getInstance().getGateway().getPlaylists());
	}

	public ObservableList<Playlist> getPlaylists() {

		return playlists;

	}

	public void addPlaylist(Playlist pl) {
		playlists.add(pl);
	}

	public Playlist getPlaylistById(int id) {

		for (Playlist pl : playlists) {
			if (pl.getId() == id) {
				System.out.println("PLAYLIST ID: " + pl.getId());
				return pl;
			}
		}
		
		return null;

	}
}
