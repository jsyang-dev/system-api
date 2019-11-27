package link.myrecipes.api.repository;

import link.myrecipes.api.domain.LogCallDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogCallRepository extends MongoRepository<LogCallDocument, ObjectId> {
}
