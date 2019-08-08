#Simulation

##Internal

```java
public abstract class CellGroup {
  	public static final List<KeyCode> ALLOWED_KEYS = Arrays.asList( new KeyCode[] KeyCode.E, KeyCode.R, KeyCode.B, KeyCode.G});
	public CellGroup(List<Double> configList) throws IOException
	public int[] getCellStates() 
	public List<Polygon> drawShapes()
	public void update() throws Exception 
	public List<String> getSimInfoCopy()
	public void handleKeyPress(KeyCode code)
	public void handleKeyRelease(KeyCode code)
}
 
package CellGroup;
public class HexCellGroup extends CellGroup{ 
  	public HexCellGroup(List<Double> configList) throws IOException 
}
 
package CellGroup;
public class SquareCellGroup extends CellGroup{ 
  	public SquareCellGroup(List<Double> configList) throws IOException 
}
 
package CellGroup;
public class TriCellGroup extends CellGroup{ 
  	public TriCellGroup(List<Double> configList) throws IOException 
}
```

##External

```java
public abstract class Agent { 
  	public Agent(int myState)
	public int getState()
	public boolean equals(Object o)
}
 
public abstract class Cell { 
      public Cell(int intialState) 
    public void pushStateToNeighbors()
    public void listenForState(int state)
    public abstract boolean update() throws Exception;
    public abstract void reset();
    public int getState()
    public void setState(int s)
    public boolean isEmpty()
    public void setNeighbors(Collection<Cell> myNeighbors)
    public void pushUpdateToNeighbors(int oldState, int newState)
    public void listenForUpdate(int oldState, int newState)
    public void setHasNotMoved(boolean newState)
}
 
public class FireCell extends Cell { 
      public FireCell(int initialState, Collection <Double> simSettings)
    public FireCell(int initialState, double prob)
    public boolean update()
    public void reset()
}
 
public class LifeCell extends Cell { 
      public LifeCell(int initialState, Collection<Double> simSettings)
    public LifeCell(int initialState)
    public boolean checkUpdate()
    public boolean update()
    public boolean isAlive()
    public void reset()
}
 
public abstract class Patch extends Cell{ 
  	public Patch(int state)
	public void setNeighbors(List<Patch> list)
	public void setAgents(List<Agent> list)
}
 
public class RPSCell extends Cell{ 
  	public RPSCell(int intialState, Collection<Double> simSettings) 
	public boolean update() throws Exception 
	public void reset() 
}
 
public class SegCell extends Cell{ 
      public SegCell(int initialState, Collection<Double> simSettings)
    public SegCell(int initialState)
    public boolean checkUpdate()
    public boolean update() throws Exception
    public boolean isUnhappy()
    public boolean recursiveNeighborSearch(int newState, LinkedList<SegCell> oldStates)
    public void reset()
    public void listenForState(int newState)
    public void listenForUpdate(int oldState, int newState)
 }
    
public class WaTorCell extends Cell { 
      public WaTorCell(int initialState, Collection<Double> simSettings)
    public WaTorCell(int initialState)
    public boolean checkUpdate()
    public boolean update()
    public void reset()
    public void setState(int myState)
    public int getNext()
    public int getRoundsLived()
    public int getEnergy()
}
```

#Configuration

##Internal

```java
public class XMLException extends RuntimeException { 
      public XMLException (String message, Object ... values) 
    public XMLException (Throwable cause, String message, Object ... values) 
    public XMLException (Throwable cause) 
}
```

#External

```java
public class Util { 
    public List<String> parseText(File dataFile) throws IOException
    public ArrayList<String> getCellStateNames() throws IOException 
    public void saveFile(List<String> simData, File file) throws IOException
	public List<String> readTextFile(File file) throws FileNotFoundException
}

```

#Visualization

##Internal

```java
public class Game extends Application implements Runnable{ 
  	public void start(Stage s) throws Exception 
	public Game(Stage s)
	public void run() 
}

public class SplashScreen extends Application{ 
  	public void start(Stage s) throws Exception 
}
```

#External

```java
public class AreaChartSample { 
  	public AreaChartSample(List<String> cellStateNames,String title) 
	public AreaChart<Number,Number> getAreaChart() 
	public void addDataToSeries(Queue<Number> dataQ)
}
 
public class SpeedSlider { 
  	public SpeedSlider(String label) 
	public double getSliderSpeed() 
	public HBox getSlider()
}
 
public class UI { 
  	public UI() throws IOException
	public boolean shouldSave()
	public boolean restart()
	public boolean step()
	public double getSpeed()
	public File getSaveLocation()
	public List<Button> getButtons()
	public HBox getSlider()
	public static void exceptionThrower(Exception e)
	public File getFileUI ()
}
```