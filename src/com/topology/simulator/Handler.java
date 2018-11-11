package com.topology.simulator;

import java.util.Deque;
import java.util.LinkedList;

public abstract class Handler {
    private Deque<Event> eventDeque;
    public Handler() {
        eventDeque = new LinkedList<>();
    }

    public void post(Event event) {
        eventDeque.addLast(event);
    }

    public void postAtFrontOfQueue(Event event) {
        eventDeque.addFirst(event);
    }

    public void loopUntilEmpty() {
        Event event;
        while((event = eventDeque.pollFirst()) != null) {
            handle(event);
        }
    }

    public abstract void handle(Event event);
}
