program test
  use monkey
  implicit none
  type(apina) :: eka,toka
  integer :: x

  x=3

  eka =apina("aabraham", 12)

  print *,'hei, olen ', eka%nimi, ' ja olen pirun vanha (',eka%ika,')' 

  toka=eka+x
  print *,'hei, olen ', eka%nimi, ' ja olen pirun vanha (',eka%ika,')'
  
end program test
