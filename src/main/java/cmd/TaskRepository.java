package cmd;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByCategory(String category);
    List<Task> findByDeadlineBefore(LocalDate date);
    List<Task> findByDescriptionContaining(String text);
    List<Task> findBySubtasks_NameContaining(String text);
}