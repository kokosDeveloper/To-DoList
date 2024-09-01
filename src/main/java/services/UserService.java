package services;

import dao.UserDao;
import entity.User;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class UserService {
    private UserDao userDao;
    public void save(User user){
        userDao.save(user);
    }
    public List<User> getAll(){
        return userDao.getAll();
    }
}
