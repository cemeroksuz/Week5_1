package core.concretes;

import core.abstracts.EMailService;

public class EMailManager implements EMailService{

	 @Override
	    public void sendVerificationMail(String email) {
	        System.out.println("Doðrulama Maili gönderildi:  " + email);
	        System.out.println("E-mail --> Mail Adresini Doðrulamak Ýçin Bu Linki Týklayýnýz.");
	    }

}
