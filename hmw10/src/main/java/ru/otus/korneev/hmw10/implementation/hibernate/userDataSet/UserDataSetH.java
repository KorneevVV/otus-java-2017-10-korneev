package ru.otus.korneev.hmw10.implementation.hibernate.userDataSet;

import ru.otus.korneev.hmw10.dataSets.DataSet;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserDataSetH extends DataSet{

	public UserDataSetH() {
	}

	public UserDataSetH(String name, int age, AddressDataSet addressDataSet) {
//		this.setId(-1);
		this.name = name;
		this.age = age;
		this.addressDataSet = addressDataSet;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;

	@OneToOne(cascade = CascadeType.ALL)
	private AddressDataSet addressDataSet;

	public AddressDataSet getAddressDataSet() {
		return addressDataSet;
	}

	public void setAddressDataSet(AddressDataSet addressDataSet) {
		this.addressDataSet = addressDataSet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserDataSetH{" +
				"name='" + name + '\'' +
				", age=" + age +
				", addressDataSet=" + addressDataSet +
				'}';
	}
}
