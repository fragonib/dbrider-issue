CREATE TABLE table1
(
    id             UUID        NOT NULL,
    type           VARCHAR(20) NOT NULL,
    row_created_on TIMESTAMP   NOT NULL DEFAULT now(),

    CONSTRAINT pk_table1 PRIMARY KEY (id)
);

CREATE TABLE table2
(
    id             UUID        NOT NULL,
    type           VARCHAR(20) NOT NULL,
    row_created_on TIMESTAMP   NOT NULL DEFAULT now(),

    CONSTRAINT pk_table2 PRIMARY KEY (id)
);

