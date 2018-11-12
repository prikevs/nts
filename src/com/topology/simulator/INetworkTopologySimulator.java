package com.topology.simulator;

public interface INetworkTopologySimulator {
    void executeCommand(Address address, ICommand command);

    void executeCommand(String uri, ICommand command);

    void addNode(INode node);
}
