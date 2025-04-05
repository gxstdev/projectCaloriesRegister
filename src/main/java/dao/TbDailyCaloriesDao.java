package dao;

import java.util.List;

import entity.TbDailyCalories;
import entity.TbUser;

public interface TbDailyCaloriesDao {
	public boolean insert(TbDailyCalories tbDailyCalories);
	public boolean update(TbDailyCalories tbDailyCalories);
	public TbDailyCalories findByDailyCalories(TbDailyCalories tbDailyCalories);
	public boolean delete(Integer id);
	public List<TbDailyCalories> findAll(TbUser user);
}
