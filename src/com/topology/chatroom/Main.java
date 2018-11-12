package com.topology.chatroom;

import com.topology.simulator.INetworkTopologySimulator;
import com.topology.simulator.INode;
import com.topology.simulator.NetworkTopologySimulator;

public class Main {
    public static void main(String[] args) {
        INetworkTopologySimulator networkTopologySimulator = new NetworkTopologySimulator();
        INode serverNode = new ServerNode();
        networkTopologySimulator.addNode(serverNode);

        for(int i = 1; i <= 8; i++) {
            INode clientNode = new ClientNode("client"+i);
            networkTopologySimulator.addNode(clientNode);
        }
        for(int i = 1; i <= 8; i++) {
            Command command = new Command();
            command.type = Command.Type.ONLINE;
            networkTopologySimulator.executeCommand("client"+i, command);
        }
        Command command = new Command();
        command.type = Command.Type.MESSAGE;
        command.message = "hello world";
        networkTopologySimulator.executeCommand("client1", command);
    }
}
