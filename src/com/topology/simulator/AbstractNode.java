package com.topology.simulator;

public abstract class AbstractNode implements INode {
    private Delegate delegate;

    public AbstractNode() {}

    @Override
    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    protected Address connect() {
        return delegate.connect(this);
    }

    protected boolean registerUri(String uri) {
        return delegate.registerUri(this, uri);
    }

    protected void disconnect() {
        delegate.disconnect(this);
    }

    protected void send(Address address, IData data) {
        delegate.send(this, address, data);
    }

    protected void send(String uri, IData data) {
        Address address = lookup(uri);
        if (address != null) {
            delegate.send(this, address, data);
        }
    }

    protected Address getAddress() {
        return delegate.getAddress(this);
    }

    protected Address lookup(String uri) {
        return delegate.lookup(uri);
    }

    @Override
    public abstract void onReceive(Address address, IData data);

    @Override
    public abstract void onCommand(ICommand command);
}
