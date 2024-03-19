package com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways;


import com.fiap.techchallenge4.fiaptechchallenge4.domain.commom.Event;

public interface ProductionMessageSender {
    <T extends Event> void publish(T event);
}
