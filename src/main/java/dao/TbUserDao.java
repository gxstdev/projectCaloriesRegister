package dao;

import entity.TbUser;

public interface TbUserDao {
	public boolean insert(TbUser tbUser);	
	public void update(TbUser tbUser);
	public TbUser findByUSer(TbUser tbUser);
	public void delete(Integer id);
}
