package entities;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_DAILY_CALORIES")
public class TbDailyCalories {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbDailyCalories")
	@SequenceGenerator(name = "SeqTbDailyCalories", sequenceName = "SQ_CD_DAILY_CAL", allocationSize = 1)
	//obrigatório ter a anotação @Column, se já tiver sido criada a tabela no banco
	//caso esteja sendo criada, se não informar, irá criar as colunas extamente 
	//com os nomes das colunas sendo iguais aos nomes dos atributos declarados
	//passar para notion
	@Column(name = "CD_DAILY_CAL")
	private Integer cdDailyCalories;
	@Column(name = "DT_REGISTER")
	private Date dtRegister;	
	@Column(name = "QT_CALORIES")
	private Integer qtCalories;
	
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
