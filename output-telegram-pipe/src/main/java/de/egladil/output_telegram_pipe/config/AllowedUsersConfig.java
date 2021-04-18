// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AllowedUsersConfig
 */
public class AllowedUsersConfig {

	@JsonProperty
	private String ansibleUser;

	@JsonProperty
	private String root;

	@JsonProperty
	private String sshUser;

	@JsonProperty
	private String sudoUser;

	/**
	 * @return the ansibleUser
	 */
	public String ansibleUser() {

		return ansibleUser;
	}

	/**
	 * @param ansibleUser
	 *                    the ansibleUser to set
	 */
	public AllowedUsersConfig withAnsibleUser(final String ansibleUser) {

		this.ansibleUser = ansibleUser;
		return this;
	}

	/**
	 * @return the root
	 */
	public String root() {

		return root;
	}

	/**
	 * @param root
	 *             the root to set
	 */
	public AllowedUsersConfig withRoot(final String root) {

		this.root = root;
		return this;
	}

	/**
	 * @return the sshUser
	 */
	public String sshUser() {

		return sshUser;
	}

	/**
	 * @param sshUser
	 *                the sshUser to set
	 */
	public AllowedUsersConfig withSshUser(final String sshUser) {

		this.sshUser = sshUser;
		return this;
	}

	/**
	 * @return the sudoUser
	 */
	public String sudoUser() {

		return sudoUser;
	}

	/**
	 * @param sudoUser
	 *                 the sudoUser to set
	 */
	public AllowedUsersConfig withSudoUser(final String sudoUser) {

		this.sudoUser = sudoUser;
		return this;
	}

}
