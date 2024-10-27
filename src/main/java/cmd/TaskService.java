package cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getOverdueTasks() {
        return taskRepository.findByDeadlineBefore(LocalDate.now());
    }

    public List<Task> getTasksByCategory(String category) {
        return taskRepository.findByCategory(category);
    }

    public List<SubTask> getSubTasksByCategory(String category) {
        return taskRepository.findByCategory(category)
                .stream()
                .flatMap(task -> task.getSubtasks().stream())
                .toList();
    }

    public Task saveOrUpdateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public List<Task> searchByDescription(String keyword) {
        return taskRepository.findByDescriptionContaining(keyword);
    }

    public List<Task> searchBySubTaskName(String keyword) {
        return taskRepository.findBySubtasks_NameContaining(keyword);
    }
}
