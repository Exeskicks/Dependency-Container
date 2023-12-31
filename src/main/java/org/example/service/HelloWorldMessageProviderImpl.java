package org.example.service;

import org.example.interfaces.MessageProvider;

public class HelloWorldMessageProviderImpl implements MessageProvider {
    private final String id;

    public HelloWorldMessageProviderImpl(String id) {
        this.id = id;
    }

    @Override
    public String provide() {
        return "Hello World!!!";
    }

    @Override
    public String getProviderId() {
        return this.id;
    }
}