package dao.impl;

import java.time.LocalDate;
import java.util.List;

import dao.TbDailyCaloriesDao;
import entity.TbDailyCalories;
import entity.TbUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
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
			callProcedure(tbDailyCalories);
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
	public boolean update(TbDailyCalories tbDailyCalories) {
		try {
			tr.begin();
			em.merge(tbDailyCalories);
			callProcedure(tbDailyCalories);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			return false;
		}
	}

	@Override
	public TbDailyCalories findByDailyCalories(TbDailyCalories tbDailyCalories) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" DELETE FROM TbDailyCalories tde ");
			jpql.append(" WHERE tde.code = :code ");
			
			tr.begin();
			Query query = em.createQuery(jpql.toString()).setParameter("code", id);
			query.executeUpdate();
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbDailyCalories> findAll(TbUser user) {
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" SELECT tdc FROM TbDailyCalories tdc left join fetch tdc.tbDailyCaloriesExcedeed ");
			jpql.append(" WHERE tdc.user.code = :code ");
			
			Query query = em.createQuery(jpql.toString(), TbDailyCalories.class);
			query.setParameter("code", user.getCode());
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
			return null;
		}
	}
	
	private void callProcedure(TbDailyCalories tbDailyCalories) {
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
	}
}
