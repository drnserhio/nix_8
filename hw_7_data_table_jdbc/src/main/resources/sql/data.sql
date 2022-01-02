insert into employee(id, dateCreate, dateUpdate, firstname, lastname, username) VALUES
(1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Ivanov', 'Ivanov', 'ianov'),
(2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Dmitriy', 'Vasilevich', 'dmit123'),
(3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Konstantin', 'Ivanov', 'ks1234'),
(4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Kiril', 'Kirilov', 'kr'),
(5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Oram', 'Hayam', 'ohayam'),
(6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Krostopher', 'Columb', 'krio');


insert into department (id, dateCreate, dateUpdate, nameCompany, address) VALUES
(1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Nix-Solution', 'Karazina St, 2'),
(2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Epam', 'Kolomenskaya St, 63'),
(3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Logic', 'Novhorods''ka St, 3-B');

insert into relations values (1, 1);
insert into relations values (2, 1);
insert into relations values (3, 4);
insert into relations values (3, 5);
insert into relations values (2, 3);
insert into relations values (1, 6);




