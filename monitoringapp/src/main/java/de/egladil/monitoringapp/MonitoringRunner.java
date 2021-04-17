package de.egladil.monitoringapp;

import de.egladil.monitoringapp.impl.OneTimeMonitoringRunner;
import de.egladil.monitoringapp.impl.ScheduledMonitoringRunner;

public interface MonitoringRunner {

	/**
	 * Führt die Abfragen entweder ein einziges Mal aus oder startet eine Endlosschleife, um die Abfragen periodisch
	 * auszuführen.
	 */
	void execute();

	/**
	 * Sendet STop-Signal an die Instanz.
	 */
	void stop();

	/**
	 * Erzeugt in Abhängigkeit der Konfiguration den gewünschten Runner.
	 *
	 * @param config
	 * @return
	 */
	static MonitoringRunner create(final MonitoringConfig config) {
		if (config.getPollIntervallMinutes() == 0) {
			return new OneTimeMonitoringRunner(config);
		}
		return new ScheduledMonitoringRunner(config);
	}
}
