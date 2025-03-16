package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_DAILY_CALORIES_EXCEDEED")
public class TbDailyCaloriesExcedeed {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbDailyCaloriesExcedeed")
	@SequenceGenerator(name = "SeqTbDailyCaloriesExcedeed", sequenceName = "SQ_DAILY_CAL_EXCEDEED", allocationSize = 1)
	@Column(name = "CD_DAILY_CAL_EXCEDEED")
	private Integer code;
	
	@Column(name = "DT_REGISTER")
	private LocalDate date;
	
	@Column(name = "QT_CALORIES")
	private Integer quantity;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_DAILY_CAL")
	private TbDailyCalories dailyCalories;

	public TbDailyCaloriesExcedeed() {
	}

	public TbDailyCaloriesExcedeed(Integer code, LocalDate date, Integer quantity, TbDailyCalories dailyCalories) {
		this.code = code;
		this.date = date;
		this.quantity = quantity == null ? 0 : quantity;
		this.dailyCalories = dailyCalories;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public TbDailyCalories getDailyCalories() {
		return dailyCalories;
	}

	public void setDailyCalories(TbDailyCalories dailyCalories) {
		this.dailyCalories = dailyCalories;
	}

	@Override
	public String toString() {
		return "TbDailyCaloriesExcedeed [code=" + code + ", date=" + date + ", quantity=" + quantity
				+ ", dailyCalories=" + dailyCalories + "]";
	}
}
