package gateway;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import model.SongEntry;

/**
 * create a DB object that creates a connection , inserts a record, and prints
 * the id of the newly inserted record a better approach
 * 
 * @author marcos
 *
 */
public class SongGateway {
	private Connection conn = null;
	private Logger logger;
	private ArrayList<SongEntry> songList;

	public SongGateway() {
		this.logger = LogManager.getLogger();
		songList = new ArrayList<SongEntry>();
		try {
			this.connectToDB();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// do stuff with the database with PreparedStatements, like issue SQL
		// queries
		// this.fetchStuff();

		// always close the connection when done
		// this.close();
	}

	/*
	 * insert a new song record
	 */
	public void insertStuff(SongEntry a) {
		// create a prepared statement using an SQL query
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// insert a record
			// using a parameterized query with 6 parameters
			String query = "insert into songs " + " (path, title, artist, album, length, track_number) "
					+ " values(?, ?, ?, ?, ?, ?)";
			st = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			// plug in the arguments for the 6 parameters
			// order is based on when they occur in the SQL query string
			st.setString(1, a.getLocation());
			st.setString(2, a.getTitle());
			st.setString(3, a.getArtist());
			st.setString(4, a.getAlbum());
			st.setLong(5, a.getLengthInt());
			st.setInt(6, a.getTrackId());
			st.executeUpdate();

			// get the generated key of the new record
			// jdbc returns a resultset of field data containing the new key(s)
			rs = st.getGeneratedKeys();
			// make sure you don't call getInt() on an empty result set
			// next() also moves the result set to the first record
			if (rs != null && rs.next()) {
				// returned keys don't have column names unfortunately
				// so just ask for the value of the first column in the returned
				// key set
			}

		} catch (SQLException e) {
			// should log this exception since something happened during the
			// query execution
			e.printStackTrace();
		} finally {
			// be sure to close things properly if they are open, regardless of
			// exception
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// should probably log this also
				e.printStackTrace();
			}
		}
	}

	public void alterStuff(SongEntry e) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String query = "UPDATE songs " + "SET title = ?, " + "track_id = ?, " + "artist = ?, " + "album = ?, "
					+ "WHERE id = ?";
			st = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			// plug in the arguments for the 3 parameters
			// order is based on when they occur in the SQL query string
			st.setString(1, e.getTitle());
			st.setInt(2, e.getTrackId());
			st.setString(3, e.getArtist());
			st.setString(4, e.getAlbum());
			st.executeUpdate();

			// get the generated key of the new record
			// jdbc returns a resultset of field data containing the new key(s)
			rs = st.getGeneratedKeys();
			// make sure you don't call getInt() on an empty result set
			// next() also moves the result set to the first record
			if (rs != null && rs.next()) {
				// returned keys don't have column names unfortunately
				// so just ask for the value of the first column in the returned
				// key set
			}

		} catch (SQLException e1) {
			// should log this exception since something happened during the
			// query execution
			e1.printStackTrace();
		} finally {
			// be sure to close things properly if they are open, regardless of
			// exception
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e2) {
				// should probably log this also
				e2.printStackTrace();
			}
		}
	}

	public void deleteRecord(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			String query = "DELETE FROM songs where id = ?";
			st = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setInt(1, id);
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// be sure to close things properly if they are open, regardless of
			// exception
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// should probably log this also
				e.printStackTrace();
			}
		}
	}

	/*
	 * fetch all song records
	 */
	public void fetchStuff() {
		// create a prepared statement using an SQL query
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// fetch records
			// don't need a parameterized query here since no user-provided
			// input
			// is being passed to the db server
			String query = "select * " + " from songs";
			st = conn.prepareStatement(query);

			// execute the query and get the record data in the form of a
			// ResultSet
			rs = st.executeQuery();

			// iterate over the result set using the next() method
			// note: result set is not initially at first row. we have to
			// manually move it to row 1
			songList.clear();

			while (rs.next()) {
				SongEntry song = null;
				try {
					song = new SongEntry(new MP3File(new File(rs.getString("path"))));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoMPEGFramesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ID3v2FormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CorruptHeaderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				songList.add(song);
			}

		} catch (SQLException e) {
			// should log this exception since something happened during the
			// query execution
			e.printStackTrace();
		} finally {
			// be sure to close things properly if they are open, regardless of
			// exception
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				// should probably log this also
				e.printStackTrace();
			}
		}
	}

	public ArrayList<SongEntry> getSongEntrys() {
		this.fetchStuff();
		return songList;
	}

	public void close() {
		try {
			conn.close();
			logger.trace("Closed author database connection");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void connectToDB() throws IOException, SQLException, ClassNotFoundException {
		Statement stmt = null;

		Class.forName("org.h2.Driver");
		
		String createTableSQL = "CREATE TABLE IF NOT EXISTS songs (path varchar(255), title varchar(255),"
				+ " artist varchar(255), album varchar(255), length long, track_number int)";

		// this creates a database in the users home directory
		// if it already exists, it does nothing
		// looks like ~/.aural.mv.db
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/.aural");
			// create the songs table here
			stmt = conn.createStatement();
			stmt.execute(createTableSQL);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		stmt = conn.createStatement();
	}
      
   
  
}