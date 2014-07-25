package hcv.core.metrics;

import com.codahale.metrics.Counter;

import java.util.Map;

/**
 * @author tdubravcevic
 */
public class LoginCounters {

	private Map<String, Counter> counterMap;

	public LoginCounters(Map<String, Counter> counterMap) {

		this.counterMap = counterMap;
	}

	public Map<String, Counter> getCounterMap() {

		return counterMap;
	}

	public void setCounterMap(Map<String, Counter> counterMap) {

		this.counterMap = counterMap;
	}
}
