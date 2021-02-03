package com.threerivers.transaction.service;

public interface MongoSequenceService {

	long getSequenceNumber(String sequenceName);
}
