package com.github.palmurugan.common.event;

public class DataEvent<K, T> {

  private K key;

  private T data;

  private EventType eventType;

  public DataEvent(K key, T data, EventType eventType) {
    this.key = key;
    this.data = data;
    this.eventType = eventType;
  }

  public K getKey() {
    return key;
  }

  public T getData() {
    return data;
  }

  public EventType getEventType() {
    return eventType;
  }

  @Override
  public String toString() {
    return "DataEvent{" + "key=" + key + ", data=" + data + ", eventType=" + eventType + '}';
  }
}
