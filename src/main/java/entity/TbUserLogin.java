package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import listenner.TbUserLoginListenner;

@Entity
@Table(name = "TB_USER_LOGIN")
@EntityListeners(TbUserLoginListenner.class)
public class TbUserLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbUserLogin")
	@SequenceGenerator(name = "SeqTbUserLogin", sequenceName = "SQ_CD_LOGIN", allocationSize = 1)
	@Column(name = "CD_LOGIN")
	private Integer cdLogin;
	
	@NotNull
	@Column(name = "DS_LOGIN")
	private String dsLogin;
	
	@NotNull
	@Column(name = "DS_PASSWORD")
	private String dsPassword;
	
	@Column(name = "FL_ACTIVE")
	private Integer flActive;
	
	public TbUserLogin() {
	}

	public TbUserLogin(@NotNull String dsLogin, @NotNull String dsPassword) {
		this.dsLogin = dsLogin;
		this.dsPassword = dsPassword;
		this.flActive = 1;
	}

	public Integer getCdLogin() {
		return cdLogin;
	}

	public void setCdLogin(Integer cdLogin) {
		this.cdLogin = cdLogin;
	}

	public String getDsLogin() {
		return dsLogin;
	}

	public void setDsLogin(String dsLogin) {
		this.dsLogin = dsLogin;
	}

	public String getDsPassword() {
		return dsPassword;
	}

	public void setDsPassword(String dsPassword) {
		this.dsPassword = dsPassword;
	}

	public Integer getFlActive() {
		return flActive;
	}

	public void setFlActive(Integer flActive) {
		this.flActive = flActive;
	}

	@Override
	public String toString() {
		return String.format("CD: %d - USERNAME: %s - PASSWORD: %s", cdLogin, dsLogin, dsPassword);
	}
	
}
