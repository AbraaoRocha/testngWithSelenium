package testng.utils;

public class ValueUtils {

	public String removeChar(String value, String charToRemove) {

		String formmatedValue;

		formmatedValue = value.replace(charToRemove, "");

		return formmatedValue;

	}

	public Double calculateValueChanged(String value, int multipliedBy) {
		double formmatedValue;
		String addaptedString;

		addaptedString = removeChar(value, "$");
		addaptedString = removeChar(addaptedString, ",");

		formmatedValue = Double.parseDouble(addaptedString);

		formmatedValue *= multipliedBy;

		return formmatedValue;
	}

}
