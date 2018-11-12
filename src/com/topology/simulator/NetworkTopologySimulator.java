package com.topology.simulator;

public class NetworkTopologySimulator implements INetworkTopologySimulator, INode.Delegate {
    private final NodeManager nodeManager;
    private final Handler handler;
    private final Address SIMULATOR_ADDRESS = Address.create(0);

    private static void handleNetworkData(NodeManager nodeManager, Event event) {
        Event.NetworkData networkData = (Event.NetworkData) event.data;
        INode targetNode = nodeManager.getNode(networkData.to);
        if (targetNode != null) {
            targetNode.onReceive(networkData.from, networkData.data);
        }
    }

    private static void handleCommandData(NodeManager nodeManager, Event event) {
        Event.CommandData command = (Event.CommandData) event.data;
        INode targetNode = nodeManager.getNode(command.executorAddress);
        if (targetNode != null) {
            targetNode.onCommand(command.command);
        }
    }

    public NetworkTopologySimulator() {
        nodeManager = new NodeManager();
        handler = new Handler() {
            @Override
            public void handle(Event event) {
                switch (event.type) {
                    case SEND:
                        handleNetworkData(nodeManager, event);
                        break;
                    case COMMAND:
                        handleCommandData(nodeManager, event);
                        break;
                }
            }
        };
    }

    public void executeCommand(Address address, ICommand command) {
        Event.CommandData commandData =
                Event.CommandData.newBuilder().setExecutorAddress(address).setCommand(command).build();
        Event event = Event.obtain(Event.Type.COMMAND, commandData);
        handler.post(event);
        handler.loopUntilEmpty();
    }

    public void executeCommand(String uri, ICommand command) {
        Address address = nodeManager.lookupUri(uri);
        if (address != null) {
            executeCommand(address, command);
        }
    }

    public void addNode(INode node) {
        node.setDelegate(this);
        node.initialize();
    }

    @Override
    public void send(INode node, Address address, IData data) {
        Address from = nodeManager.getAddress(node);
        Event.NetworkData networkData =
                Event.NetworkData.newBuilder().setFrom(from).setTo(address).setData(data).build();
        Event event = Event.obtain(Event.Type.SEND, networkData);
        handler.postAtFrontOfQueue(event);
    }

    @Override
    public Address connect(INode node) {
        return nodeManager.addNode(node);
    }

    @Override
    public void disconnect(INode node) {
        nodeManager.removeNode(node);
    }

    @Override
    public Address getAddress(INode node) {
        return nodeManager.getAddress(node);
    }

    @Override
    public Address lookup(String uri) {
        return nodeManager.lookupUri(uri);
    }

    @Override
    public boolean registerUri(INode node, String uri) {
        return nodeManager.registerUri(node, uri);
    }
}
