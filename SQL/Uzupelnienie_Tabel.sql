insert into dostawa (data)
values ('2023-01-22'),
       ('2023-01-18'),
       ('2023-01-26');

insert into ksiazka (autor, tytul, gatunek, opis, wydawnictwo, rok_wydania, wypozyczona_ilosc)
values ('Remigusz Mróz', 'Projekt Riese', 'thriller', 'Nic nie jest ostateczne. Nawet śmierć.', 'Filia', '2022', 0),
       ('Holly Black', 'Okrutny książę. Tom 1', 'science-fiction', 'Pierwszy tom trylogii. Historia siedemnastoletniej dziewczyny, która została porwana do Elysium, świata elfów, i będzie musiała poradzić sobie w tej niebezpiecznej krainie.', 'Jaguar', '2018', 0),
       ('Sarah J. Mass', 'Dwór cierni i róż. Tom 1', 'fantasy', 'Autorka bestsellerowej serii "Szklany tron" powraca z porywającą opowieścią o miłości, która jest w stanie pokonać nienawiść i uprzedzenia!', 'Uroboros', '2016', 0),
       ('Sarah J. Mass', 'Dwór mgieł i furii. Dwór cierni i róż. Tom 2', 'fantasy', 'Między światłem a ciemnością rozgrywa się walka, w której stawką są losy całego świata. A w magicznym świecie Fae przyjaciele potrafią być bardziej niebezpieczni niż wrogowie…', 'Uroboros', '2017', 0),
       ('John Flanagan', 'Ruiny Gorlanu. Zwiadowcy. Tom 1', 'fantasy', 'Pierwszy tom jednej z najpoczytniejszych serii fantasy na świecie.', 'Jaguar', '2021', 0),
       ('John Flanagan', 'Płonący most. Zwiadowcy. Tom 2', 'fantasy', 'Drugi tom serii o przygodach Willa i jego przyjaciół.', 'Jaguar', '2021', 0),
       ('John Flanagan', 'Ziemia skuta lodem. Zwiadowcy. Tom 3', 'fantasy', 'Trzeci tom bestsellerowej sagi o Korpusie Zwiadowców.', 'Jaguar', '2021', 0),
       ('Alice Oseman', 'Heartstopper. Tom 1', 'młodzieżowa', 'Powieść graficzna LGBTQ+ o życiu, miłości i wszystkim, co dzieje się pomiędzy – gratka dla fanów filmu Twój Simon i książek Holly Bourne.', 'Jaguar', '2021', 0);

insert into dostawa_ksiazka (id_dostawa, id_ksiazka, ilosc)
values (1, 5, 5),
       (1, 6, 6),
       (1, 7, 7),
       (1, 2, 8),
       (2, 1, 9),
       (2, 3, 10),
       (2, 4, 11),
       (3, 2, 12);