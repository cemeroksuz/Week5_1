package core.concretes;

import core.abstracts.EMailService;

public class EMailManager implements EMailService{

	 @Override
	    public void sendVerificationMail(String email) {
	        System.out.println("Do�rulama Maili g�nderildi:  " + email);
	        System.out.println("E-mail --> Mail Adresini Do�rulamak ��in Bu Linki T�klay�n�z.");
	    }

}
