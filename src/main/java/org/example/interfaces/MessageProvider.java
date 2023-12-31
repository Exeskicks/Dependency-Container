package org.example.interfaces;
//В интерфейсе MessageProvider
// опишем контракт на реализацию ряда методов:
public interface MessageProvider {
    //редоставления некоторого сообщения
    String provide();
    //обязан отдавать идентификатор конкретного имплементирующего наш интерфейс объекта
    String getProviderId();
}
