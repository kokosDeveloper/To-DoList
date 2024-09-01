package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Task;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@AllArgsConstructor
public class TaskDao {
    private ObjectMapper objectMapper;
    private File file;
    public void save(Task task){
        try {
            List<Task> tasks = objectMapper.readValue(file, new TypeReference<List<Task>>() {
            });
            tasks.add(task);
            objectMapper.writeValue(file, tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Task> getAll(){
        try {
            return objectMapper.readValue(file, new TypeReference<List<Task>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(UUID id){
        try {
            List<Task> tasks = objectMapper.readValue(file, new TypeReference<List<Task>>() {
            });
            List<Task> tasksWithoutRemoved = tasks.stream()
                    .filter(task -> !task.getId().equals(id))
                    .collect(Collectors.toList());
            objectMapper.writeValue(file, tasksWithoutRemoved);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
