package script;

import launch.MasterController;
import model.SongEntry;

public class ScriptPlayObject extends ScriptObject
{
    private SongEntry entry;
    public ScriptPlayObject(String command, int line)
    {
        compile(command, line);
    }
    
    @Override
    public int compile(String command, int line)
    {
        // TODO Write compile method for a play object
        String[] tokens = command.split(" ");
        if(tokens.length != 2 || !tokens[0].equalsIgnoreCase("play"))
        {
            System.err.println("Error in play syntax");
            return 1;
        }
        
        SongEntry song = searchByPath(tokens[1]);
        if(song == null)
        {
            System.err.println("Error: song does not exist");
            return 2;
        }
        
        entry = song;
        
        return 0;
    }
    
    private SongEntry searchByPath(String path)
    {
        SongEntry returnEntry = null;
        for(SongEntry s : MasterController.getInstance().getLibraryController().getSongs())
        {
            if(s.getLocation().equals(path))
            {
                returnEntry = s;
                break;
            }
        }
        
        return returnEntry;
    }

    @Override
    public void execute()
    {
        // TODO Write execute method for play object
        MasterController.getInstance().getPlaybackController().setSelected(entry);
        MasterController.getInstance().getPlaybackController().playSelection();
    }
}
