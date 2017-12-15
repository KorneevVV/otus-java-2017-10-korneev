package ru.otus.korneev.hmw08;

public class ExampleClass {
	private int intEC = 0;
	private double doublEC = 0.505;
	private String stringEC = "value innerString";
	private Boolean boolEC = Boolean.TRUE;
	private Integer integerEC = 11;
	private ExampleSimpleClass exampleSimpleClass = new ExampleSimpleClass();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExampleClass that = (ExampleClass) o;

		if (intEC != that.intEC) return false;
		if (Double.compare(that.doublEC, doublEC) != 0) return false;
		if (!stringEC.equals(that.stringEC)) return false;
		if (!boolEC.equals(that.boolEC)) return false;
		if (!integerEC.equals(that.integerEC)) return false;
		return exampleSimpleClass.equals(that.exampleSimpleClass);
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = intEC;
		temp = Double.doubleToLongBits(doublEC);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + stringEC.hashCode();
		result = 31 * result + boolEC.hashCode();
		result = 31 * result + integerEC.hashCode();
		result = 31 * result + exampleSimpleClass.hashCode();
		return result;
	}
}
