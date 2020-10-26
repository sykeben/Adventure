JFLAGS = -verbose
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        Adventure.java \
        Battle.java \
        Dungeon.java \
        InitStatus.java \
		Move.java \
		Player.java \
		Room.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class