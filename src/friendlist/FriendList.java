
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
 
/**
 * An example of an accordion control. You can use accordion controls to define
 * individual panes and  display them one at a time.
 *
 * @see javafx.scene.control.Accordion
 * @related controls/buttons/ToggleButton
 * @related controls/toolbar/ToolBar
 */
public class FriendList extends Application {
    private static final Image ICON_44 = new Image(FriendList.class.getResourceAsStream("fishy.png"));
    private static final Image ICON_45 = new Image(FriendList.class.getResourceAsStream("cartman_hitler_icon.png"));
    private static final Image ICON_46 = new Image(FriendList.class.getResourceAsStream("afroman.png"));
    private static final Image ICON_47 = new Image(FriendList.class.getResourceAsStream("grey_snail.png"));
    private static final Image ICON_48 = new Image(FriendList.class.getResourceAsStream("Toy Boy Panda.png")); //48x48 only
    private ObservableList<VBox> friendItems = FXCollections.observableArrayList();
    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 250,1000));
        
        ListView listview = new ListView();
            
        friendItems.add(createFriend(ICON_48, "Stranger Danger"));
        friendItems.add(createFriend(ICON_47, "Paul Massey"));
        friendItems.add(createFriend(ICON_44, "Backstabber"));
        friendItems.add(createFriend(ICON_45, "Mattyftw"));
        friendItems.add(createFriend(ICON_46, "Dracs von Mordenheim"));
        listview.setItems(friendItems);
        
 //       listview.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
   //         @Override
     //       public void handle(MouseEvent e) {
       //         if(e.getButton() ==  MouseButton.SECONDARY){
         //           final ContextMenu contextMenu = new ContextMenu();
           //         contextMenu.
             //   }
            //}
       // });

        
        
        
        TitledPane t1 = new TitledPane("General", listview);
        TitledPane t2 = new TitledPane("Recently Played", new Text("String"));
        TitledPane t3 = new TitledPane("Offline", new Rectangle(120,50, Color.RED));
        
        Accordion accordion = new Accordion();
        accordion.getPanes().add(t1);
        accordion.getPanes().add(t2);
        accordion.getPanes().add(t3);
        root.getChildren().add(accordion);
    }
    public VBox createFriend(Image image, String friendName){
        final VBox box = new VBox(10);
        ImageView imageView = new ImageView(image);
        imageView = new ImageView(image);
        Label label = new Label(friendName, imageView); // "Image on the left" max size
        label.setContentDisplay(ContentDisplay.LEFT);
        box.getChildren().add(label);
        
        final ContextMenu cm = new ContextMenu();
        cm.getItems().add(makeMenuItem("Invite to Game"));
        cm.getItems().add(makeMenuItem("Send Message"));
        cm.getItems().add(makeMenuItem("View Profile"));
        cm.getItems().add(makeMenuItem("Remove"));
        cm.getItems().add(makeMenuItem("Spectate"));
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    cm.show(box, e.getScreenX(), e.getScreenY());
                }
            }
        });
        return box;
    }
    private MenuItem makeMenuItem(String label){ 
        Label l = new Label(label);
        MenuItem menuitem = new MenuItem();
        menuitem.setGraphic(l);
        menuitem.setStyle("-fx-padding: 5 10 5 10");
        return menuitem;
    }
    public double getSampleWidth() { return 150; }
 
    public double getSampleHeight() { return 150; }
 
    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}