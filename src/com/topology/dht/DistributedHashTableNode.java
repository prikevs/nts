package com.topology.dht;

import com.topology.simulator.AbstractNode;
import com.topology.simulator.Address;
import com.topology.simulator.ICommand;
import com.topology.simulator.IData;

public class DistributedHashTableNode extends AbstractNode {
    Address selfAddress;

    @Override
    public void initialize() {
        selfAddress = connect();
    }

    @Override
    public void onReceive(Address address, IData rawData) {
        log("receive data from " + address.getIdentifier() + ": " + ((Data) rawData).msg);
    }

    @Override
    public void onCommand(ICommand rawCommand) {
        Command realCommand = (Command) rawCommand;
        log("receive command: " + realCommand.cmd);
        Data data = new Data();
        data.msg = "hello the other";
        send(Address.create(2), data);
    }

    private void log(String str) {
        System.out.println(selfAddress.getIdentifier() + " - " + str);
    }
}
