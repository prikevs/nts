package com.topology.chatroom;

import com.topology.simulator.AbstractNode;
import com.topology.simulator.Address;
import com.topology.simulator.ICommand;
import com.topology.simulator.IData;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ServerNode extends AbstractNode {
    private Map<Address, String> clients = new HashMap<>();

    @Override
    public void initialize() {
        connect();
        registerUri("server");
    }

    @Override
    public void onReceive(Address address, IData rawData) {
        Data data = (Data) rawData;
        switch (data.type) {
            case ONLINE:
                clients.put(address, data.clientName);
                break;
            case OFFLINE:
                clients.remove(address);
                break;
            case MESSAGE:
                String msg = "Message from " + clients.get(address) + ": " + data.message;
                Data sendData = new Data();
                sendData.message = msg;
                sendData.type = Data.Type.MESSAGE;
                clients.forEach(new BiConsumer<Address, String>() {
                    @Override
                    public void accept(Address addr, String s) {
                        if (!s.equals(clients.get(address))) {
                            send(addr, sendData);
                        }
                    }
                });
        }
    }

    @Override
    public void onCommand(ICommand rawCommand) {
    }
}
