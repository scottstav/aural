package script;

import java.util.List;

public class ScriptExecutor
{
    private List<ScriptObject> scriptObjects;
    public ScriptExecutor(List<ScriptObject> scriptObjects)
    {
        this.scriptObjects = scriptObjects;
    }
    
    public void execute()
    {
        // #TODO Need to find a way to iterate through compiled objects and run each one.
        for(ScriptObject object : scriptObjects)
            object.execute();
    }
}
