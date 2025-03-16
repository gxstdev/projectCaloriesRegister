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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_STREAK")
public class TbStreak {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbStreak")
	@SequenceGenerator(name = "SeqTbStreak", sequenceName = "SQ_CD_STREAK", allocationSize = 1)
	@Column(name = "CD_STREAK")
	private Integer code;
	
	@Column(name = "VL_STREAK")
	private Integer streak;
	
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_USER")
	private TbUser user;
	
	@Column(name = "DT_LAST_UPDATE")
	private LocalDate lastUpdate;
	
	public TbStreak() {
	}

	public TbStreak(Integer code, Integer streak, @NotNull TbUser user, LocalDate lastUpdate) {
		this.code = code;
		this.streak = streak;
		this.user = user;
		this.lastUpdate = lastUpdate;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getStreak() {
		return streak;
	}

	public void setStreak(Integer streak) {
		this.streak = streak;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "TbStreak [code=" + code + ", streak=" + streak + ", user=" + user + ", lastUpdate=" + lastUpdate + "]";
	}
	
}
