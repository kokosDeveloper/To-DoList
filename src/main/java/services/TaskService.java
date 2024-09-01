package services;

import dao.TaskDao;
import entity.Task;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
public class TaskService {
    private TaskDao taskDao;
    public void save(Task task){
        taskDao.save(task);
    }
    public List<Task> getAll(){
        return taskDao.getAll();
    }
    public void delete(UUID id){
        taskDao.delete(id);
    }
    public void updateStatus(UUID id, boolean status){
        taskDao.updateStatus(id, status);
    }
}
