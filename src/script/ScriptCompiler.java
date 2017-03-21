package script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import jdk.nashorn.internal.runtime.ScriptObject;

public class ScriptCompiler
{
    private File file;
    private List<ScriptObject> scriptTasks;
    public ScriptCompiler(File file)
    {
        this.file = file;
        compile();
    }
    
    public void compile()
    {
        // #TODO Need to implement a way to parse strings into objects that do things.
        //       Idea: whenever you encounter a loop line, keep adding imperative objects to the loop object
        try
        {
          BufferedReader reader = new BufferedReader(new FileReader(file));
          String line;
          while ((line = reader.readLine()) != null)
          {
            
          }
          reader.close();
        }
        catch (Exception e)
        {
          System.err.format("Exception occurred trying to read '%s'.", file.getAbsolutePath());
          e.printStackTrace();
        }
    }
    
    public List<ScriptObject> getScriptObjects()
    {
        return scriptTasks;
    }
}
