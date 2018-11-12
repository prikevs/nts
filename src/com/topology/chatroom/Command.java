package com.topology.chatroom;

import com.topology.simulator.ICommand;

public class Command implements ICommand {
    public enum Type {
        MESSAGE,
        OFFLINE,
        ONLINE,
    }
    public Type type;
    public String message;
}
