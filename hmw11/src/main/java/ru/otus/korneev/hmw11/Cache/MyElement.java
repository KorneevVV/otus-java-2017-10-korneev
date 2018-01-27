package ru.otus.korneev.hmw11.Cache;

import java.lang.ref.SoftReference;

public class MyElement<K, V> {
    private final K key;
    private final SoftReference<V> value;
    private final long creationTime;
    private long lastAccessTime;

    public MyElement(K key, V value) {
        this.key = key;
        this.value = new SoftReference<>(value);
        this.creationTime = getCurrentTime();
        this.lastAccessTime = getCurrentTime();
    }

    long getCurrentTime() {
        return System.currentTimeMillis();
    }

    K getKey() {
        return key;
    }

    public V getValue() {
        return value.get();
    }

    long getCreationTime() {
        return creationTime;
    }

    long getLastAccessTime() {
        return lastAccessTime;
    }

    void setAccessed() {
        lastAccessTime = getCurrentTime();
    }
}