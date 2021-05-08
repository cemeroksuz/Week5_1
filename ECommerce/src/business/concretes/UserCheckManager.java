package business.concretes;

import business.abstracts.UserCheckService;
import core.abstracts.AuthService;
import core.abstracts.EMailService;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

import java.util.regex.Pattern;

public class UserCheckManager implements UserCheckService{

	private AuthService authService;
    private final EMailService emailService;

    public UserCheckManager(EMailService emailService) {
        this.emailService = emailService;
    }

    public UserCheckManager(AuthService authService, EMailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @Override
    public boolean isValidFirstName(String firstName) {
        if (firstName.length() >= 2) return true;
        System.out.println("Ad 2 Karakterden Az Olamaz!");
        return false;
    }

    @Override
    public boolean isValidLastName(String lastName) {
        if (lastName.length() >= 2) return true;
        System.out.println("Soyad 2 Karakterden Az Olamaz!");
        return false;
    }

    @Override
    public boolean isValidPassword(String password) {
        if (password.length() >= 6) return true;
        System.out.println("Parola 6 Karakterden Az Olamaz!");
        return false;
    }

    @Override
    public boolean isValidEmailFormat(String email) {
        String emailRegex= "^\\w+(\\.\\w+)*@[a-zA-Z]+(\\.\\w{2,6})+$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        if (email == null) {
            System.out.println("Hatalý Mail Formatý!");
            return false;
        } else if (!pattern.matcher(email).matches()) System.out.println("Hatalý Mail Formatý!");
        return true;
    }

    @Override
    public boolean isUsedEmail(String email, UserDao userDao) {
        if (userDao.getByEmail(email) != null) {
            System.out.println("Bu Mail Adresi Daha Önceden Kullanýlmýþ.");
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidUser(User user, UserDao userDao) {
        if (authService != null) return authService.isValidUser();
        if (!isValidFirstName(user.getFirstName())) return false;
        else if (!isValidLastName(user.getLastName())) return false;
        else if (!isValidEmailFormat(user.geteMail())) return false;
        else if (!isValidPassword(user.getPassword())) return false;
        else if (isUsedEmail(user.geteMail(), userDao)) return false;
        emailService.sendVerificationMail(user.geteMail());
        System.out.println("Doðrulandý!");
        return true;
    }

    @Override
    public boolean isCorrectLoginInput(String email, String password, UserDao userDao) {
        if (authService != null) return authService.isValidUser();
        User user = userDao.getByEmail(email);
        if (user == null) {
            System.out.println("Yanlýþ E-mail!");
            return false;
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Yanlýþ Parolas!");
            return false;
        }
        return true;
    }
}
