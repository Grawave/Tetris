#Rakennekaavio
Mustat nuolet kuvaavat mistä paketista lähtee mihinkin pakettiin käskyjä.
![Rakennekaavio](/Dokumentaatio/Rakennekaavio.png)
#Selostus

###Logiikka ja Domain
koostavat pelin ytimen. Nämä luokat ovat luokkakaavion vasemmalla puolella, eli GameSituation, Field, Piece, Block, Direction, Formation, Rotation, PieceDropper. Ne eivät tiedä mitään GUI:sta, mutta niiltä voi kysyä pelin tilanteesta. Niitä voidaan käskyttää ulkopuolelta.
###GUI
luokat TetrisFrame, ContentPanel, GameSituationPanel, ja keybinding luokat muodostavat käyttäjälle näkyvän kokonaisuuden. Ne eivät tiedä suoraan Logiikkaa eivätkä Domainia, mutta niille voi syöttää tietoa pelitilanteesta ja käskeä piirtämään tilanteita. ContentPanelin sisältämät keybindingsit pystyvät ottamaan yhteyttä kommunikointiluokkaan ja kertoa käyttäjän napinpainalluksista.
###Kommunikointi
Yllä huomattiin, että Logiikka ja Domain eivät tiedä GUIta, ja että GUI ei tunne suoraan Logiikkaa ja Domainia. Luokkien välinen yhteys ja käskytys toimii CommunicationPlatformin kautta.

CommunicationPlatform tietää GameSituationin ja keskeisimmän GUI luokan TetrisFramen. GUI tuntee myös CommunicationPlatformin, jotta pystyy viestimään käyttäjän napinpainalluksiin pohjautuvan toiminnan eteenpäin.

CommunicationPlatform käskyttää GameSituationia kahdessa tapauksessa.
		      -PieceDropper kutsuu metodia movePiece(DOWN)
		      -Käyttäjän syötteen perusteella keybindings pakettiin kuuluva luokka kutsuu rotatePiece() tai movePiece()

Jokaisen kerran kun GameSituationia käskytetään, kerrotaan Guille päivityksen tarpeesta.