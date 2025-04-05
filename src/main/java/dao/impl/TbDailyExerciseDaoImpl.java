package dao.impl;

import java.util.List;

import dao.TbDailyExerciseDao;
import entity.TbDailyExercise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TbDailyExerciseDaoImpl implements TbDailyExerciseDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CaloriesRegister");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tr = em.getTransaction();

	@Override
	public boolean insert(TbDailyExercise tbDailyExercise) {
		try {
			tr.begin();
			em.persist(tbDailyExercise);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			return false;
		}
		return true;
	}

	@Override
	public void update(TbDailyExercise tbDailyExercise) {
		// TODO Auto-generated method stub

	}

	@Override
	public TbDailyExercise findByDailyCalories(TbDailyExercise tbDailyExercise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TbDailyExercise> findAll(TbDailyExercise tbDailyExercise) {
		// TODO Auto-generated method stub
		return null;
	}

}
