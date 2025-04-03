package dao;

import entity.TbMonthCaloriesGoal;

public interface TbMonthCaloriesGoalDao {
	public void insert(TbMonthCaloriesGoal tbMonthCaloriesGoal);	
	public void update(TbMonthCaloriesGoal tbMonthCaloriesGoal);
	public TbMonthCaloriesGoal findByUSer(TbMonthCaloriesGoal tbUtbMonthCaloriesGoalser);
	public void delete(TbMonthCaloriesGoal code);
}
