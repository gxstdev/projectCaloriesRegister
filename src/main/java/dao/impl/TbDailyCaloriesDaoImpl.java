package dao.impl;

import java.time.LocalDate;

import dao.TbDailyCaloriesDao;
import entity.TbDailyCalories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;

public class TbDailyCaloriesDaoImpl implements TbDailyCaloriesDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CaloriesRegister");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tr = em.getTransaction();

	@Override
	public boolean insert(TbDailyCalories tbDailyCalories) {
		try {
			tr.begin();
			em.persist(tbDailyCalories);	
			em.flush();
			
			StoredProcedureQuery procedure = em.createStoredProcedureQuery("SP_INSERT_EXCEDEED_CALORIES");
			procedure.registerStoredProcedureParameter("P_QT_CALORIES", Integer.class, ParameterMode.IN);
			procedure.registerStoredProcedureParameter("P_DT_REGISTER", LocalDate.class, ParameterMode.IN);
			procedure.registerStoredProcedureParameter("P_CD_USER", Integer.class, ParameterMode.IN);
			procedure.registerStoredProcedureParameter("P_CD_DAILY_CAL", Integer.class, ParameterMode.IN);
			
			procedure.setParameter("P_QT_CALORIES", tbDailyCalories.getQtCalories());
			procedure.setParameter("P_DT_REGISTER",tbDailyCalories.getDtRegister());
			procedure.setParameter("P_CD_USER", tbDailyCalories.getUser().getCode());
			procedure.setParameter("P_CD_DAILY_CAL", tbDailyCalories.getCdDailyCalories());
			
			procedure.execute();
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
	public void update(TbDailyCalories tbDailyCalories) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public TbDailyCalories findByUSer(TbDailyCalories tbDailyCalories) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
