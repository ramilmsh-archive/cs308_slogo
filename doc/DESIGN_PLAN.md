# Introduction
The main problem our team is trying to solve by writing this program is to create an animation that is controlled through user input, based on a simple programming language. Users will input commands, which will cause the on-screen turtle to move, drawing an image as it goes. The user interface will be split into the animation screen, where users can view the image, and a console where users can enter commands. We plan to have our code organized in a hub/spoke structure, where a central communications class instantiates and organizes information flow between the user interface, an interpreter, and an executor. This encourages encapsulation by ensuring that the latter three classes work entirely independently of one another, with no idea of the others’ existence. 

Because the GUI, interpreter, and executor don’t communicate directly with each other, they have complete freedom to implement their sections of the project however they choose. This makes for easy flexibility, as they can be changed without affecting any other classes. These classes are all also open for extension, but closed to each other.

# Design Overview

## Back-end, external
The program is set up in a hub/spoke design, where the front-end and back-end are separated by a central Controller class that facilitates communication between the two halves of the project. The Controller passes the Interpreter class a String of user input, which the Interpreter packages and returns as an array of Command objects. The Controller then passes that array to the Executor class, which carries out the command on the Turtle object. If the Turtle coordinates change, it fires an event, which the Controller is observing. The Controller then carries that changed data back to the GUI.This means that not only is the front-end kept separate from the back-end, but the Interpreter and Executor similarly do not communicate directly. This means that each section of the project operates “independently”, unaware of the existence of classes other than themselves and the Controller. Errors (for example, invalid user input to the Interpreter, invalid numeric input to the Turtle, etc) are thrown to the Controller, which handles them in conjunction with the relevant class. For example, errors caused by invalid user input, like if the Interpreter got the command “FR 50” instead of “FD”, would necessitate GUI action. To make this happen, the Controller would pass the GUI the relevant information.

## Back-end, internal
The program shall attempt to interpret string commands, passed to it by the GUI and convert them into the control sequences. The GUI will receive input in the form of line input or a file of valid slogo code and pass it via controller to the interpreter. Interpreter shall maintain all of the scope data, such as the variable tree (each block of code will represent a scope, which will have access to its own items, as well as the parent’s), as well as methods. When a processing request is submitted, the interpreter shall create a name (item) in the scope, to be executed, represented by an array of commands. Upon execution, it will recursively resolve all the expressions into values, such as arithmetic expressions, “words, function calls, to be able to send commands with actual numerical input, instead of symbolic. By design, the rest of the program will be completely unaware of variables and functions defined, nor meaning of the string commands.

## Front-end, external
The primary role of the front end is to allow the user to interact with the program. The user can interact with the program by either giving the program some kind of input, (e.g. pressing a button, type a command, etc) or receive an output that is given by the program(e.g. seeing the turtle move, seeing an error message, etc.). As a result, the user interface should be able to 1) receive some input from the user; 2) do some superficial processing, then communicate the input with the back-end; 3) let the back-end process the user input; 4) deliver an output to the user.
For the Slogo IDE, the front-end should be able to handle the following tasks when asked by other components of the program::
change the position of the turtle(s)
 
1. Draw lines on the canvas
 
2. Display error message to the user
 
3. Display numerical result of user input
 
4. Display/update user-defined variables or commands

## Front-end, internal
The front-end is divided into the following sections:
A window that holds everything the user sees, except popup windows
A menu bar that can be seen all the time
Tabs bar that allow the user to select tabs
A page that displays everything seen for a particular tab

This is the overall view of the front end. Our basic implementation will mostly be done on the work page. This page includes the following:

1. A graphics view that displays presents a visual representation of the current state of the program
2. A state display that shows the current state of the program by values. This includes a variable display that shows all variables created by the user, and a user-defined command (abbreviated as u.d.c from this point on) display that shows all u.d.c.
3. A text output that shows the text result of user’s commands. This unit keeps a record of commands the user entered before, and displays any value or error message caused by the user’s last command
4. A “command receiver” that primarily takes command from the user. 
5. A tool bar that allows the user to perform functions specific to the current page
All these parts are connected by a front-end controller.

 External Communication
![External Communication](https://raw.githubusercontent.com/rainorangelemon/picture_for_blog/master/for%20CS308/IMG_7828.JPG)

 Front-End Internal Communication
![Front-End Internal Communication](https://raw.githubusercontent.com/rainorangelemon/picture_for_blog/master/for%20CS308/IMG_7827.JPG)


# User Interface
Refer to the picture below for how the user interface is going to look like. The function designated for each component is described in the design overview section. The user interacts with the program by clicking on menus, the toolbar, or typing into the command receiver. The user observe from the state display, the graphics display, and the text output.

 A Peek at the UI
![A Peek at the UI](https://raw.githubusercontent.com/rainorangelemon/picture_for_blog/master/for%20CS308/IMG_7826.JPG)

# API Details

## Back-end, external:
The back-end consists of two main sections: the executor and the interpreter. The interpreter will receive user-entered commands from the Controller class, translate and package them into a Command object, and return them to the Controller. It has two public methods that are part of the External back-end API:

```java
public interface Interpreter{
public Command[] process (String line);
public void reset();
}
```

The process method takes input from the user by way of the Controller class and returns an array of Command objects, and the reset method clears all data.

The Executor receives one-by-one from the Controller the Command objects that the interpreter returned (never communicating with the Interpreter directly). It also has two public methods that are part of the External back-end API:

```java
public interface Executor{
	public double execute (Command c);
	public void reset();
}
```

The execute method takes the Command object, carries out the command, and returns the resultant double. The reset method, like with the Interpreter, clears all data. The Executor has control of the Turtle class, while implements ObserverDispatcher so that it can add Observers when invoked by the Controller class. These Observers allow the Controller to pass back changed Turtle data to the GUI.

```java
interface ObserverDispatcher
	public void addObserver(Observer observer);
public void removeObserver(Observer observer);
public void notifyObservers(String changedValue);
public Point getCoords();
public double getAngle();
public boolean penVis();
public boolean turtleVis();
```

The former two methods allow for classes to add and remove Observers from the Turtle class. The third method allows the Turtle class to inform Observers when content has changed. The latter four allow for observing classes to access Turtle properties when the Turtle calls on the Observers to inform them of a change. The Turtle also implements the Turtle interface, below:

```java
public interface Turtle
public double move(double[] dist);
public double move(Point p);
public double turn(double degrees);
public double absTurn(double degrees);
public double absTurn(Point p);
public void turtleVis(int vis);
public void penVis(int vis);
public void badData() throws IOException;
```

These first five methods control the movement/turning of the Turtle, whereas the last two control whether the turtle is visible, and whether the pen is up or down. 

This API allows for the user to control the Turtle through various commands, and for Observing classes to recognize when the instance variables of the Turtle have changed. The APIs above can be found in the group repository on Gitlab. Additionally, the Turtle throws an IOException if the Commands they receive from the user are invalid.


## Back-end, internal
In order to keep all of the necessary data in the scope, each of the elements will extend abstract class element, which will have method parse(String line), which will resolve a command string into the subsequent Element: Variable, Expression, Function, Command. This will allow to create a convenient recursive parsing structure. Commands and Functions will implement Executable interface, while Expression and Variable will be Resolvable. Finally, Scope will contain the scope tree with all the defined variables and functions

## Front-end, external

The front-end APIs will be implemented in a class called ViewInterface. Specifically, the following methods should be implemented to support project requirements.

```java
public interface ViewInterface{
public boolean updateTurtleGraphic(Turtle turtle_before, Turtle turtle_after) 
	public boolean displayError(String error_msg)
	public boolean displayResult(String result)
	public boolean updateVariable(VariableHolder variables)
	public boolean updateUDC(UserDefinedCommandHolder udcs)
}
```

## Front-end, internal
The front end internal API will be implemented through a class called ViewController. This is the channel that parts in the front-end communicates with each other. This API will be developed as implementation details are settled.

The ViewController is responsible for communication between parts of the view. It should at least implemented the following methods. 

```java
interface ViewControllerInterface{
boolean createNewPage() 
	boolean createPopupWindow()
boolean destoryPage() 
}
```

Each part of the front view described can also implement their specific interfaces. For example, the graphics view might be an interface that has method eraseTurtle() and drawTurtle(). More generally, each part performs the task specified in the front end external interface should have the corresponding function.


# API Example Code

## Use Case 0: User types “fd 50” in the input receiver

The string gets transmitted to the controller, and passed down to the interpreter. The interpreter produces the corresponding command object which is received by the controller, while adding the command to the command history. The command is passed to the execute for the executor to update the position of the turtle. After the turtle is updated, the controller passes it to the GUI by calling the updateTurtle() method for the GUI to update the graphical view.

## Use Case 1: User types HOME into the console

GUI parses String and passes it through the Controller to the Interpreter, which checks it for validity and packages it into a Command object. The Interpreter passes that object through the Controller to the Executor, which pulls the data out of it by using .getCommand() to retrieve the value HOME and .getValue() to retrieve the value 0. The Executor then calls the Turtle’s .move(Point p) method, and passes it the Point (0,0). The Turtle converts that theoretical Point to the actual Point based on the size of the screen, which it holds as a constant, and resets its coordinates to that Point. It then notifies all Observers of the change. The Controller uses the Observer to retrieve the new coordinate point, and informs the GUI of where the turtle is now. The GUI then redraws the Turtle at that Point.

## Use Case 2: User selects “Chinese” as input language

The GUI passes this update to the Interpreter, which includes it in all Command objects it packages. When the Executor opens the Command object to call on the Turtle, it uses the .getLanguage method of the Command class to realize that .getCommand will return a Chinese word, and uses the .txt files in the resources package to convert the text into English, to know which Turtle methods to call.

## Use Case 3: User selects “Change Background Color” in the tool bar

The ViewController gets called and opens a new popup window for the user to select a color. When the user selects the color, the ViewController stores the result and calls GraphicsView to change its color.

## Use Case 4: User clicks “command reference” in the menu

The ViewController generates the help page and the corresponding tab. The page displays html formatted command reference from the project specification.

# Design Considerations
One design issue that we discussed was how to structure the overall program. Our original idea was to model the read-eval-print loop by creating three sections that essentially operated in a loop, with each class containing an instance of the subsequent. 

This design was tempting because it seems an intuitive illustration of a concept that we felt was a central part of this project. Also, this would simplify transfer of information, and would make for partial encapsulation, since it would mean each class would only deal with one another. However, there were also a number of drawbacks to this setup. One of the major detractions was that if, for example, a read class had information that needed to be passed to the print class, it would need to travel through all three sections, which violates encapsulation principles. Additionally, we worried it might limit extension possibilities, since all three sections would be tightly locked into their relationships with each other. A final, alarming problem was that it would be easy enough to a read class to instantiate the eval class, and the eval class to instantiate the print class-- but the final leg becomes more complicated. There didn’t seem to be a good way to ensure that the print class stored the same instantiation of the read class that started this cycle.   

The immediate answer was that the read class could pass itself down the line until the print class, but that would mean that every class along the way would have the opportunity to see and/or manipulate the read class, which obviously we wanted to avoid. Instead, we thought we could have a third-party class manage the passing of the read class to the print class. But as soon as we’d introduced another section into the structure, an easier solution became apparent. Instead, we proposed the hub/spoke design we originally decided on, where a central class creates all of the others. The drawback of this is that it means that the class in the middle (the Controller) has total access to all of the other classes. It also weakens the encapsulation of the project, as it means that the Controller sees all of the other classes. 

However, it removes the problem where the other classes all contain an instance of themselves, and it eliminates all of the issues with “passing data down the line”. It also means that the spoke classes (GUI, Interpreter and Executor) are totally encapsulated, since they do not see any of the other spoke classes. We decided in the end that the latter proposal was a more logical structure to enact, and would make the project both more flexible and more encapsulated overall.

# Team Responsibilities
Because the sections of the project are kept so separate from each other (with the exception of the central Controller class), we plan to work on our classes alone, based on the APIs we have established above.  

Natalie will take primary responsibility for the Executor and Turtle classes. Ramil will take responsibility for the Interpreter, which includes core classes, util classes, and math classes. Dan will take responsibility for the GUI and other user interface classes. Chenning will take responsibility for the Controller class and the Main class that will launch the program.

If need be, we will continue to meet throughout the first week to discuss any issues which may affect multiple people’s sections. We will also aim to work in our own branches and to leave our merge requests to be fulfilled by others so that we can cut down on the chances of merging code to master that negatively affects a different team member’s code. Once the basic implementation has been completed, we will meet to re-delegate tasks based on the work left to be completed, and which sections of the project best lend themselves to extension.
