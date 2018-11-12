package com.topology.chatroom;

import com.topology.simulator.AbstractNode;
import com.topology.simulator.Address;
import com.topology.simulator.ICommand;
import com.topology.simulator.IData;

public class ClientNode extends AbstractNode {
    private String clientName;

    public ClientNode(String clientName) {
        this.clientName = clientName;
    }

    private void online() {
        Data data = new Data();
        data.type = Data.Type.ONLINE;
        data.clientName = clientName;
        send("server", data);
    }

    private void offline() {
        Data data = new Data();
        data.type = Data.Type.OFFLINE;
        data.clientName = clientName;
        send("server", data);
    }

    @Override
    public void initialize() {
        connect();
        registerUri(clientName);
    }

    @Override
    public void onReceive(Address address, IData rawData) {
        Data data = (Data) rawData;
        if (data.type.equals(Data.Type.MESSAGE)) {
            System.out.println(clientName + " - " + data.message);
        }
    }

    @Override
    public void onCommand(ICommand rawCommand) {
        Command command = (Command) rawCommand;
        switch (command.type) {
            case ONLINE:
                online();
                break;
            case MESSAGE:
                Data data = new Data();
                data.message = command.message;
                data.type = Data.Type.MESSAGE;
                send("server", data);
                break;
            case OFFLINE:
                offline();
                break;
        }

    }
}
