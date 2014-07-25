package hcv.core.flyway;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.FlywayException;

import javax.sql.DataSource;
import java.nio.file.Paths;

/**
 * @author tdubravcevic
 */
public final class FlywayDatabaseMigrator {

	private final Flyway flyway;

	public FlywayDatabaseMigrator() {

		this.flyway = new Flyway();
	}

	/**
	 * Drops all objects (tables, views, procedures, triggers, ...) in the configured schemas. The schemas are cleaned
	 * in the order specified by the {@code schemas} property.
	 *
	 * @throws MigrationException when the clean fails.
	 */
	public void clean() {

		try {
			flyway.clean();
		} catch (FlywayException e) {
			throw new MigrationException("Database clean failed.", e);
		}
	}

	/**
	 * Migrates the database.
	 *
	 * @throws MigrationException if the migration fails.
	 */
	public void migrateDatabase(DataSource source) throws MigrationException {

		flyway.setDataSource(source);
		flyway.setLocations(Paths.get("sql").toString());
		//flyway.setPlaceholders(databaseType.getKeywords());

		try {
			flyway.migrate();
		} catch (FlywayException e) {
			throw new MigrationException("Database migration failed.", e);
		}

	}
}
