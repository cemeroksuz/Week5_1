package business.concretes;

import business.abstracts.UserCheckService;
import business.abstracts.UserService;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService{

	private final UserDao userDao;
    private final UserCheckService userCheckService;

    public UserManager(UserDao userDao, UserCheckService userCheckService) {
        this.userDao = userDao;
        this.userCheckService = userCheckService;
    }

    @Override
    public void register(User user) {
        if (userCheckService.isValidUser(user, userDao)) {
            userDao.add(user);
            System.out.println("Doðrulama Baþarýlý");
            return;
        }
        System.out.println("Doðrulama Baþarýsýz");
    }

    @Override
    public void login(String email, String password) {
        if (userCheckService.isCorrectLoginInput(email, password, userDao)) {
            System.out.println("Kullanýcý Giriþi Yapýlýyor...");
            return;
        }
        System.out.println("giriþ Baþarýsýz");
    }

    @Override
    public void update(User user) {
        if (userCheckService.isValidUser(user, userDao)) {
            userDao.update(user);
            System.out.println("Günecelleme Baþarýlý");
            return;
        }
        System.out.println("Günecelleme Baþarýsýz");
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
        System.out.println("Silme Baþarýlý");
    }
	

}
