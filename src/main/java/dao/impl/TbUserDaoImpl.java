package dao.impl;

import dao.TbUserDao;
import entity.TbUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class TbUserDaoImpl implements TbUserDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("CaloriesRegister");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tr = em.getTransaction();

	@Override
	public void insert(TbUser tbUser) {
		try {
			tr.begin();
			em.persist(tbUser);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tr != null) {
				tr.rollback();
			}
		}

	}

	@Override
	public void update(TbUser tbUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public TbUser findByUSer(TbUser tbUser) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT tu FROM TbUser tu ");
			sql.append(" WHERE tu.userLogin.cdLogin = :cdLogin ");

			Query jpql = em.createQuery(sql.toString(), TbUser.class).setParameter("cdLogin",
					tbUser.getUserLogin().getCdLogin());

			return (TbUser) jpql.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}
}
