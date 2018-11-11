package com.topology.dht;

import com.topology.simulator.Address;
import com.topology.simulator.ICommand;
import com.topology.simulator.NetworkTopologySimulator;

public class Main {
    public static void main(String[] argv) {
        NetworkTopologySimulator simulator = new NetworkTopologySimulator();
        simulator.addNode(new DistributedHashTableNode());
        simulator.addNode(new DistributedHashTableNode());
        simulator.executeCommand(Address.create(1), new Command("hello"));
    }
}
