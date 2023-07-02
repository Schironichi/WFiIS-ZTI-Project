create view lista_ksiazek as
select k.id_ksiazka,
       tytul,
       autor,
       gatunek,
       opis,
       wydawnictwo,
       rok_wydania,
       COALESCE(SUM(dk.ilosc), 0) - COALESCE(SUM(rk.rezerwacja_ilosc), 0) as "dostepne"
from ksiazka k
    left join dostawa_ksiazka dk on k.id_ksiazka = dk.id_ksiazka
    left join rezerwacja_ksiazka rk on k.id_ksiazka = rk.id_ksiazka
group by k.id_ksiazka;

create view lista_dostawa as
select dk.id_dostawa,
       k.id_ksiazka,
       k.tytul       as "tytul",
       dk.ilosc      as "ilosc"
from dostawa_ksiazka dk
    left join ksiazka k on k.id_ksiazka = dk.id_ksiazka
order by dk.id_dostawa;

create view lista_rezerwacja as
select rk.id_rezerwacja,
       k.id_ksiazka,
       k.tytul       as "tytul",
       rk.czas_wypozyczenia,
       rk.rezerwacja_ilosc
from rezerwacja_ksiazka rk
    left join ksiazka k on k.id_ksiazka = rk.id_ksiazka
group by k.id_ksiazka;

create view rezerwacja_view as
select r.id_rezerwacja,
       r.email,
       r.telefon,
       r.imie,
       r.data_rezerwacji,
       MIN(rk.czas_wypozyczenia) as "wypozyczenie"
from rezerwacja r
    left join rezerwacja_ksiazka rk on r.id_rezerwacja = rk.id_rezerwacja
group by r.id_rezerwacja;