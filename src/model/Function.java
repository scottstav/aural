package model;

import controller.Handler;
import javafx.event.EventType;

public class Function
{
    private String textCommand;
    private Handler handler;
    private EventType type;
    
    public Function(Handler handler, String textCommand, EventType type)
    {
        this.textCommand = textCommand;
        this.handler = handler;
        this.type = type;
    }
    
    public Handler getHandler()
    {
        return handler;
    }
    
    public String getTextCommand()
    {
        return textCommand;
    }
    
    public EventType getEventType()
    {
        return type;
    }
}
