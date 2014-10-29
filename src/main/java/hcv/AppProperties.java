package hcv;

import java.io.IOException;
/**
 * @author tdubravcevic
 */
public enum AppProperties {

	FILE_LOCATION ("hcv.file.manager.location", "C:\\Users\\tdubravcevic\\Downloads"),
	;

	private final String name;
	private final String defaultValue;

	private AppProperties(String name, String defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		String value = System.getProperties().getProperty(name);
		if (value != null) return value;
		return defaultValue;
	}

	public int getIntValue() throws IOException {
		String value = getValue();
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			throw new IOException(name + " property not an integer: " + value + " caused by: " + e.toString());
		}
	}

	public boolean getBooleanValue() {
		String value = getValue();
		return value.equalsIgnoreCase("true");
	}

	public void setValue(String value) {
		System.getProperties().setProperty(name, value);
	}

}

