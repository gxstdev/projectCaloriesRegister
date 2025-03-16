package entity;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_MONTH_CALORIES_GOAL")
public class TbMonthCaloriesGoal {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbMonthCaloriesGoal")
	@SequenceGenerator(name = "SeqTbMonthCaloriesGoal", sequenceName = "SQ_MONTH_CAL_GOAL", allocationSize = 1)
	@Column(name = "CD_MONTH_CAL_GOAL")
	private Integer code;

	@NotBlank
	@Size(max = 10)
	@Column(name = "DS_MONTH")
	private String month;

	@NotNull
	@Column(name = "QT_CALORIES_GOAL")
	private Integer quantity;

	@Column(name = "FL_ACTIVE")
	private Integer active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USER")
	private TbUser user;

	public TbMonthCaloriesGoal() {
	}

	public TbMonthCaloriesGoal(Integer code, @NotBlank @Size(max = 10) String month, @NotNull Integer quantity,
			Integer active, TbUser user) {
		this.code = code;
		this.month = month;
		this.quantity = quantity == null ? 0 : quantity;
		this.active = active;
		this.user = user;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TbMonthCaloriesGoal [code=" + code + ", month=" + month + ", quantity=" + quantity + ", active="
				+ active + ", user=" + user + "]";
	}
}
