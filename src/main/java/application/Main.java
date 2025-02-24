package application;

import java.util.List;

import entities.TbDailyCalories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CaloriesRegister");
        EntityManager em = emf.createEntityManager();

        TbDailyCalories obj = em.find(TbDailyCalories.class, 1);
        System.out.println(obj);

        em.close();
        emf.close();
	}
}
