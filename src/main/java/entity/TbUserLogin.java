package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_USER_LOGIN")
public class TbUserLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbUserLogin")
	@SequenceGenerator(name = "SeqTbUserLogin", sequenceName = "SQ_CD_LOGIN", allocationSize = 1)
	@Column(name = "CD_LOGIN")
	public Integer cdLogin;
	
	@NotNull
	@Column(name = "DS_LOGIN")
	public String dsLogin;
	
	@NotNull
	@Column(name = "DS_PASSWORD")
	public String dsPassword;
	
	public TbUserLogin() {
	}

	public TbUserLogin(Integer cdLogin, @NotNull String dsLogin, @NotNull String dsPassword) {
		super();
		this.cdLogin = cdLogin;
		this.dsLogin = dsLogin;
		this.dsPassword = dsPassword;
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

	@Override
	public String toString() {
		return "TbUserLogin [cdLogin=" + cdLogin + ", dsLogin=" + dsLogin + ", dsPassword=" + dsPassword + "]";
	}
	
}
