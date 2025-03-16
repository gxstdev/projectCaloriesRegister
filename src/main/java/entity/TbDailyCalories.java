package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_DAILY_CALORIES")
public class TbDailyCalories {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbDailyCalories")
	@SequenceGenerator(name = "SeqTbDailyCalories", sequenceName = "SQ_CD_DAILY_CAL", allocationSize = 1)
	@Column(name = "CD_DAILY_CAL")
	private Integer code;
	
	@NotNull
	@Column(name = "DT_REGISTER")
	private LocalDate date;

	@NotNull
	@Column(name = "QT_CALORIES")
	private Integer quantity;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USER")
	private TbUser user;

	@Transient
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");

	public TbDailyCalories() {
	}

	public TbDailyCalories(Integer code, @NotNull LocalDate date, @NotNull Integer quantity, @NotNull TbUser user) {
		this.code = code;
		this.date = date;
		this.quantity = quantity == null ? 0 : quantity;
		this.user = user;
	}

	public Integer getCdDailyCalories() {
		return code;
	}

	public void setCdDailyCalories(Integer cdDailyCalories) {
		this.code = cdDailyCalories;
	}

	public LocalDate getDtRegister() {
		return date;
	}

	public void setDtRegister(LocalDate dtRegister) {
		this.date = dtRegister;
	}

	public Integer getQtCalories() {
		return quantity;
	}

	public void setQtCalories(Integer qtCalories) {
		this.quantity = qtCalories;
	}

	@Override
	public String toString() {
		return String.format("CD REGISTRO CAL: %d - DATA: %s - QUATIDADE CALORIAS: %d", code, date, quantity);
	}

}
