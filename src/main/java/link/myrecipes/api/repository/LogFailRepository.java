package link.myrecipes.api.repository;

import link.myrecipes.api.domain.LogFailDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogFailRepository extends MongoRepository<LogFailDocument, ObjectId> {
}
