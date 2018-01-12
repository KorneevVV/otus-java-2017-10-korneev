package ru.otus.korneev.hmw10.implementation.hibernate.userDataSet;

import ru.otus.korneev.hmw10.dataSets.DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet {

	public AddressDataSet() {
	}

	public AddressDataSet(String street) {
		this.street = street;
	}

	@Column(name = "street")
	private String street;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "AddressDataSet{" +
				"street='" + street + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		AddressDataSet that = (AddressDataSet) o;

		return street != null ? street.equals(that.street) : that.street == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (street != null ? street.hashCode() : 0);
		return result;
	}
}
