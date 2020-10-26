              .
               )  ,
              (    )
            ___)__(_____,
            \__________/ \
             \________/__/
           .__\______/__.
+----------(____________)-----------+
|                                   |
|     T H E   Q U E S T   F O R     |
|  ,_____,                          |
|  |_, ,_|  __ _   __   __   __ _   |
|    | |   / _` |  \ \ / /  / _` |  |
|    | |  | (_| |   \ V /  | (_| |  |
|   _/ |   \__,_|    \_/    \__,_|  |
|  |__/                             |
|                                   |
+-----------------------------------+
 A mini text adventure by Ben Sykes.

========= BACKSTORY & TL;DR =========

              Welcome!

You, the player are magically placed
in a large dungeon. Through careful
observation (and maybe a hint from a
note placed near you), you discover
that you have been teleported to the
Javaverse.

Your goal is defeat the math-hungry
monsters littered around the dungeon,
reach the goal, and escape. You have
3 lives, each which can be lost in
battle. Good luck!

============ USER INPUT =============

                 >
         This is your friend.

Since you can't really play a game
without interacting with it, it's
important to know when the game is
prompting you to do something. When
you see the greater-than symbol, the
game is prompting you to enter some
information, usually an answer to a
question posed just before the prompt
character.

=========== THE PLAYFIELD ===========

     +----+----+----+----+----+
     | @] |    |    |    |    |
     +----+----+----+----+----+
     |    |    |    |  ! |    |
     +----+----+----+----+----+
     |    |  $ |    |  $ |  $ |
     +----+----+----+----+----+
     |    |    |    |  $ |  $ |
     +----+----+----+----+----+
     |    |    |    |    |  $ |
     +----+----+----+----+----+
       This is the playfield.

The most important part of playing
TWFJ is the playfield. This critical
element displays where you, monsters,
and the goal are with these symbols:

          @       $       !

Pay attention to these symbols and
where they are while planning out the
most efficient way to beat the game.

============ SPAWN POINT ============

                 ]
           It begins here.

At the start of each game, you spawn
at the spawn point, represnted by the
right square bracket. This is a safe
zone where no monsters can spawn.

============ THE PLAYER =============

                 @
            This is you!

You are represnted by the AT symbol.
When prompted, you can move up, down,
left, right, or stay simply by typing
in just that. If you are a shorthand
kind of person, you can also type in
just the first letter of where you
would like to go.

============= MONSTERS ==============

                 $
          These are evil.

Monsters are represnted with dollar
signs. When you move into a room with
one, a "battle" will be initiated. In
order to defeat the monster and make
it disappear, you must answer their
question correctly. If you fail to do
so, you will be defeated, resulting
in a life being lost and you being
sent back to spawn. If you lose all
3 lives, you will be killed an the
game will end.

============= THE GOAL ==============

                 !
          Get here & win.

The goal, represnted by a bang (aka
an exclamation mark), does what it
says on the box. After defeating all
monsters on the playfield, come here
to claim your victory and beat the
game!

=========== OTHER GOALS ============

       The clock is ticking.

If your looking to take on another
challenge, why not pit yourself
against time itself? Each round is
timed, and that time is displayed
when it ends.

=========== RESTRICTIONS ===========

       Oh, this is a project?

Ok, proof I have obeyed the project
restrictions is right here:

   1. The first custom if statement
      is located on line 59.

   2. The first custom if-else-if
      statement begins on line 211.

   3. && is utilized on line 93, and
      || is utilized on line 209.

   4. I am not comparing objects as
      a whole, just values returned
      from functions within those
      objects.

   5. The game itself ends when the
      statement contained in the
      while loop at line 128 returns
      false. If the player does not
      choose to play another round
      when prompted at line 225,
      the program cleans up after
      itself and closes.

   6. The game is won once all the
      monsters are defeated (when
      monsterCount <= 0), and lost
      once the player loses all lives
      (you.isDead() returns true).

   7. Monster positions, the goal
      position, and battle questions
      are all randomized.

   8. Comments are everywhere. The
      first example of one is found
      on line 1.

============= COMPILING =============

       if (!developer) skip();

The following information only
pertains to developers or users who
did not recieve a precompiled JAR.

Being a Java application, this should
be easy to compile on any platform
through use of the included Makefile.

For most people, a simple "make"
should do the trick. However, if
something goes terribly wrong, you
can compile a version with all the
debugging info needed for testing
with "make debug".

Note: If you are on Windows, you may
need to replace any instances of
"$(RM)" with "del" in the Makefile
as some Windows "make" tools do not
properly map the command to delete
or remove files.

=====================================
(C)2020 - Ben Sykes