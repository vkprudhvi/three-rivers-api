package com.threerivers.transaction.service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.threerivers.transaction.entity.DatabaseSequence;
import com.threerivers.transaction.service.MongoSequenceService;

@Service
public class MongoSequenceServiceImpl implements MongoSequenceService {
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public long getSequenceNumber(String sequenceName) {
		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seq", 1);
		DatabaseSequence databaseSequence = mongoOperations.findAndModify(query, update,
				options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(databaseSequence) ? databaseSequence.getSeq() : 1;
	}
}
