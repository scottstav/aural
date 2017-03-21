package script;

import java.io.File;
import java.util.List;

import jdk.nashorn.internal.runtime.ScriptObject;

public class ScriptCompiler
{
    private File file;
    private List<ScriptObject> scriptTasks;
    public ScriptCompiler(File file)
    {
        this.file = file;
        compile(file);
    }
    
    public void compile(File file)
    {
        // #TODO Need to implement a way to parse strings into objects that do things.
    }
    
    public List<ScriptObject> getScriptObjects()
    {
        return scriptTasks;
    }
}
