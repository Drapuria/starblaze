package net.drapuria.starblaze.logging.repository;

import net.drapuria.starblaze.logging.model.Log;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogRepository extends MongoRepository<Log, Long> {

    Optional<Log> findBySoftwareName(String name);
}
