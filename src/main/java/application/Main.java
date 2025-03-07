package application;

import entity.TbDailyCalories;
import entity.TbUserLogin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CaloriesRegister");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			TbUserLogin ul = new TbUserLogin(null, "oljdev", "0000");
			em.persist(ul);

			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (em != null) {
				em.getTransaction().rollback();
			}
			em.close();
			emf.close();
		}
		/*
		 * TbUserLogin ul = new TbUserLogin(null, "gxstdev", "abcd"); em.persist(ul);
		 * 
		 * em.close(); emf.close();
		 */
	}
}
