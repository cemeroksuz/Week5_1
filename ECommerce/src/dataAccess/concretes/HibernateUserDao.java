package dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class HibernateUserDao implements UserDao{

	 List<User> usersInDatabase = new ArrayList<>();
	 
	@Override
	public void add(User user) {
		System.out.println("Hibernet ile Veri Tabanýna Eklendi: " + user.geteMail());
        usersInDatabase.add(user);
	}

	@Override
	public void update(User user) {
		 System.out.println("Hibernet ile Veri Güncellendi:" + user.geteMail());
	        usersInDatabase.remove(getById(user.getId())); 
	        usersInDatabase.add(user);
		
	}

	@Override
	public void delete(User user) {
		System.out.println("Hibernet ile Veri Silindi:" + user.geteMail());
        usersInDatabase.remove(getById(user.getId())); 
		
	}

	@Override
	public User getById(int id) {
		List<User> users = getAll();
        if (users != null) {
            for (User user : users) {
                if (user.getId() == id) return user;
            }
        }
        return null;
	}

	@Override
	public User getByEmail(String email) {
		List<User> users = getAll();
        if (users != null) {
            for (User user : users) {
                if (user.geteMail().equals(email)) return user;
            }
        }
        return null;
	}

	@Override
	public List<User> getAll() {
		
		return usersInDatabase; 
	}

	
	
	
	
}
