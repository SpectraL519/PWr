// Change the program so that the rows, columns, latency and probability are taken from args
// Generate and operate on all graphic elements from ThreadSimulation class and operate on Threars within the SimulationThread class, eg: add getRed, getGreen, getBlue methods and change ThreadColors' element's colors from the ThreadSimulation class 

/** @author Jakub Musial 268442  */
/** Thread Simulation @version 1.0 */
/** Java @version jdk1.8.0_331 */

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;





class SimulationThread extends Thread {
    private int rowIndex; /** Thread's row index on the Simulation Board */
    private int columnIndex; /** Thread's column index on the Simulation Board */
    private int rowSize; /** Simulation Board's vertival size */
    private int columnSize; /** Simulation Board's horizontal size */

    private int latency; /** Thread's operating latency */
    private int probability; /** Probability of a color change event */

    public boolean Active; /** Determines if the thread is currently active */

    private int red; /** Thread's color value: red */
    private int green; /** Thread's color value: green */
    private int blue; /** Thread's color value: blue */

    /** SimulationThread class constructor */
    public SimulationThread (int rowIndex, int columnIndex, int rowSize, int columnSize, int latency, int probability) {
        super();

        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
               
        this.latency = latency;
        this.probability = probability;

        this.Active = false;
    }

    /** Thread's random color generator */
    private void generateRandomColors () {
        this.red = ThreadSimulation.RandomGenerator.nextInt() % 256;
        this.green = ThreadSimulation.RandomGenerator.nextInt() % 256;
        this.blue = ThreadSimulation.RandomGenerator.nextInt() % 256;
    }

    /** Thread's color generator (based on surrounding threads) */
    private void generateNewColors() {
        double tmpRed = 0.0;
        double tmpGreen = 0.0;
        double tmpBlue = 0.0;

        int ActiveSurroundingThreads = 0;

        /** Generating color (red, green, blue) values based on surrounding threads */
        if (ThreadSimulation.Threads[(this.rowIndex - 1) % this.rowSize][this.columnIndex] != null) {
            ActiveSurroundingThreads++;

            tmpRed += ThreadSimulation.Threads[(this.rowIndex - 1) % this.rowSize][this.columnIndex].red;
            tmpGreen += ThreadSimulation.Threads[(this.rowIndex - 1) % this.rowSize][this.columnIndex].green;
            tmpBlue += ThreadSimulation.Threads[(this.rowIndex - 1) % this.rowSize][this.columnIndex].green;
        }
        if (ThreadSimulation.Threads[(this.rowIndex + 1) % this.rowSize][this.columnIndex] != null) {
            ActiveSurroundingThreads++;

            tmpRed += ThreadSimulation.Threads[(this.rowIndex + 1) % this.rowSize][this.columnIndex].red;
            tmpGreen += ThreadSimulation.Threads[(this.rowIndex + 1) % this.rowSize][this.columnIndex].green;
            tmpBlue += ThreadSimulation.Threads[(this.rowIndex + 1) % this.rowSize][this.columnIndex].green;
        }
        if (ThreadSimulation.Threads[this.rowIndex][(this.columnIndex - 1) % columnSize] != null) {
            ActiveSurroundingThreads++;

            tmpRed += ThreadSimulation.Threads[this.rowIndex][(this.columnIndex - 1) % columnSize].red;
            tmpGreen += ThreadSimulation.Threads[this.rowIndex][(this.columnIndex - 1) % columnSize].green;
            tmpBlue += ThreadSimulation.Threads[this.rowIndex][(this.columnIndex - 1) % columnSize].green;
        }
        if (ThreadSimulation.Threads[this.rowIndex][(this.columnIndex + 1) % columnSize] != null) {
            ActiveSurroundingThreads++;

            tmpRed += ThreadSimulation.Threads[this.rowIndex][(this.columnIndex + 1) % columnSize].red;
            tmpGreen += ThreadSimulation.Threads[this.rowIndex][(this.columnIndex + 1) % columnSize].green;
            tmpBlue += ThreadSimulation.Threads[this.rowIndex][(this.columnIndex + 1) % columnSize].green;
        }

        if (ActiveSurroundingThreads > 0) {
            this.red = (int) tmpRed / ActiveSurroundingThreads;
            this.green = (int) tmpGreen / ActiveSurroundingThreads;
            this.blue = (int) tmpBlue / ActiveSurroundingThreads;
        }
        else {
            this.generateRandomColors();
        }
    }

    /** Thread starting method */
    @Override
    public void run() {
        if (!this.Active) {
            /** Creating a colored Pane for thread representation */
            Pane ColorPane = new Pane();
            ColorPane.setOnMouseClicked(new ThreadHandler());

            this.generateRandomColors(); /** Creating a color for the thread */
        }
        else {
            /** Activating the thread */
            this.Active = true;
        }

        /** Thread operating loop */
        while(this.Active) {
            /** Generating new thread colors based on surrounding threads */
            if (ThreadSimulation.RandomGenerator.nextInt() % 100 < this.probability) { 
                this.generateNewColors();
                ThreadSimulation.ThreadColors[this.rowIndex][this.columnIndex].setBackground(new Background(new BackgroundFill(Color.rgb(this.red, this.green, this.blue), null, null)));
            }

            /** Thread random latency implementation */
            try {
                Thread.sleep((ThreadSimulation.RandomGenerator.nextInt() % this.latency) + (this.latency / 2));
            }
            catch (InterruptedException exception) {}
        }
    }

    public void stopThread () {
        this.Active = false;
    }

    public void resumeThread () {
        this.Active = true;
        this.run();
    }



    class ThreadHandler implements EventHandler <MouseEvent> {
        SimulationThread thread;

        @Override
        public void handle (MouseEvent event) {
            if (event.getButton() == MouseButton.SECONDARY) {
                /** Context menu for thread options */
                final ContextMenu ThreadMenu = new ContextMenu();

                Label StopThreadLabel = new Label("Stop thread");
                final MenuItem StopThreadButtton = new MenuItem(null, StopThreadLabel);
                StopThreadButtton.setOnAction(new EventHandler <ActionEvent> () {
                    @Override
                    public void handle (ActionEvent event) {
                        if (!thread.Active) {
                            thread.stopThread();
                            StopThreadLabel.setText("Resume thread");
                        }
                        else {
                            thread.resumeThread();
                            StopThreadLabel.setText("Stop thread");
                        }
                    }
                });

                Label KillThreadLabel = new Label("Kill thread");
                final MenuItem KillThreadButton = new MenuItem(null, KillThreadLabel);
                KillThreadButton.setOnAction(new EventHandler <ActionEvent> () {
                    @Override
                    public void handle (ActionEvent event) {
                        if (!thread.Active) {
                            thread = new SimulationThread(thread.rowIndex, thread.columnIndex, thread.rowSize, thread.columnSize, thread.latency, thread.probability);

                            KillThreadButton.setText("Recreate thread");
                        }
                        else {
                            thread.start();
                            
                            KillThreadLabel.setText("Kill thread");
                        }
                    }
                });

                ThreadMenu.getItems().addAll(StopThreadButtton, KillThreadButton);
                ThreadMenu.show(ThreadSimulation.ThreadColors[thread.rowIndex][thread.columnIndex], event.getScreenX(), event.getScreenY());
            }
        }
    }
}





public class ThreadSimulation extends Application {
    private boolean ActiveSimulation = false;
    private boolean SimulationStopped = false;

    private static String[] parameters; /** Array for representing main method's String[] args */

    public static Pane[][] ThreadColors; /** Array for displaying colors of the threads */

    private int columns; /** Simulation board's horizontal size */
    private int rows; /** Simulation board's vertical size */
    private int latency; /** Simulation operating latency */
    private int probability; /** Probability of a color change event */

    public static SimulationThread[][] Threads; /** Simulation's thread board */

    public static Random RandomGenerator = new Random(); /** Simulation's random number generator */

    @Override
    public void start (Stage PrimaryStage) {

        BorderPane MainPane = new BorderPane();
        MainPane.setPrefSize(1024, 628);
        
        /** Butons Operating the simulation */
        Button StartSimulationButton = new Button("Start simulation");
        Button StopSimulationButton = new Button("Stop simulation");
        StopSimulationButton.setVisible(false);
        Button EndSimulationButton = new Button("End simulation");
        EndSimulationButton.setVisible(false);

        // StartSimulationButton action implementation
        StartSimulationButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {
                if (!ActiveSimulation) {
                    try {
                        // Getting the m, n, k, p values form their TextFields
                        columns = Integer.parseInt(parameters[0]);
                        rows = Integer.parseInt(parameters[1]);
                        latency = Integer.parseInt(parameters[2]);
                        probability = Integer.parseInt(parameters[3]);

                        ActiveSimulation = true;

                        StartSimulationButton.setVisible(false);
                        StopSimulationButton.setVisible(true);
                        EndSimulationButton.setVisible(true);

                        // Simulation board
                        GridPane SimulationBoard = new GridPane();

                        // Simulation threads
                        Threads = new SimulationThread[rows][columns];
                        for (int i = 0; i < rows; i++) {
                            for (int j = 0; j < columns; j++) {
                                ThreadColors[i][j] = new Pane();
                                ThreadColors[i][j].setPrefSize(1024 / rows, 600 / columns);

                                Threads[i][j] = new SimulationThread(rows, columns, i, j, latency, probability);
                                Threads[i][j].start();
                            }
                        }

                        // start the simulation

                        MainPane.setBackground(null);
                        MainPane.setCenter(SimulationBoard);
                    }
                    catch (Exception exception) {
                        Label ExceptionLabel = new Label("Something went wrong\nPlease try again");
                        ExceptionLabel.setAlignment(Pos.CENTER);
                        ExceptionLabel.setFont(new Font("Courier New", 36));
                        
                        MainPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                        MainPane.setCenter(ExceptionLabel);
                    }
                }
                else if (ActiveSimulation && SimulationStopped) {
                    SimulationStopped = false;

                    StartSimulationButton.setVisible(false);
                    StopSimulationButton.setVisible(true);
                }
            }
        });

        // StopSimulationButton action implementation
        StopSimulationButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {
                SimulationStopped = true;

                StartSimulationButton.setText("Resume simulation");
                StartSimulationButton.setVisible(true);
                StopSimulationButton.setVisible(false);
            }
        });

        // EndSimulationButton action implementation
        EndSimulationButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {
                Threads = null;

                ActiveSimulation = false;

                StartSimulationButton.setText("Start simulation");
                StartSimulationButton.setVisible(true);
                StopSimulationButton.setVisible(false);
                EndSimulationButton.setVisible(false);

                MainPane.setBackground(null);
                MainPane.setCenter(null);
            }
        });

        HBox SimmulationButtonPanel = new HBox();
        SimmulationButtonPanel.setPadding(new Insets(10, 12, 10, 12));
        SimmulationButtonPanel.setSpacing(10);
        SimmulationButtonPanel.setAlignment(Pos.CENTER_LEFT);
        SimmulationButtonPanel.getChildren().addAll(StartSimulationButton, StopSimulationButton, EndSimulationButton);


        MainPane.setTop(SimmulationButtonPanel);

        PrimaryStage.setTitle("Thread simulation");
        PrimaryStage.setScene(new Scene(MainPane));
        PrimaryStage.show();
    }

    public static void main (String[] args) {
        parameters = args;
        Application.launch(args);
    }
}
