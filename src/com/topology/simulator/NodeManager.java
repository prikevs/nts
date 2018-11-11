package com.topology.simulator;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class NodeManager {
    private int latestIdentifier = 1;
    private Map<Address, INode> addressNodeHashMap = new HashMap<>();
    private Map<INode, Address> nodeAddressHashMap = new IdentityHashMap<>();

    private int nextIdentifier() {
        return latestIdentifier++;
    }

    public Address getAddress(INode node) {
        return nodeAddressHashMap.get(node);
    }

    public Address addNode(INode node) {
        Address newAddress = Address.create(nextIdentifier());
        addressNodeHashMap.put(newAddress, node);
        nodeAddressHashMap.put(node, newAddress);
        return newAddress;
    }

    public INode getNode(Address address) {
        return addressNodeHashMap.get(address);
    }

    public void removeNode(INode node) {
        Address address = nodeAddressHashMap.get(node);
        if (address != null) {
            nodeAddressHashMap.remove(node);
            addressNodeHashMap.remove(address);
        }
    }
}
