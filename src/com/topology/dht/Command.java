package com.topology.dht;

import com.topology.simulator.ICommand;

public class Command implements ICommand {
    public String cmd;

    public Command(String cmd) {
        this.cmd = cmd;
    }
}
