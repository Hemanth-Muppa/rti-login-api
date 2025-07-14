CREATE TABLE NetUser (
    userCode BIGINT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    mobile VARCHAR(20) NOT NULL,
    userType VARCHAR(20),
    activeIdle CHAR(1),
    activationKey VARCHAR(100),
    activationKeyConf VARCHAR(100),
    loginAttempts INT,
    lastSuccessfulLogin DATETIME,
    lastUnSuccessfulLogin DATETIME
);

CREATE TABLE LoginHistory (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    loginDateTime DATETIME,
    ip VARCHAR(50),
    browser VARCHAR(100),
    os VARCHAR(100),
    userName VARCHAR(100),
    uCode BIGINT
);
