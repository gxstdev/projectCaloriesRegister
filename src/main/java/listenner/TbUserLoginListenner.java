package listenner;

import entity.TbUserLogin;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

public class TbUserLoginListenner {
	
	@PostPersist
	public void logPersistedUserLogin(TbUserLogin tbUserLogin) {
		System.out.println("Usuário criado!");
		System.out.println("-> " + tbUserLogin.getDsLogin());
		System.out.println("-> " + tbUserLogin.getDsPassword());
	}
	
	@PostUpdate
	public void logUpdatedUserLogin(TbUserLogin tbUserLogin) {
		System.out.println("Usuário atualizado!");
		System.out.println("-> " + tbUserLogin.getDsLogin());
		System.out.println("-> " + tbUserLogin.getDsPassword());
		
		if (tbUserLogin.getFlActive() == 0) {
			System.out.println("Usuário deletado!");
			System.out.println("-> " + tbUserLogin.getDsLogin());
		}
	}
	
	
}
