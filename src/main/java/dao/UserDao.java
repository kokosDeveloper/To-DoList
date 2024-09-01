package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class UserDao {
    private ObjectMapper objectMapper;
    private File file;
    public void save(User user){
        try {
            List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
            users.add(user);
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getAll(){
        try {
            return objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
