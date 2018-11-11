package com.topology.simulator;

public class Event {
    public enum Type {
        SEND,
        COMMAND,
    }

    public static class NetworkData {
        public final Address from;
        public final Address to;
        public final IData data;
        private NetworkData(Builder builder) {
            from = builder.from;
            to = builder.to;
            data = builder.data;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {
            private Address from;
            private Address to;
            private IData data;

            public Builder setFrom(Address from) {
                this.from = from;
                return this;
            }

            public Builder setTo(Address to) {
                this.to = to;
                return this;
            }

            public Builder setData(IData data) {
                this.data = data.clone();
                return this;
            }

            public NetworkData build() {
                return new NetworkData(this);
            }
        }
    }

    public static class CommandData {
        public final Address executorAddress;
        public final ICommand command;

        private CommandData(Builder builder) {
            executorAddress = builder.executorAddress;
            command = builder.command;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {
            private Address executorAddress;
            private ICommand command;

            public Builder setExecutorAddress(Address address) {
                executorAddress = address;
                return this;
            }

            public Builder setCommand(ICommand command) {
                this.command = command;
                return this;
            }

            public CommandData build() {
                return new CommandData(this);
            }
        }
    }

    public final Type type;
    public final Object data;

    private Event(Type type, Object data) {
        this.type = type;
        this.data = data;
    }

    public static Event obtain(Type type, Object data) {
        return new Event(type, data);
    }
}
