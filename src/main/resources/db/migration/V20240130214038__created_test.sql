CREATE TABLE bag (
    bag_id BIGINT AUTO_INCREMENT,
    PRIMARY KEY (bag_id)
);

CREATE TABLE item (
    item_id BIGINT AUTO_INCREMENT,
    item_name varchar(100),
    bag_id BIGINT,
    PRIMARY KEY (item_id)
);
