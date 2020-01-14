DROP DATABASE IF EXISTS super_being_sightings_test;

CREATE DATABASE IF NOT EXISTS super_being_sightings_test;

USE super_being_sightings_test;

CREATE TABLE IF NOT EXISTS Superbeing
	(
		SuperbeingId Int(11) NOT NULL auto_increment,
        SuperbeingName VARCHAR(80) NOT NULL,
        SuperbeingDescription VARCHAR(140) NOT NULL,
        SuperbeingPower TINYTEXT NOT NULL,
        HeroOrVillain VARCHAR(7) NOT NULL,
        PRIMARY KEY (SuperbeingId)
    )
    Engine = InnoDB;
    
CREATE TABLE IF NOT EXISTS Location
	(
		LocationId Int(11) NOT NULL auto_increment,
        LocationName VARCHAR(60) NOT NULL,
        LocationDescription VARCHAR(60) NOT NULL,
        LocationAddress VARCHAR(100) NOT NULL,
        LocationStreetName VARCHAR(40) NOT NULL,
        LocationCityName VARCHAR(50) NOT NULL,
        LocationStateName VARCHAR(25) NOT NULL,
        LocationZipCode VARCHAR(10) NOT NULL,
        Latitude DECIMAL(9,6),
        Longitude DECIMAL(9,6),
        Primary Key (LocationId)
	)
    ENGINE = InnoDB;
    
CREATE TABLE IF NOT EXISTS Organization
	(
		OrganizationId INT(11) NOT NULL auto_increment,
        OrganizationName VARCHAR(100) NOT NULL,
        OrganizationDescription VARCHAR(200),
        OrganizationPhoneNumber VARCHAR(10) NOT NULL,
        LocationId INT(11),
        Primary Key (OrganizationId)
	)
    ENGINE = InnoDB;
    
CREATE TABLE IF NOT EXISTS Sighting
	(
		SightingId INT(11) NOT NULL auto_increment,
        LocationId INT(11),
        SightingDate DATETIME NOT NULL,
        Primary Key (SightingId)
    )
	ENGINE = InnoDB;
    
CREATE TABLE IF NOT EXISTS SuperbeingOrganization
	(
		SuperbeingId INT(11) NOT NULL,
        OrganizationId INT(11) NOT NULL,
        Primary Key (SuperbeingId, OrganizationId)
	)
    Engine = InnoDB;
    
CREATE TABLE IF NOT EXISTS SuperbeingSighting
	(
		SuperbeingId INT(11) NOT NULL,
        SightingId INT(11) NOT NULL,
        Primary Key (SuperbeingId, SightingId)
	)
    Engine = InnoDB;
    
CREATE TABLE IF NOT EXISTS SuperbeingLocation
	(
		SuperbeingId INT(11) NOT NULL,
        LocationId INT(11) NOT NULL,
        Primary Key (SuperbeingId, LocationId)
	)
    Engine = InnoDB;
    
ALTER TABLE Organization
ADD CONSTRAINT fk_organization_location
FOREIGN KEY (LocationId) REFERENCES Location (LocationId) ON DELETE SET NULL,
MODIFY COLUMN OrganizationName VARCHAR(100) CHARACTER SET UTF8 COLLATE utf8_general_ci;

ALTER TABLE Superbeing
MODIFY COLUMN SuperbeingName VARCHAR(80) CHARACTER SET UTF8 COLLATE utf8_general_ci;

ALTER TABLE Location
MODIFY COLUMN LocationName VARCHAR(60) CHARACTER SET UTF8 COLLATE utf8_general_ci;

ALTER TABLE Sighting
ADD CONSTRAINT fk_sighting_location
FOREIGN KEY (LocationId) REFERENCES Location (LocationId) ON DELETE set Null;    
    
ALTER TABLE SuperbeingOrganization
ADD CONSTRAINT fk_superbeingorganization_superbeing
FOREIGN KEY (SuperbeingID) REFERENCES Superbeing (SuperbeingId) ON DELETE CASCADE,
ADD CONSTRAINT fk_superbeingorganization_organization
FOREIGN KEY (OrganizationId) REFERENCES Organization (OrganizationId) ON DELETE CASCADE;
    
ALTER TABLE SuperbeingSighting
ADD CONSTRAINT fk_superbeingsighting_superbeing
FOREIGN KEY (SuperbeingId) REFERENCES Superbeing (SuperbeingId) ON DELETE CASCADE,
ADD CONSTRAINT fk_superbeingsighting_sighting
FOREIGN KEY (SightingId) REFERENCES Sighting (SightingId) ON DELETE CASCADE;

ALTER TABLE SuperbeingLocation
ADD CONSTRAINT fk_superbeinglocation_superbeing
FOREIGN KEY (SuperbeingId) REFERENCES Superbeing (SuperbeingId) ON DELETE CASCADE,
ADD CONSTRAINT fk_superbeinglocation_locatio
FOREIGN KEY (LocationId) REFERENCES Location (LocationId) ON DELETE CASCADE;

INSERT INTO Superbeing (SuperbeingName, SuperbeingDescription, SuperbeingPower, HeroOrVillain) VALUES ('Iron Man', 'Tony Stark in a suit of armor.', 'High tech power armor and genius intelligence', 'Hero');
INSERT INTO Superbeing (SuperbeingName, SuperbeingDescription, SuperbeingPower, HeroOrVillain) VALUES ('The Sentry', 'OP superhero', 'Flight, Energy Projection, Matter manipulation, Extreme strength, Invulnerability', 'Hero');
INSERT INTO Superbeing (SuperbeingName, SuperbeingDescription, SuperbeingPower, HeroOrVillain) VALUES ('Spiderman', 'Human mutated by spider DNA', 'Spider abilities, super strength and durability', 'Hero');
INSERT INTO Superbeing (SuperbeingName, SuperbeingDescription, SuperbeingPower, HeroOrVillain) VALUES ('The Incredible Hulk', 'Bruce Banner transformation', 'Extreme physical strength and durability, less intelligence', 'Hero');
INSERT INTO Superbeing (SuperbeingName, SuperbeingDescription, SuperbeingPower, HeroOrVillain) VALUES ('All Might', 'The Symbol of Peace', 'One for All', 'Hero');

INSERT INTO Location (LocationName, LocationDescription, LocationAddress, LocationStreetName, LocationCityName, LocationStateName, LocationZipCode, Latitude, Longitude) VALUES ('Avengers Tower', 'Avengers Headquarters', '200', 'Park Ave', 'Manhattan', 'NY', '10166', '40.7535', '73.9768');
INSERT INTO Location (LocationName, LocationDescription, LocationAddress, LocationStreetName, LocationCityName, LocationStateName, LocationZipCode, Latitude, Longitude) VALUES ('Empire State Building', 'Tall building', '20 w', '34th St', 'New York', 'NY', '10001', '40.7484', '73.9857');

INSERT INTO Organization (OrganizationName, OrganizationPhoneNumber, LocationId) Values ('The Avengers', '5555555555', 1);
INSERT INTO Organization (OrganizationName, OrganizationPhoneNumber, LocationId) Values ('The Arachnids', '4561239081', 2);

INSERT INTO Sighting(LocationId, SightingDate) VALUES (1, '2018-10-4');
INSERT INTO Sighting(LocationId, SightingDate) VALUES (1, '2018-9-25');
INSERT INTO Sighting(LocationId, SightingDate) VALUES (2, '2018-10-3');

INSERT INTO SuperbeingSighting (SuperbeingId, SightingId) VALUES (1, 1);
INSERT INTO SuperbeingSighting (SuperbeingId, SightingId) VALUES (3, 2);
INSERT INTO SuperbeingSighting (SuperbeingId, SightingId) VALUES (1, 3);

INSERT INTO SuperbeingOrganization (SuperbeingId, OrganizationId) VALUES (1, 1);
INSERT INTO SuperbeingOrganization (SuperbeingId, OrganizationId) VALUES (3, 1);
INSERT INTO SuperbeingOrganization (SuperbeingId, OrganizationId) VALUES (3, 2);

INSERT INTO SuperbeingLocation (SuperbeingId, LocationId) VALUES (1, 1);
INSERT INTO SuperbeingLocation (SuperbeingId, LocationId) VALUES (3, 1);
INSERT INTO SuperbeingLocation (SuperbeingId, LocationId) VALUES (1, 2);

CREATE TABLE IF NOT EXISTS `users` (
 `user_id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(20) NOT NULL,
 `password` varchar(100) NOT NULL,
 `enabled` tinyint(1) NOT NULL,
 PRIMARY KEY (`user_id`),
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
--
-- Dumping data for table `users`
--
INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'admin', '$2a$10$IjNWJ5DZ76vF/ceQlqjVX.TA5ChwgyQrGVZP8EfSlLJlDTC0l019G', 1),
(2, 'user', '$2a$10$IjNWJ5DZ76vF/ceQlqjVX.TA5ChwgyQrGVZP8EfSlLJlDTC0l019G', 1);
--
-- Table structure for table `authorities`
--
CREATE TABLE IF NOT EXISTS `authorities` (
 `username` varchar(20) NOT NULL,
 `authority` varchar(20) NOT NULL,
 KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_SIDEKICK'),
('user', 'ROLE_SIDEKICK');
--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON update cascade;
