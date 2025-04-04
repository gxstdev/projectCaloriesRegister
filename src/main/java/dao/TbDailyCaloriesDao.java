package dao;

import entity.TbDailyCalories;

public interface TbDailyCaloriesDao {
	public boolean insert(TbDailyCalories tbDailyCalories);
	public void update(TbDailyCalories tbDailyCalories);
	public TbDailyCalories findByUSer(TbDailyCalories tbDailyCalories);
	public void delete(Integer id);
}
