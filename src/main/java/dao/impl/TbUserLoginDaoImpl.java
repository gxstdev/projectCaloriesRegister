package dao.impl;

import dao.TbUserLoginDao;
import entity.TbUserLogin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class TbUserLoginDaoImpl implements TbUserLoginDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CaloriesRegister");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tr = em.getTransaction();

	@Override
	public void insert(TbUserLogin tbUserLogin) {
		try {
			tr.begin();
			em.persist(tbUserLogin);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
		}

	}

	@Override
	public void update(TbUserLogin tbUserLogin) {
		try {
			tr.begin();
			em.merge(tbUserLogin);
			tr.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
		} 
	}

	@Override
	public TbUserLogin findByLogin(String dsLogin) {		
		try {
			StringBuilder sql = new StringBuilder();			
			sql.append(" SELECT tul FROM TbUserLogin tul ");
			sql.append(" WHERE tul.dsLogin = :dsLogin ");
			
			Query jpql = em.createQuery(sql.toString(), TbUserLogin.class).setParameter("dsLogin", dsLogin);
			
			return (TbUserLogin) jpql.getSingleResult();	
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	@Override
	public void delete(Integer id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE TbUserLogin tul ");
			sql.append(" SET tul.flActive = 0 ");
			sql.append(" WHERE tul.cdLogin = :cdLogin ");
			
			Query jpql = em.createQuery(sql.toString()).setParameter("cdLogin", id);
			
			tr.begin();
			jpql.executeUpdate();
			tr.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
		}
	}
	
	public void closeEntity() {
		em.close();
		emf.close();
	}
}