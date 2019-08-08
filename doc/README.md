# slogo

Project members: Natalie Huffman, Ramily Shaymardanov, Dan Sun, and Chenning Yu. We were team 9.

We started on October 5th and finished October 30th. Among the four of us, we spent approximately 100 hours on SLogo over the course of the two weeks.

Dan worked on the front-end and GUI aspects of the project, Chenning worked on the controller (which facilitated communication between the sections), Ramil worked on the interpreter, which did the actual translation part of the project, and Natalie worked on the executor, which actually carried out the commands. The online Java API was consulted, and StackOverflow on occasion, which is cited in the relevant classes.

The file used to start the project is Main, in the default package. The files used to test the project can be found under the tests folder. There are tester files for controller.channel, interpreter, interpreter.core.elements, interpreter.core.include, interpreter.util, and turtle. The program should be able to handle wrong commands and invalid number inputs (such as selecting an index for a gui input that doesn't exist) without crashing. The project uses multiple resource files which can be found under src/resources, as well as under the outer resources folder.

If the user enters tell[100], our project will create all the turtles up to that value, and not just one additional turtle. For commands that would have different return values if entered multiple times, such as "fd set :x + :x 10", the command is only executed once and all active turtles are moved the same distance.

On the whole, this project was enjoyable despite being challenging and we felt that we learned a lot about class interaction.