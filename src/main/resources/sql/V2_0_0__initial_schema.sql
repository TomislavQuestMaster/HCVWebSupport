CREATE TABLE clubs (
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	maxMembersSize INT(11) NULL DEFAULT NULL,
	username VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (id)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE users (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  password VARCHAR(255) NULL DEFAULT NULL,
  rightsLevel VARCHAR(255) NULL DEFAULT NULL,
  userType VARCHAR(255) NULL DEFAULT NULL,
  username VARCHAR(255) NULL DEFAULT NULL,
  club_id BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX FK_nck1mcsb5bmdb9ysei1f6omnl (club_id),
  CONSTRAINT FK_nck1mcsb5bmdb9ysei1f6omnl FOREIGN KEY (club_id) REFERENCES clubs (id)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE trainings (
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	defense SMALLINT(6) NULL DEFAULT NULL,
	description VARCHAR(1000) NULL DEFAULT NULL,
	fun SMALLINT(6) NULL DEFAULT NULL,
	goalie SMALLINT(6) NULL DEFAULT NULL,
	keypoints VARCHAR(1000) NULL DEFAULT NULL,
	lastUpdate BIGINT(20) NULL DEFAULT NULL,
	levels BLOB NULL,
	name VARCHAR(255) NULL DEFAULT NULL,
	offense SMALLINT(6) NULL DEFAULT NULL,
	price DOUBLE NULL DEFAULT NULL,
	stress SMALLINT(6) NULL DEFAULT NULL,
	tactics SMALLINT(6) NULL DEFAULT NULL,
	tags BLOB NULL,
	technics SMALLINT(6) NULL DEFAULT NULL,
	uniqueDeviceId INT(11) NULL DEFAULT NULL,
	updatingDeviceName VARCHAR(255) NULL DEFAULT NULL,
	owner_id BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (id),
	INDEX FK_7l05qa0qivlm7k0mrut2o7wo0 (owner_id),
	CONSTRAINT FK_7l05qa0qivlm7k0mrut2o7wo0 FOREIGN KEY (owner_id) REFERENCES users (id)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE packages (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  description VARCHAR(1000) NULL DEFAULT NULL,
  groupId BIGINT(20) NOT NULL,
  keypoints VARCHAR(1000) NULL DEFAULT NULL,
  lastUpdate BIGINT(20) NULL DEFAULT NULL,
  name VARCHAR(255) NULL DEFAULT NULL,
  type INT(11) NULL DEFAULT NULL,
  updatingDeviceName VARCHAR(255) NULL DEFAULT NULL,
  owner_id BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX FK_3d9f4n4jgq27ock6r9vkqbgqx (owner_id),
  CONSTRAINT FK_3d9f4n4jgq27ock6r9vkqbgqx FOREIGN KEY (owner_id) REFERENCES users (id)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE packages_trainings (
  packages_id BIGINT(20) NOT NULL,
  trainings_id BIGINT(20) NOT NULL,
  INDEX FK_k8f1wco1cj54ujo98b2l4pqgt (trainings_id),
  INDEX FK_ogv1kdlgo14rjf0cgiy9bquak (packages_id),
  CONSTRAINT FK_ogv1kdlgo14rjf0cgiy9bquak FOREIGN KEY (packages_id) REFERENCES packages (id),
  CONSTRAINT FK_k8f1wco1cj54ujo98b2l4pqgt FOREIGN KEY (trainings_id) REFERENCES trainings (id)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;

CREATE TABLE calendar (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  lastUpdate BIGINT(20) NULL DEFAULT NULL,
  notes VARCHAR(1000) NULL DEFAULT NULL,
  since BIGINT(20) NULL DEFAULT NULL,
  until BIGINT(20) NULL DEFAULT NULL,
  updatingDeviceName VARCHAR(255) NULL DEFAULT NULL,
  owner_id BIGINT(20) NULL DEFAULT NULL,
  packageItem_id BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX FK_3reuenj0nqre1n4ygyrxr153i (owner_id),
  INDEX FK_r9pcautagyqyj2ynvvtjf7kik (packageItem_id),
  CONSTRAINT FK_r9pcautagyqyj2ynvvtjf7kik FOREIGN KEY (packageItem_id) REFERENCES packages (id),
  CONSTRAINT FK_3reuenj0nqre1n4ygyrxr153i FOREIGN KEY (owner_id) REFERENCES users (id)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;
