#Aihemäärittely


**Aihe:**Tetris peli toteutetaan javalla. Pelissä on neljästä ruudusta koostuvia geometrisiä muotoja jotka tippuvat kohti pohjaa. Jos ruudulle muodostuu täysi rivi ilman aukkoja, rivi katoaa ja kaikki yläpuolella olevat rivit tippuvat yhden alemmas.


Logiikka ja graafinen käyttöliittymä pyritään eriyttämään mahdollisimman selkeästi.

Graafinen käyttöliittymä perustuu javan valmiiseen Swing kirjastoon.

**Käyttäjät:** Pelaajat

**Pelaajien toiminnot:**
- pelin aloitus
- palikan siirtäminen horisontaaliakselilla
- palikan kääntäminen 90 astetta
- palikan kiihdytys


##Luokkakaavio
![Luokkakaavio](/Dokumentaatio/LuokkaKaavio.png)

GameSituation tietää luokat Field ja Piece. Sillä on metodi jonka avulla palasta voidaan liikuttaa kentässä.

Engine on luokka jonka avulla peli käynnistetään, ja GameSituationia muutetaan.

Seuraavaksi hahmotellaan eri threadeja. Luokka joka kuuntelee käyttäjän painalluksia ja luokka joka välittää siirtokäskyn alaspäin sekunnin välein. Luokat toimivat todennäköisesti luokan Engine kautta.