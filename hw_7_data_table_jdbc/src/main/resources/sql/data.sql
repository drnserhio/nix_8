insert into employee(id, dateCreate, dateUpdate, firstname, lastname, username) VALUES
(1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Ivanov', 'Ivanov', 'ianov'),
(2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Dmitriy', 'Vasilevich', 'dmit123'),
(3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Konstantin', 'Ivanov', 'ks1234'),
(4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Kiril', 'Kirilov', 'kr'),
(5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Oram', 'Hayam', 'ohayam'),
(6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Krostopher', 'Columb', 'krio'),
(7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Abab', 'Bab', 'Cac'),
(8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DD', 'DD', 'DD'),
(9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'CC', 'CC', 'CC'),
(10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'TT', 'TT', 'TT'),
(11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ZZ', 'ZZ', 'ZZ'),
(12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'VV', 'VV', 'VV'),
(13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NN', 'NN', 'NN'),
(14, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'YY', 'YY', 'CaYYc');



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




