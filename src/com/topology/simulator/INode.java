package com.topology.simulator;

/**
 * Interface of nodes in the network.
 */
interface INode {
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

        Address getAddress(INode node);
    }

    void initialize();

    void setDelegate(Delegate delegate);

    /**
     * Callback while receiving data.
     * @param address where data is from.
     * @param data data received.
     */
    void onReceive(Address address, IData data);

    void onCommand(ICommand command);
}
