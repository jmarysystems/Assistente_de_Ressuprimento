/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jmary.home.Browser;

import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;

/**
 *
 * @author AnaMariana
 */
public class WebViewPane extends Pane {    
    public WebView view;

    public WebViewPane( String url ) {     
        view = new WebView();
        view.getEngine().load( url );
 
        GridPane grid = new GridPane();
        GridPane.setConstraints(view, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
                new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
        );
        grid.getChildren().addAll( view );
        getChildren().add(grid);
    }

    WebViewPane(String url, boolean b) {     
        view = new WebView();
        view.getEngine().loadContent( url );
 
        GridPane grid = new GridPane();
        GridPane.setConstraints(view, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
                new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
        );
        grid.getChildren().addAll( view );
        getChildren().add(grid);
    }
    
    WebViewPane(String url, boolean b, boolean b2) {     
        Media m = new Media( url );
        MediaPlayer mp = new MediaPlayer(m);
        mp.setAutoPlay(false);
        MediaControl mediaControl = new MediaControl(mp);
 
        GridPane grid = new GridPane();
        GridPane.setConstraints( mediaControl, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
                new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
        );
        grid.getChildren().addAll( mediaControl );
        getChildren().add(grid);
    }

    @Override 
    protected void layoutChildren() {
        List<Node> managed = getManagedChildren();
        double width = getWidth();
        double height = getHeight();
        double top = getInsets().getTop();
        double right = getInsets().getRight();
        double left = getInsets().getLeft();
        double bottom = getInsets().getBottom();
        for (int i = 0; i < managed.size(); i++) {
            Node child = managed.get(i);
            layoutInArea(child, left, top, width - left - right, height - top - bottom,
                    0, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER);
        }
    }
}