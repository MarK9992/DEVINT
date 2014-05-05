rem set PATH=../../lib/;../../jre/bin/
rem -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar;../ressources/lib/slick/slick.jar;../ressources/lib/slick/lwjgl.jar -d ../bin src/*.java

javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar;../ressources/lib/slick/slick.jar;../ressources/lib/slick/lwjgl.jar -d ../bin level/*.java main/*.java map/*.java objects/*.java states/*.java util/*.java
pause
