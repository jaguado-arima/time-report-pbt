package eu.arima.tr.reports.reportsServiceImpl;

import static org.mockito.Mockito.mock;

import java.time.DateTimeException;
import java.time.LocalDate;

import eu.arima.tr.reports.DayStatusSummary;
import eu.arima.tr.reports.ReportsServiceImpl;
import eu.arima.tr.worklogs.WorklogRepository;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.lifecycle.BeforeProperty;

public class GetDayStatusSummaryForWorkerAndDayPropertyTests {

	ReportsServiceImpl reportsService;

	@BeforeProperty
	void setup() {
		reportsService = new ReportsServiceImpl(mock(WorklogRepository.class));
	}

	@Property
	@Label("Given the username of a worker the status result has that username and date")
	boolean the_status_result_belongs_to_the_requested_worker(@ForAll String username,
			@ForAll("localdates") LocalDate date) {

		DayStatusSummary result = reportsService.getDayStatusSummaryForWorkerAndDay(username, date);

		return result.getDate().equals(date) && result.getWorkerUserName().equals(username);

	}

	@Provide
	Arbitrary<LocalDate> localdates() {
		Arbitrary<Integer> years = Arbitraries.integers().between(1900, 2099);
		Arbitrary<Integer> months = Arbitraries.integers().between(1, 12);
		Arbitrary<Integer> days = Arbitraries.integers().between(1, 31);

		return Combinators.combine(years, months, days).as(LocalDate::of).ignoreException(DateTimeException.class);
	}

}
