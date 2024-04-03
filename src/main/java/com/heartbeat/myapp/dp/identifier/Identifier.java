package com.heartbeat.myapp.dp.identifier;

import java.io.Serializable;

public interface Identifier<T> extends Serializable {

    T getValue();
}
