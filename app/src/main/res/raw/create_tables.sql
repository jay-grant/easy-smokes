CREATE TABLE levels
(
    _id CHAR(25) NOT NULL,
    levelName CHAR(25) NOT NULL,
    PRIMARY KEY (_id),
    UNIQUE (levelName)
);

CREATE TABLE sides
(
    _id CHAR(25) NOT NULL,
    sideName CHAR(25) NOT NULL,
    PRIMARY KEY (_id),
    UNIQUE (sideName)
);

CREATE TABLE nades
(
    _id CHAR(3) NOT NULL,
    nadeName VARCHAR(50) NOT NULL,
    nadeLevel CHAR(25) NOT NULL,
    nadeSide CHAR(25) NOT NULL,
    demoCount INT(2) NOT NULL,
    PRIMARY KEY (_id),
    CONSTRAINT fk_NadeLevel FOREIGN KEY (nadeLevel) REFERENCES levels(_id),
    CONSTRAINT fk_NadeSide FOREIGN KEY (nadeSide) REFERENCES sides(_id)
);

CREATE TABLE favs
(
    _id CHAR(3),
    orderCounter INT(3),
    PRIMARY KEY (_id),
    CONSTRAINT fk_FavouritesNadeID FOREIGN KEY (_id) REFERENCES nades(_id),
    UNIQUE (orderCounter)
);

CREATE TABLE demos
(
    _id INT(3),
    demoNumber INT(1) NOT NULL,
    title VARCHAR(25),
    description VARCHAR(200),
    imgUrl VARCHAR(50),
    PRIMARY KEY (_id, demoNumber),
    CONSTRAINT fk_DemoNadeID FOREIGN KEY (_id) REFERENCES nades(_id)
);