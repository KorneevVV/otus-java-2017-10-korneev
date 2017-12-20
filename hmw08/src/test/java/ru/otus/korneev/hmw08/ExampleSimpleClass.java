package ru.otus.korneev.hmw08;

import java.util.Arrays;
import java.util.List;

public class ExampleSimpleClass {
	private int intESC = 0;
	private double doublESC = 0.4;
	private String innerESC = "value innerString";
	private Boolean boolESC = Boolean.TRUE;
	private Integer integerESC = 99;
	private int[] intArray = new int[]{5, 1, 2};
	private String[] stringArray = new String[]{"s5", "s1", "s2"};
	List<Integer> list = Arrays.asList(1, 5, 6, 11, 3, 15, 7, 8);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExampleSimpleClass that = (ExampleSimpleClass) o;

		if (intESC != that.intESC) return false;
		if (Double.compare(that.doublESC, doublESC) != 0) return false;
		if (innerESC != null ? !innerESC.equals(that.innerESC) : that.innerESC != null) return false;
		if (boolESC != null ? !boolESC.equals(that.boolESC) : that.boolESC != null) return false;
		if (integerESC != null ? !integerESC.equals(that.integerESC) : that.integerESC != null) return false;
		if (!Arrays.equals(intArray, that.intArray)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(stringArray, that.stringArray)) return false;
		return list != null ? list.equals(that.list) : that.list == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = intESC;
		temp = Double.doubleToLongBits(doublESC);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (innerESC != null ? innerESC.hashCode() : 0);
		result = 31 * result + (boolESC != null ? boolESC.hashCode() : 0);
		result = 31 * result + (integerESC != null ? integerESC.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(intArray);
		result = 31 * result + Arrays.hashCode(stringArray);
		result = 31 * result + (list != null ? list.hashCode() : 0);
		return result;
	}
}