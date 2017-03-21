package script;

public abstract class ScriptObject
{
    public abstract int compile(String command, int line);
    public abstract void execute();
}
