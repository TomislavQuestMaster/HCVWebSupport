package hcv.metrics;

import com.codahale.metrics.MetricSet;

/**
 * @author tdubravcevic
 */
public interface IMetricsProvider {

	public MetricSet getMetricSet();
}
