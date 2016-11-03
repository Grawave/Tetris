# Tetris
Java labra

Muokkaus !2
UUsi muokkaus. tämän ei tulisi näkyä varjorepossa


Ongelma: Git & Netbeans: 
input: git status
output ei sisällä tiedostoa .gitignore.

Mitä tein? Tuli error index file smaller than expected. Korjasin sen seuraavasti (stackoverflow):
isjani@da5-cs-bk107-23:~/Tetris$ rm .git/index
isjani@da5-cs-bk107-23:~/Tetris$ git add .

