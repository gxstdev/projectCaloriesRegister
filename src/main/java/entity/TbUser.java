package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_USER")
public class TbUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqTbUser")
	@SequenceGenerator(name = "SeqTbUser", sequenceName = "SQ_CD_USER", allocationSize = 1)
	@Column(name = "CD_USER")
	private Integer code;
	
	@OneToOne
	@JoinColumn(name = "CD_LOGIN")
	private TbUserLogin userLogin;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "NM_USER")
	private String name;
	
	@Column(name = "VL_AGE")
	private Integer age;
	
	@Column(name = "VL_WEIGHT")
	private Double weight;
	
	@Column(name = "VL_HEIGHT")
	private Double height;

	@Size(max = 1)
	@NotBlank
	@Column(name = "DS_GENDER")
	private String gender;

	public TbUser() {
	}

	public TbUser(Integer code, TbUserLogin userLogin, String nameString, Integer age, Double weight, Double height,
			@Size(max = 1) @NotBlank String gender) {
		this.code = code;
		this.userLogin = userLogin;
		this.name = nameString;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public TbUserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(TbUserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public String getNameString() {
		return name;
	}

	public void setNameString(String nameString) {
		this.name = nameString;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "TbUser [code=" + code + ", userLogin=" + userLogin + ", nameString=" + name + ", age=" + age
				+ ", weight=" + weight + ", height=" + height + ", gender=" + gender + "]";
	}
}
