import business.concretes.UserCheckManager;
import business.concretes.UserManager;
import core.concretes.EMailManager;
import dataAccess.concretes.HibernateUserDao;
import entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		
		User user1 = new User(1,"Cem","Eröksüz","cemeroksuz@gmail.com","123456789");
		User user2 = new User(2,"Can","Ercan","canercan@gmail.com","123654789");
		User user3 = new User(3,"Caner","Eray","canereray@gmail.com","987654321");
		
		UserManager userManager = new UserManager(new HibernateUserDao(),new UserCheckManager(new EMailManager()));
		userManager.register(user1);
        userManager.login(user1.geteMail(), user1.getPassword());
		
        System.out.println("***************************************************************");
        
        userManager.register(user2);
        userManager.login(user2.geteMail(), user2.getPassword());
        
        System.out.println("***************************************************************");
        
        UserManager userManager2 = new UserManager(new HibernateUserDao(), new UserCheckManager(new EMailManager()));
        userManager2.register(user3);
        userManager2.login(user3.geteMail(), user3.getPassword());
	}

}
