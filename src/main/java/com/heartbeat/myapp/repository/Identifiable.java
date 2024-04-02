package com.heartbeat.myapp.repository;

public interface Identifiable<ID extends Identifier> {

    ID getId();
}
