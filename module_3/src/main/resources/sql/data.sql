delete emp from employees emp;
delete dep from departments dep;

insert into employees(id, firstname, lastname, username) VALUES
(1,  'Ivanov', 'Ivanov', 'ianov'),
(2,  'Dmitriy', 'Vasilevich', 'dmit123'),
(3,  'Konstantin', 'Ivanov', 'ks1234'),
(4,  'Kiril', 'Kirilov', 'kr'),
(5,  'Oram', 'Hayam', 'ohayam'),
(6,  'Krostopher', 'Columb', 'krio'),
(7,  'Abab', 'Bab', 'Cac'),
(8,  'DD', 'DD', 'DD'),
(9,  'CC', 'CC', 'CC'),
(10,  'TT', 'TT', 'TT'),
(11,  'ZZ', 'ZZ', 'ZZ'),
(12,  'VV', 'VV', 'VV'),
(13,  'NN', 'NN', 'NN'),
(14,  'YY', 'YY', 'CaYYc');



insert into departments (id, nameCompany, address) VALUES
(1, 'Nix-Solution', 'Karazina St, 2'),
(2, 'Epam', 'Kolomenskaya St, 63'),
(3, 'Logic', 'Novhorods''ka St, 3-B');

insert into relations values (1, 1);
insert into relations values (2, 1);
insert into relations values (3, 4);
insert into relations values (3, 5);
insert into relations values (2, 3);
insert into relations values (1, 6);




