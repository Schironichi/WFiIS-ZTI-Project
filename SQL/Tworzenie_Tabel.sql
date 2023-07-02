-- Tworzenie tabel --
CREATE TABLE dostawa
(
    id_dostawa        SERIAL PRIMARY KEY,
    data              DATE    NOT NULL
);
CREATE TABLE ksiazka
(
    id_ksiazka        SERIAL PRIMARY KEY,
    autor             VARCHAR NOT NULL,
    tytul             VARCHAR NOT NULL,
    gatunek           VARCHAR NOT NULL,
    opis              VARCHAR,
    wydawnictwo       VARCHAR NOT NULL,
    rok_wydania       INTEGER NOT NULL,
    wypozyczona_ilosc INTEGER NOT NULL
);
CREATE TABLE dostawa_ksiazka
(
    id_dostawa INTEGER NOT NULL,
    id_ksiazka INTEGER NOT NULL,
    ilosc      INTEGER NOT NULL
);
CREATE TABLE rezerwacja
(
    id_rezerwacja    SERIAL PRIMARY KEY,
    email            VARCHAR NOT NULL,
    imie             VARCHAR NOT NULL,
    telefon          VARCHAR NOT NULL,
    data_rezerwacji  DATE    NOT NULL
);
CREATE TABLE rezerwacja_ksiazka
(
    id_ksiazka        INTEGER NOT NULL,
    id_rezerwacja     INTEGER NOT NULL,
    czas_wypozyczenia INTEGER NOT NULL,
    rezerwacja_ilosc  INTEGER NOT NULL
);
-- 
ALTER TABLE dostawa_ksiazka
    ADD PRIMARY KEY (id_dostawa, id_ksiazka);
ALTER TABLE rezerwacja_ksiazka
    ADD PRIMARY KEY (id_ksiazka, id_rezerwacja);
-- Modyfikacja tabel - referencje kluczy obcych --
ALTER TABLE dostawa_ksiazka
    ADD FOREIGN KEY (id_dostawa) REFERENCES dostawa (id_dostawa);
ALTER TABLE dostawa_ksiazka
    ADD FOREIGN KEY (id_ksiazka) REFERENCES ksiazka (id_ksiazka);
ALTER TABLE rezerwacja_ksiazka
    ADD FOREIGN KEY (id_ksiazka) REFERENCES ksiazka (id_ksiazka);
ALTER TABLE rezerwacja_ksiazka
    ADD FOREIGN KEY (id_rezerwacja) REFERENCES rezerwacja (id_rezerwacja);