package com.topology.simulator;

/**
 * Interface of nodes in the network.
 */
public interface INode {
    interface Delegate {
        /**
         * UDP style sending data. Do not need to know if data reaches destination.
         * @param node self.
         * @param address address that data sent to.
         * @param data data to be sent.
         */
        void send(INode node, Address address, IData data);

        /**
         * Connect into network topology simulator.
         * @param node self.
         * @return address in the network.
         */
        Address connect(INode node);

        /**
         * Disconnect self from the network.
         * @param node
         */
        void disconnect(INode node);

        /**
         * Gets address by self node.
         * @param node this.
         * @return address of the node.
         */
        Address getAddress(INode node);

        /**
         * Lookup address by the uri provided.
         * @param uri
         */
        Address lookup(String uri);

        boolean registerUri(INode node, String uri);
    }

    /**
     * Initialize the node, will be called by addNode().
     */
    void initialize();

    /**
     * Set delegate, which will be NetworkTopologySimulator.
     * @param delegate NetworkTopologySimulator.
     */
    void setDelegate(Delegate delegate);

    /**
     * Callback while receiving data.
     * @param address where data is from.
     * @param data data received.
     */
    void onReceive(Address address, IData data);

    /**
     * Callback while receiving commands.
     * @param command command received.
     */
    void onCommand(ICommand command);
}
