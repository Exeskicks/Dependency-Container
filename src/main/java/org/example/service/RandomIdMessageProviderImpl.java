package org.example.service;

import org.springframework.stereotype.Component;
import org.example.interfaces.MessageProvider;
import java.util.UUID;

@Component
public class RandomIdMessageProviderImpl implements MessageProvider {
    private final String id;

    public RandomIdMessageProviderImpl() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String provide() {
        return this.id;
    }

    @Override
    public String getProviderId() {
        return this.id;
    }
}
