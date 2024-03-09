package net.drapuria.starblaze.logging.repository;

import net.drapuria.starblaze.logging.model.Log;
import net.drapuria.starblaze.logging.model.LogEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogEntryRepository extends MongoRepository<LogEntry, Long> {

    Page<LogEntry> findByLog_Id(Long id, Pageable pageable);

    List<LogEntry> findAllByLog_Id(Long id);

    void deleteAllByLog_Id(Long id);

    long countByLog(Log log);
}
