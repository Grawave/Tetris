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

Engine on luokka jonka avulla peli käynnistetään

CommunicationPlatform välittää komentoja GUIn ja logiikan välillä.

#Sekvenssikaavioita
##Uusi Peli
Seuraava kaavio näyttää miten peli päättyy pieceDropperin käskystä movePiece(DOWN). Tämän jälkeen luodaan uusi peli pelaajan käskystä.
-	GameSituationin move metodi huomaa, että palaa ei voida siirtää alaspäin, kutsutaan Fieldin freeze metodia
-	Freeze metodi huomaa että pala ei voi olla edes nykyisellä paikallaan, palautetaan MoveResult joka sisältää tiedon pelin häviöstä
-	GameSituationin move metodi palauttaa annetun MoveResult olion Communicatorille.
-	Commmunicator lopettaa pelin, tallentaa pisteet ja luo EnFramen
-	EndFramessa on nappula josta klikkaamalla käsketään Communicatorin luoda uusi peli
-	Communicator luo uuden pelin. 
![newGameSequence] (/Dokumentaatio/Sekvenssikaaviot/newGameSequence.png)

##Piecedropper, onnistunut palan siirtäminen
![PieceDropper] (/Dokumentaatio/Sekvenssikaaviot/PieceDropperDropsPieceSuccesfully.png)

##MovePiece, onnistunut palan siirtäminen.
![MovePiece](/Dokumentaatio/Sekvenssikaaviot/movePiece.png)

##MovePiece, palan siirtäminen joka johtaa palan jäädyttämiseen
![FreezePiece](/Dokumentaatio/Sekvenssikaaviot/freezePiece.png)
