package ru.otus.korneev.hmw08;

public class ExampleSimpleClass {
	private int intESC = 0;
	private double doublESC = 0.4;
	private String innerESC = "value innerString";
	private Boolean boolESC= Boolean.TRUE;
	private Integer integerESC = 99;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExampleSimpleClass that = (ExampleSimpleClass) o;

		if (intESC != that.intESC) return false;
		if (Double.compare(that.doublESC, doublESC) != 0) return false;
		if (!innerESC.equals(that.innerESC)) return false;
		if (!boolESC.equals(that.boolESC)) return false;
		return integerESC.equals(that.integerESC);
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = intESC;
		temp = Double.doubleToLongBits(doublESC);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + innerESC.hashCode();
		result = 31 * result + boolESC.hashCode();
		result = 31 * result + integerESC.hashCode();
		return result;
	}
}
