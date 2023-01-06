package jp.gr.java_conf.bunooboi.gams;

import java.util.Calendar;

public class CurrentTime {
	Calendar			c;
	public final int	YEAR;
	public final int	MONTH;
	public final int	DAY;
	public final int	HOUR;
	public final int	MINUTE;
	public final int	SECOND;

	public CurrentTime() {
		c = Calendar.getInstance();
		YEAR = c.get(Calendar.YEAR);
		MONTH = c.get(Calendar.MONTH) + 1;
		DAY = c.get(Calendar.DATE);
		HOUR = c.get(Calendar.HOUR_OF_DAY);
		MINUTE = c.get(Calendar.MINUTE);
		SECOND = c.get(Calendar.SECOND);
	}
}
