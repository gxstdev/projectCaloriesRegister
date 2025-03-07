package entity;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_DAILY_CALORIES")
public class TbDailyCalories {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbDailyCalories")
	@SequenceGenerator(name = "SeqTbDailyCalories", sequenceName = "SQ_CD_DAILY_CAL", allocationSize = 1)
	@Column(name = "CD_DAILY_CAL")
	private Integer cdDailyCalories;
	
	@Column(name = "DT_REGISTER")
	private Date dtRegister;
	
	@Column(name = "QT_CALORIES")
	private Integer qtCalories;
	
	@ManyToOne
	@JoinColumn(name = "CD_USER")
	private TbUser cdUser;
	
	@Transient
	private DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");

	public Integer getCdDailyCalories() {
		return cdDailyCalories;
	}
	public void setCdDailyCalories(Integer cdDailyCalories) {
		this.cdDailyCalories = cdDailyCalories;
	}
	
	public Date getDtRegister() {
		return dtRegister;
	}
	public void setDtRegister(Date dtRegister) {
		this.dtRegister = dtRegister;
	}
	
	public Integer getQtCalories() {
		return qtCalories;
	}
	public void setQtCalories(Integer qtCalories) {
		this.qtCalories = qtCalories;
	}
	
	@Override
	public String toString() {
		return String.format("CD REGISTRO CAL: %d - DATA: %s - QUATIDADE CALORIAS: %d", 
				cdDailyCalories , dtRegister, qtCalories);
	}
	
}
