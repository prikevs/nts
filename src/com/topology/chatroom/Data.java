package com.topology.chatroom;

import com.topology.simulator.IData;

public class Data implements IData {
    public enum Type {
        ONLINE,
        OFFLINE,
        MESSAGE,
    }

    public Type type;
    public String clientName;
    public String message;

    public IData clone() {
        return this;
    }
}
