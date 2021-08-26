package eci.edu.ieti.labUnoParteUno.service;

import eci.edu.ieti.labUnoParteUno.datos.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class UserServiceHashMap implements UserService{
    private HashMap<String,User> usersHashMap=new HashMap<>();
    @Override
    public User create(User user) {
        usersHashMap.put(user.getId(),user);
        return user;
    }

    @Override
    public User findById(String id) {
        User found=usersHashMap.get(id);
        return found;
    }

    @Override
    public List<User> all() {
        List<User> allUsers = new ArrayList<>();
        for(String id: usersHashMap.keySet()){
            allUsers.add(usersHashMap.get(id));
        }
        return allUsers;
    }

    @Override
    public void deleteById(String id) {
        usersHashMap.remove(id);
    }

    @Override
    public User update(User user, String userId) {
        usersHashMap.put(userId,user);
        return user;
    }
}
