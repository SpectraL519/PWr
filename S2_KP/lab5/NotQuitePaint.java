/** @author Jakub Musial 268442  */
/** Program @version 1.0 */
/** Java @version jdk1.8.0_331 */

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;

import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale; 
import javafx.scene.transform.Translate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;





/** Class implementing circle manipulations 
 * @see MyCircle
 * @see FigureActionPerformed
 * @see ChangePosX
 * @see ChangePosY
 * @see Changeradius
 * @see CircleMouseEvents
*/
class MyCircle extends Circle {
    private Pane DrawingSpace;

    /** MyCircle class constructor */
    public MyCircle (double center_x, double center_y, double radius, Pane DrawingSpace) {
        super(center_x,center_y,radius);

        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1.4);
        this.setFill(Color.WHITE);
        this.setOnMouseClicked(new CircleMouseEvents());
        this.setOnMouseDragged(new CircleMouseEvents());
        this.setOnScroll(new CircleScrollEvents());

        this.DrawingSpace = DrawingSpace;
    }

    /** Activates figure */
    private void ActivateFigure () {
        DrawingSpace.getChildren().remove(this);
        DrawingSpace.getChildren().add(this);
    }
    
    /** Detects if the mouse hoverd over the figure */
    private boolean FigureActionPerformed (double pos_x, double pos_y) {
        return getBoundsInLocal().contains(pos_x, pos_y);
    }

    /** Changes the X coordinate of Circle center point by delta_x */
    private void ChangePosX (double delta_x) {
        this.setCenterX(this.getCenterX() + delta_x);
    }

    /** Changes the Y coordinate of Circle center point by delta_y */
    private void ChangePosY (double delta_y) {
        this.setCenterY(this.getCenterY() + delta_y);
    }

    /** Changes Circle radoius by delta_radius */
    private void ChangeRadius (double delta_radius) {
        this.setRadius(this.getRadius() + delta_radius);
    }

    /** Class implementing reaction to mouse events on the circle 
     * @see handle
     * @see Movement
    */
    class CircleMouseEvents implements EventHandler <MouseEvent> {
        MyCircle circle;

        private double mouse_x;
        private double mouse_y;

        /** Determines circle movement */
        private void Movement (MouseEvent event) {
            double delta_x = event.getX() - mouse_x;
            double delta_y = event.getY() - mouse_y;

            if (circle.FigureActionPerformed(mouse_x, mouse_y)) {
                circle.ChangePosX(delta_x);
                circle.ChangePosY(delta_y);
            }

            mouse_x += delta_x;
            mouse_y += delta_y;
        }

        @Override
        public void handle (MouseEvent event) {
            circle = (MyCircle) event.getSource();

            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                ActivateFigure();

                if (event.getButton() == MouseButton.PRIMARY) {
                        mouse_x = event.getX();
                        mouse_y = event.getY();
                }
                /** Circle advanced options implementation: color picking, rotation, other */
                else if (event.getButton() == MouseButton.SECONDARY) {
                    final ContextMenu CircleMenu = new ContextMenu();

                    /** Circle color selection implementation */
                    final ColorPicker colorPicker = new ColorPicker((Color) circle.getFill());
                    final MenuItem ColorPickerButton = new MenuItem(null, colorPicker);
                    ColorPickerButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            circle.setFill(colorPicker.getValue());
                        }
                    });

                    /** Fill removal implementation */
                    final MenuItem NullFillButton = new MenuItem(null, new Label("Remove fill"));
                    NullFillButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            circle.setFill(null);
                        }
                    });

                    /** Circle deletion implementation */
                    final MenuItem DeleteButtton = new MenuItem(null, new Label("Delete figure"));
                    DeleteButtton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            DrawingSpace.getChildren().remove(circle);
                        }
                    });

                    /** Menu closing implementation */
                    final MenuItem CloseButton = new MenuItem(null, new Label("Close menu"));
                    CloseButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            CircleMenu.hide();
                        }
                    });

                    /** Circle context menu implementation */
                    CircleMenu.getItems().addAll(ColorPickerButton, NullFillButton, DeleteButtton, CloseButton);
                    CircleMenu.show(DrawingSpace, event.getScreenX(), event.getScreenY());
                }
            }
            /** Circle movement implementation */
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && event.getButton() == MouseButton.PRIMARY) {
                /** @see CircleMouseEvents#Movement */
                Movement(event);
            }
        }
    }

    /** Class implementing reaction to scroll events on the circle 
     * @see handle
     * @see Scaling
    */
    class CircleScrollEvents implements EventHandler <ScrollEvent> {
        MyCircle circle;

        /** Determines tircle scaling */
        private void Scaling (ScrollEvent event) {
            double scroll_x = event.getX();
            double scroll_y = event.getY();

            if (circle.FigureActionPerformed(scroll_x, scroll_y)) {
                circle.ChangeRadius(event.getDeltaY() * 0.05);
            }
        }

        /** Circle scaling implementation */
        @Override
        public void handle (ScrollEvent event) {
            circle = (MyCircle) event.getSource();

            if (event.getEventType() == ScrollEvent.SCROLL) {
                Scaling(event);
            }
        }
    }
}




/** Class implementing triangle manipulations 
 * @see MyTriangle
 * @see FigureActionPerformed
 * @see TriangleMouseEvents
 * @see TriangleScrollEvents
*/
class MyTriangle extends Polygon {
    private BorderPane MainPane;
    private Pane DrawingSpace;

    public Point2D centre;
    public Point2D p1final;
    public Point2D p2final;
    public Point2D p3final;

    /** MyTriangle class constructor */
    public MyTriangle (Point2D p1, Point2D p2, Point2D p3, BorderPane MainPane, Pane DrawingSpace) {
        super();

        centre = p1.midpoint(p2).midpoint(p3);
        this.p1final = p1.subtract(centre);
        this.p2final = p2.subtract(centre);
        this.p3final = p3.subtract(centre);

        this.getPoints().addAll(new Double[] {
            this.p1final.getX(), this.p1final.getY(),
            this.p2final.getX(), this.p2final.getY(),
            this.p3final.getX(), this.p3final.getY()
        });

        this.setLayoutX(centre.getX());
        this.setLayoutY(centre.getY());

        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1.4);
        this.setFill(Color.WHITE);
        this.setOnMouseClicked(new TriangleMouseEvents());
        this.setOnMouseDragged(new TriangleMouseEvents());
        this.setOnScroll(new TriangleScrollEvents());

        this.MainPane = MainPane;
        this.DrawingSpace = DrawingSpace;
    }

    /** Activates figure */
    private void ActivateFigure () {
        DrawingSpace.getChildren().remove(this);
        DrawingSpace.getChildren().add(this);
    }
    
    /** Detects if the mouse hoverd over the figure */
    private boolean FigureActionPerformed (double pos_x, double pos_y) {
        return getBoundsInLocal().contains(pos_x, pos_y);
    }

    /** Class implementing reaction to mouse events on the triangle 
     * @see handle
     * @see Movement
    */
    class TriangleMouseEvents implements EventHandler <MouseEvent> {
        MyTriangle triangle;
        
        private double mouse_x;
        private double mouse_y;

        /** Determines triangle movement */
        private void Movement (MouseEvent event) {
            double delta_x = event.getX() - mouse_x;
            double delta_y = event.getY() - mouse_y;

            if (triangle.FigureActionPerformed(mouse_x, mouse_y)) {
                Translate shift = new Translate();
                shift.setX(delta_x);
                shift.setY(delta_y);

                triangle.getTransforms().add(shift);
                triangle.centre = p1final.midpoint(p2final).midpoint(p3final); // repositioning triangle's centre point
            }
        }

        @Override
        public void handle (MouseEvent event) {
            triangle = (MyTriangle) event.getSource();

            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                ActivateFigure();

                if (event.getButton() == MouseButton.PRIMARY) {
                        mouse_x = event.getX();
                        mouse_y = event.getY();
                }
                /** Triangle advanced options implementation: color picking, rotation, other */
                else if (event.getButton() == MouseButton.SECONDARY) {
                    final ContextMenu TriangleMenu = new ContextMenu();

                    /** Triangle color selection implementation */
                    final ColorPicker colorPicker = new ColorPicker((Color) triangle.getFill());
                    final MenuItem ColorPickerButton = new MenuItem(null, colorPicker);
                    ColorPickerButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            triangle.setFill(colorPicker.getValue());
                        }
                    });

                    /** Fill removal implementation */
                    final MenuItem NullFillButton = new MenuItem(null, new Label("Remove fill"));
                    NullFillButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            triangle.setFill(null);
                        }
                    });

                    /** Triangle rotation implementation */
                    final MenuItem RotateButton = new MenuItem(null, new Label("Rotate Figure"));
                    RotateButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            // Creating a rotation slider
                            Slider slider = new Slider(0, 360, 0);
                            slider.setShowTickLabels(true);
                            slider.setShowTickMarks(true);
                            slider.setMajorTickUnit(90);
                            slider.setBlockIncrement(10);
                            slider.setOrientation(Orientation.HORIZONTAL);
                            slider.setLayoutX(2);
                            slider.setLayoutY(195);

                            // Creating rotation transformation
                            Rotate rotate = new Rotate();
                            // Centering pivot point
                            rotate.setPivotX(triangle.centre.getX());
                            rotate.setPivotY(triangle.centre.getY());
                            // Adding rotation to the figure
                            triangle.getTransforms().add(rotate);

                            // Connecting slider to the rotation
                            slider.valueProperty().addListener(new ChangeListener <Number>() { // allows for observing slider changes
                                public void changed(ObservableValue <?extends Number> observable, Number oldValue, Number newValue) {
                                    rotate.setAngle((double) newValue);
                                }
                            });
   
                            // Slider closing button
                            Button EndRotateButton = new Button("End rotation");
                            EndRotateButton.setOnAction(new EventHandler <ActionEvent> () {
                                @Override
                                public void handle (ActionEvent e) {
                                    MainPane.setBottom(null);
                                }
                            });

                            // Panel for figure rotation
                            HBox RotatePanel = new HBox();
                            RotatePanel.setPadding(new Insets(15, 12, 15, 12));
                            RotatePanel.setSpacing(10);
                            RotatePanel.setAlignment(Pos.CENTER_RIGHT);
                            RotatePanel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,null,null)));
                            RotatePanel.getChildren().addAll(slider, EndRotateButton);

                            MainPane.setBottom(RotatePanel);
                        }
                    });

                    /** Triangle deletion implementation */
                    final MenuItem DeleteButtton = new MenuItem(null, new Label("Delete figure"));
                    DeleteButtton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            DrawingSpace.getChildren().remove(triangle);
                            MainPane.setBottom(null); // to prevent rotating not existing figure
                        }
                    });

                    /** Menu closing implementation */
                    final MenuItem CloseButton = new MenuItem(null, new Label("Close menu")); 
                    CloseButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            TriangleMenu.hide();
                        }
                    });

                    /** Triangle context menu implementation */
                    TriangleMenu.getItems().addAll(ColorPickerButton, NullFillButton, RotateButton, DeleteButtton, CloseButton);
                    TriangleMenu.show(DrawingSpace, event.getScreenX(), event.getScreenY());
                }
            }
            /** Triangle movement implementation */
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && event.getButton() == MouseButton.PRIMARY) {
                Movement(event);
                MainPane.setBottom(null);
            }
        }
    }
    
    /** Class implementing reaction to scroll events on the triangle 
     * @see handle
     * @see Scaling
    */
    class TriangleScrollEvents implements EventHandler <ScrollEvent> {
        MyTriangle triangle;

        /** Determines triangle scaling */
        private void Scaling (ScrollEvent event) {
            double scroll_x = event.getX();
            double scroll_y = event.getY();

            if (triangle.FigureActionPerformed(scroll_x, scroll_y)) {
                Scale scale = new Scale();
                scale.setX(1 + event.getDeltaY() * 0.0005);
                scale.setY(1 + event.getDeltaY() * 0.0005);
                scale.setPivotX(triangle.centre.getX());
                scale.setPivotY(triangle.centre.getY());

                triangle.getTransforms().add(scale);
            }
        }

        /** Triangle scaling implementation */
        @Override
        public void handle (ScrollEvent event) {
            triangle = (MyTriangle) event.getSource();

            if (event.getEventType() == ScrollEvent.SCROLL) {
                Scaling(event);
            }
        }
    }
}




/** Class implementing rectangle manipulations 
 * @see MyRectangle
 * @see FigureActionPerformed
 * @see ChangePosX
 * @see ChangePosY
 * @see RectangleMouseEvents
 * @see RectangleScrollEvents
*/
class MyRectangle extends Rectangle {
    private BorderPane MainPane;
    private Pane DrawingSpace;

    /** MyTriangle class constructor */
    public MyRectangle (double x_pos, double y_pos, double width, double height, BorderPane MainPane, Pane DrawingSpace) {
        super(x_pos, y_pos, width, height);

        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1.4);
        this.setFill(Color.WHITE);
        this.setOnMouseClicked(new RectangleMouseEvents());
        this.setOnMouseDragged(new RectangleMouseEvents());
        this.setOnScroll(new RectangleScrollEvents());

        this.MainPane = MainPane;
        this.DrawingSpace = DrawingSpace;
    }

    /** Activates figure */
    private void ActivateFigure () {
        DrawingSpace.getChildren().remove(this);
        DrawingSpace.getChildren().add(this);
    }

    /** Detects if the mouse hoverd over the figure */
    private boolean FigureActionPerformed (double pos_x, double pos_y) {
        return getBoundsInLocal().contains(pos_x, pos_y);
    }
    /** Changes rectangle top left vertex's X coordinate */
    private void ChangePosX (double delta_x) {
        this.setX(this.getX() + delta_x);
    }
    /** Changes rectangle top left vertex's Y coordinate */
    private void ChangePosY (double delta_y) {
        this.setY(this.getY() + delta_y);
    }

    /** Class implementing reaction to mouse events on the rectangle 
     * @see handle
     * @see Movement
    */
    class RectangleMouseEvents implements EventHandler <MouseEvent> {
        MyRectangle rectangle;

        private double mouse_x;
        private double mouse_y;

        /** Determines rectangle movement */
        private void Movement (MouseEvent event) {
            double delta_x = event.getX() - mouse_x;
            double delta_y = event.getY() - mouse_y;

            if (rectangle.FigureActionPerformed(mouse_x, mouse_y)) {
                rectangle.ChangePosX(delta_x);
                rectangle.ChangePosY(delta_y);
            }

            mouse_x += delta_x;
            mouse_y += delta_y;
        }

        @Override
        public void handle (MouseEvent event) {
            rectangle = (MyRectangle) event.getSource();

            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                ActivateFigure();

                if (event.getButton() == MouseButton.PRIMARY) {
                    mouse_x = event.getX();
                    mouse_y = event.getY();
                }
                /** Rectangle advanced options implementation: color picking, rotation, other */
                else if (event.getButton() == MouseButton.SECONDARY) {
                    final ContextMenu RectangleMenu = new ContextMenu();

                    /** Rectangle color selection implementation */
                    final ColorPicker colorPicker = new ColorPicker((Color) rectangle.getFill());
                    final MenuItem ColorPickerButton = new MenuItem(null, colorPicker);
                    ColorPickerButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            rectangle.setFill(colorPicker.getValue());
                        }
                    });

                    /** Fill removal implementation */
                    final MenuItem NullFillButton = new MenuItem(null, new Label("Remove fill"));
                    NullFillButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            rectangle.setFill(null);
                        }
                    });

                    /** Rectangle rotation implementation */
                    final MenuItem RotateButton = new MenuItem(null, new Label("Rotate Figure"));
                    RotateButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            // Creating a rotation slider
                            Slider slider = new Slider(0, 180, 0);
                            slider.setShowTickLabels(true);
                            slider.setShowTickMarks(true);
                            slider.setMajorTickUnit(90);
                            slider.setBlockIncrement(10);
                            slider.setOrientation(Orientation.HORIZONTAL);
                            slider.setLayoutX(2);
                            slider.setLayoutY(195);

                            // Creating rotation transformation
                            Rotate rotate = new Rotate();
                            // Centering pivot point
                            rotate.setPivotX(rectangle.getWidth() / 2 + rectangle.getX());
                            rotate.setPivotY(rectangle.getHeight() / 2 + rectangle.getY());
                            // Adding rotation to the figure
                            rectangle.getTransforms().add(rotate);

                            // Connecting slider to the rotation
                            slider.valueProperty().addListener(new ChangeListener <Number>() { // allows for observing slider changes
                                public void changed(ObservableValue <?extends Number> observable, Number oldValue, Number newValue) {
                                    rotate.setAngle((double) newValue);
                                }
                            });

                            // Slider closing button
                            Button EndRotateButton = new Button("End rotation");
                            EndRotateButton.setOnAction(new EventHandler <ActionEvent> () {
                                @Override
                                public void handle (ActionEvent e) {
                                    MainPane.setBottom(null);
                                }
                            });

                            // Panel for figure rotation
                            HBox RotatePanel = new HBox();
                            RotatePanel.setPadding(new Insets(15, 12, 15, 12));
                            RotatePanel.setSpacing(10);
                            RotatePanel.setAlignment(Pos.CENTER_RIGHT);
                            RotatePanel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,null,null)));
                            RotatePanel.getChildren().addAll(slider, EndRotateButton);

                            MainPane.setBottom(RotatePanel);
                        }
                    });

                    /** Rectangle deletion implementation */
                    final MenuItem DeleteButtton = new MenuItem(null, new Label("Delete figure"));
                    DeleteButtton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            DrawingSpace.getChildren().remove(rectangle);
                            MainPane.setBottom(null); // to prevent rotating not existing figure
                        }
                    });

                    /** Menu closing implementation */
                    final MenuItem CloseButton = new MenuItem(null, new Label("Close menu"));
                    CloseButton.setOnAction(new EventHandler <ActionEvent> () {
                        @Override
                        public void handle (ActionEvent e) {
                            RectangleMenu.hide();
                        }
                    });

                    /** Rectangle context menu implementation */
                    RectangleMenu.getItems().addAll(ColorPickerButton, NullFillButton, RotateButton, DeleteButtton, CloseButton);
                    RectangleMenu.show(DrawingSpace, event.getScreenX(), event.getScreenY());
                }
            }
            /** Rectangle movement implementation */
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && event.getButton() == MouseButton.PRIMARY) {
                Movement(event);
                MainPane.setBottom(null);
            }
        }
    }

    /** Class implementing reaction to scroll events on the rectangle 
     * @see handle
     * @see Scaling
    */
    class RectangleScrollEvents implements EventHandler <ScrollEvent> {
        MyRectangle rectangle;

        /** Determines rectangle scaling */
        private void Scaling (ScrollEvent event) {
            double scroll_x = event.getX();
            double scroll_y = event.getY();

            if (rectangle.FigureActionPerformed(scroll_x, scroll_y)) {
                Scale scale = new Scale();
                scale.setX(1 + event.getDeltaY() * 0.0005);
                scale.setY(1 + event.getDeltaY() * 0.0005);
                scale.setPivotX(rectangle.getX() + rectangle.getWidth() / 2);
                scale.setPivotY(rectangle.getY() + rectangle.getHeight() / 2);

                rectangle.getTransforms().add(scale);
            }
        }

        /** Rectangle scaling implementation */
        @Override
        public void handle (ScrollEvent event) {
            rectangle = (MyRectangle) event.getSource();
            if (event.getEventType() == ScrollEvent.SCROLL) {
                Scaling(event);
            }
        }
    }
}




/** Class implementing NotQuitePaint application
 * @see DrawingPoint
 * @see start
 * @see MyCircle
 * @see MyTriangle
 * @see MyRectangle
 * @return
 */
public class NotQuitePaint extends Application {

    private Circle DrawingPoint (MouseEvent e) {
        /** Temporal drawing points creator */
        Circle C = new Circle(e.getX(), e.getY(), 2);
        C.setFill(Color.BLACK);
        return C;
    }

    @Override
    public void start (Stage PrimaryStage) { 
        /** Application starting method */
        /** Main application Pane */
        BorderPane MainPane = new BorderPane();
        MainPane.setPrefSize(1024, 628);
        
        
        /** Panel for figure type selection */
        HBox Toolbar = new HBox();
        Toolbar.setPadding(new Insets(15, 12, 15, 12));
        Toolbar.setSpacing(10);
        Toolbar.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY,null,null)));
        MainPane.setTop(Toolbar);

        /** Paint's drawing space */
        Pane DrawingSpace = new Pane();
        MainPane.setCenter(DrawingSpace);
        
        /** Circle drawing button */
        Button CircleButton = new Button("Circle");
        CircleButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override   
            public void handle (ActionEvent event) {
                if (DrawingSpace.isVisible()) { // to prevent starting drawing figure if not in DrawingSpace
                    DrawingSpace.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler <MouseEvent> () {
                        int clicks = 0;
                        Circle tmpC;
    
                        @Override
                        public void handle (MouseEvent e) {
                            if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.PRIMARY && clicks < 2) {
                                clicks++;
    
                                if (clicks == 1) {
                                    tmpC = DrawingPoint(e);
                                    DrawingSpace.getChildren().add(tmpC);
                                }
                                else if (clicks == 2) {
                                    double radius = Math.sqrt(Math.pow((e.getX() - tmpC.getCenterX()), 2) + Math.pow((e.getY() - tmpC.getCenterY()), 2));
    
                                    MyCircle C = new MyCircle(tmpC.getCenterX(), tmpC.getCenterY(), radius, DrawingSpace);
                                    DrawingSpace.getChildren().add(C);
    
                                    DrawingSpace.getChildren().remove(tmpC);
                                }
                            }
                        }
                    });
                }
            }
        });

        /** Triangle drawing button */
        Button TriangleButton = new Button("Triangle");
        TriangleButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override   
            public void handle (ActionEvent event) {
                if (DrawingSpace.isVisible()) { // to prevent starting drawing figure if not in DrawingSpace
                    DrawingSpace.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler <MouseEvent> () {
                        int clicks = 0;
                        Circle tmpC1;
                        Circle tmpC2;
    
                        @Override
                        public void handle (MouseEvent e) {
                            if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.PRIMARY && clicks < 3) {
                                clicks++;
                                
                                if (clicks == 1) {
                                    tmpC1 = DrawingPoint(e);
                                    DrawingSpace.getChildren().add(tmpC1);
                                }
                                else if (clicks == 2) {
                                    tmpC2 = DrawingPoint(e);
                                    DrawingSpace.getChildren().add(tmpC2);
                                }
                                else if (clicks == 3) {
                                    MyTriangle T = new MyTriangle(new Point2D(tmpC1.getCenterX(), tmpC1.getCenterY()), new Point2D(tmpC2.getCenterX(), tmpC2.getCenterY()), new Point2D(e.getX(), e.getY()), MainPane, DrawingSpace);
                                    DrawingSpace.getChildren().add(T);
    
                                    DrawingSpace.getChildren().removeAll(tmpC1, tmpC2);
                                }
                            }
                        }
                    });
                }
            }
        });

        /** Rectangle drawing button */
        Button RectangleButton = new Button("Rectangle");
        RectangleButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override   
            public void handle (ActionEvent event) {
                if (DrawingSpace.isVisible()) { // to prevent starting drawing figure if not in DrawingSpace
                    DrawingSpace.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler <MouseEvent> () {
                        int clicks = 0;
                        Circle tmpC;
    
                        @Override
                        public void handle (MouseEvent e) {
                            if (e.getEventType() == MouseEvent.MOUSE_CLICKED && e.getButton() == MouseButton.PRIMARY && clicks < 2) {
                                clicks++;
                                
                                if (clicks == 1) {
                                    tmpC = DrawingPoint(e);
                                    DrawingSpace.getChildren().add(tmpC);
                                }
                                else if (clicks == 2) {
                                    double x_pos = Math.min(tmpC.getCenterX(), e.getX());
                                    double y_pos = Math.min(tmpC.getCenterY(), e.getY());
                                    double width = Math.abs(tmpC.getCenterX() - e.getX());
                                    double height = Math.abs(tmpC.getCenterY() - e.getY());
    
                                    MyRectangle R = new MyRectangle(x_pos, y_pos, width, height, MainPane, DrawingSpace);
                                    DrawingSpace.getChildren().add(R);
                                    
                                    DrawingSpace.getChildren().remove(tmpC);
                                }
                            }
                        }
                    });
                }
            } 
        });


        // Text for instructions
        String InstructionString = "Circle:\nTo draw select two points: center and radius\n\nTriangle:\nTo draw select three points (one for each vertex)\n\nRectangle:\nTo draw select two points (for opposite vertices)\n\nAdvanced features:\nTo use figure's features like color selection or rotation,\npress the secondary mouse button on top of the figure";
        Label InstructionLabel = new Label(InstructionString);
        InstructionLabel.setFont(new Font("Courier New", 24));
        InstructionLabel.setVisible(false);

        // Text for info
        String InfoString = "Program: Not Quite Paint\nVersion: 1.0\nProgram's intended usage: Drawing shapes\n\nCreator's info:\nName: Jakub\nSurname: Musial\nIndex number: 268442";
        Label InfoLabel = new Label(InfoString);
        InfoLabel.setFont(new Font("Courier New", 24));
        InfoLabel.setVisible(false);

        /** Drawing space button */
        Button DrawingSpaceButton = new Button("Drawing space");
        DrawingSpaceButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {
                InfoLabel.setVisible(false);
                InstructionLabel.setVisible(false);
                
                DrawingSpace.setVisible(true);
                MainPane.setCenter(DrawingSpace);
            }
        });

        /** Instruction button */
        Button InstructionButton = new Button("Instructions");
        InstructionButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {

                if (!InstructionLabel.isVisible()) {
                    DrawingSpace.setVisible(false);
                    InfoLabel.setVisible(false);

                    InstructionLabel.setVisible(true);
                    MainPane.setCenter(InstructionLabel);
                }
                else if (InstructionLabel.isVisible()) {
                    InstructionLabel.setVisible(false);
                    DrawingSpace.setVisible(true);
                    MainPane.setCenter(DrawingSpace);
                }
            }
        });
        /** Info button */
        Button InfoButton = new Button("Program info");
        InfoButton.setOnAction(new EventHandler <ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {

                if (!InfoLabel.isVisible()) {
                    DrawingSpace.setVisible(false);
                    InstructionLabel.setVisible(false);

                    InfoLabel.setVisible(true);
                    MainPane.setCenter(InfoLabel);
                }
                else if (InfoLabel.isVisible()) {
                    InfoLabel.setVisible(false);

                    DrawingSpace.setVisible(true);
                    MainPane.setCenter(DrawingSpace);
                }
            }
        });

        
        // Adding figure buttons to Toolbar
        Toolbar.getChildren().addAll(DrawingSpaceButton, CircleButton, TriangleButton, RectangleButton, InstructionButton, InfoButton);

        /** Application Scene */
        Scene mainScene = new Scene(MainPane);

        PrimaryStage.setTitle("Not quite Paint");
        PrimaryStage.setScene(mainScene);
        PrimaryStage.show();
    }

    public static void main (String args[]) {
        Application.launch(args);
    }
}
