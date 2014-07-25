package hcv.core.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlet.InstrumentedFilterContextListener;
import com.codahale.metrics.servlets.HealthCheckServlet;
import com.codahale.metrics.servlets.MetricsServlet;

import javax.servlet.ServletContextEvent;

/**
 * @author tdubravcevic
 */
public class HcvInstrumentedFilterContextListener extends InstrumentedFilterContextListener {

	MetricRegistry metricRegistry = new MetricRegistry();
	HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

	@Override
	protected MetricRegistry getMetricRegistry() {
		return metricRegistry;
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().setAttribute(HealthCheckServlet.HEALTH_CHECK_REGISTRY,healthCheckRegistry);
		servletContextEvent.getServletContext().setAttribute(MetricsServlet.METRICS_REGISTRY, metricRegistry);
	}
}
