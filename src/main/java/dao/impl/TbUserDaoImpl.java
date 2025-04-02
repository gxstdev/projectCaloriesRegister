package dao.impl;

import dao.TbUserDao;
import entity.TbUser;
import entity.TbUserLogin;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TbUser tbUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public TbUser findByUSer(TbUser tbUser) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT tu FROM TbUser tu ");
		sql.append(" WHERE tu.userLogin.cdLogin = :cdLogin ");

		Query jpql = em.createQuery(sql.toString(), TbUser.class).setParameter("cdLogin",
				tbUser.getUserLogin().getCdLogin());

		return (TbUser) jpql.getSingleResult();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}
}
