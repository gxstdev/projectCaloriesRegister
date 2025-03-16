package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_DAILY_EXERCISE")
public class TbDailyExercise {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbDailyExercise")
	@SequenceGenerator(name = "SeqTbDailyExercise", sequenceName = "SQ_DAILY_EXERCISE", allocationSize = 1)
	@Column(name = "CD_DAILY_EXERCISE")
	private Integer code;
	
	@NotNull
	@Column(name = "DT_REGISTER")
	private LocalDate date;
	
	@Column(name = "VL_BURNED_CALORIES")
	private Integer burnedCalories;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USER")
	private TbUser user;

	public TbDailyExercise() {
	}

	public TbDailyExercise(Integer code, @NotNull LocalDate date, Integer burnedCalories, @NotNull TbUser user) {
		this.code = code;
		this.date = date == null ? LocalDate.now() : date;
		this.burnedCalories = burnedCalories == null ? 0 : burnedCalories;
		this.user = user;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getBurnedCalories() {
		return burnedCalories;
	}

	public void setBurnedCalories(Integer burnedCalories) {
		this.burnedCalories = burnedCalories;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TbDailyExercise [code=" + code + ", date=" + date + ", burnedCalories=" + burnedCalories + ", user="
				+ user + "]";
	}	
}
