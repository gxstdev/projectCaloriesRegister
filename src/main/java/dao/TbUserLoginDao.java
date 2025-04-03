package dao;


import entity.TbUserLogin;

public interface TbUserLoginDao {
	public boolean insert(TbUserLogin tbUserLogin);	
	public void update(TbUserLogin tbUserLogin);
	public TbUserLogin findByLogin(String dsLogin);
	public void delete(Integer id);
	
}
