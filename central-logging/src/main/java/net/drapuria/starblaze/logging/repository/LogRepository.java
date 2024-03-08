package net.drapuria.starblaze.logging.repository;

import net.drapuria.starblaze.logging.model.LogEntry;
import net.drapuria.starblaze.logging.model.LogType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogEntry, LogType> {
}
