package com.topology.dht;

import com.topology.simulator.IData;

public class Data implements IData {
    public String msg;

    public IData clone() {
        return this;
    }
}
