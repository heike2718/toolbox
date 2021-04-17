//=====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.monitoringapp.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.monitoringapp.MonitoringConfig;
import de.egladil.monitoringapp.MonitoringRunner;

/**
 * ScheduledMonitoringRunner
 */
public class ScheduledMonitoringRunner implements MonitoringRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduledMonitoringRunner.class);

	private boolean run = true;

	private final MonitoringConfig config;

	private final MonitoringRunner runnerDelegate;

	private final Random random;

	/**
	 * Erzeugt eine Instanz von ScheduledMonitoringRunner
	 */
	public ScheduledMonitoringRunner(final MonitoringConfig config) {
		super();
		this.config = config;
		this.runnerDelegate = new OneTimeMonitoringRunner(config);
		this.random = new Random();
		LOG.info("abfragen alle {} min", config.getPollIntervallMinutes());
	}

	@Override
	public void execute() {
		while (run) {

			if (Thread.currentThread().isInterrupted()) {
				this.run = false;
			} else {
				runnerDelegate.execute();
				sleep();
			}
		}
	}

	private void sleep() {
		int seconds = random.nextInt(config.getRandomIntervallSeconds());
		long sleepMs = (config.getPollIntervallMinutes() * 60 + seconds) * 1000;
		LOG.debug("warte {}s", sleepMs/1000);
		try {
			Thread.sleep(sleepMs);
		} catch (InterruptedException e) {
			this.run = false;
		}
	}

	@Override
	public void stop() {
		this.run = false;
	}

}
