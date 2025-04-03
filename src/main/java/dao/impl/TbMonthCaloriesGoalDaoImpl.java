package dao.impl;

import dao.TbMonthCaloriesGoalDao;
import entity.TbMonthCaloriesGoal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TbMonthCaloriesGoalDaoImpl implements TbMonthCaloriesGoalDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CaloriesRegister");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tr = em.getTransaction();

	@Override
	public void insert(TbMonthCaloriesGoal tbMonthCaloriesGoal) {
		try {
			tr.begin();
			em.persist(tbMonthCaloriesGoal);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
		}

	}

	@Override
	public void update(TbMonthCaloriesGoal tbMonthCaloriesGoal) {
		// TODO Auto-generated method stub

	}

	@Override
	public TbMonthCaloriesGoal findByUSer(TbMonthCaloriesGoal tbUtbMonthCaloriesGoalser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TbMonthCaloriesGoal code) {
		// TODO Auto-generated method stub

	}

}
