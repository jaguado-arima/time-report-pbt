package eu.arima.tr.reports.reportsServiceImpl;

import net.jqwik.api.EdgeCasesMode;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class JqwikPropertiesTests {

	@Property(edgeCases = EdgeCasesMode.FIRST, tries = 30)
	void printCombinedValuesOfTwoParams(@ForAll @IntRange(min = 0, max = 10) int a,
			@ForAll @IntRange(min = 0, max = 10) int b) {
		String parameters = String.format("%s, %s", a, b);
		System.out.println(parameters);
	}

	@Property(edgeCases = EdgeCasesMode.FIRST, tries = 30)
	void printCombinedValuesOfThreParams(@ForAll @IntRange(min = 0, max = 10) int a,
			@ForAll @IntRange(min = 10, max = 20) int b, @ForAll @IntRange(min = 20, max = 30) int c) {
		String parameters = String.format("%s, %s, %s", a, b, c);
		System.out.println(parameters);
	}

}
