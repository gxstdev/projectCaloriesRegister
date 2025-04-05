package dao;

import java.util.List;

import entity.TbDailyExercise;

public interface TbDailyExerciseDao {
	public boolean insert(TbDailyExercise tbDailyExercise);
	public void update(TbDailyExercise tbDailyExercise);
	public TbDailyExercise findByDailyCalories(TbDailyExercise tbDailyExercise);
	public void delete(Integer id);
	public List<TbDailyExercise> findAll(TbDailyExercise tbDailyExercise);
}
