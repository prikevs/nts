package com.topology.simulator;

public abstract class AbstractNode implements INode {
    protected Delegate delegate;

    public AbstractNode() {}

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    protected Address connect() {
        return delegate.connect(this);
    }

    protected void disconnect() {
        delegate.disconnect(this);
    }

    protected void send(Address address, IData data) {
        delegate.send(this, address, data);
    }

    protected Address getAddress() {
        return delegate.getAddress(this);
    }

    public abstract void onReceive(Address address, IData data);

    public abstract void onCommand(ICommand command);
}
