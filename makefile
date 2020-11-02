JFLAGS = -g
JC = javac
AFLAGS = cfe
AC = jar

ifeq ($(OS),Windows_NT)
    RM = del /f /q
endif

default:
	$(JC) $(JFLAGS) *.java
	$(AC) $(AFLAGS) Adventure-Universal.jar Adventure *.class
	$(RM) *.class