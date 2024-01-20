package com.example.tictactow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private Label playerXSc,PlayerOSc;
    private Button buttons[][] = new Button[3][3];
    private boolean playerX = true;
    private int playerXscore = 0,PlayerOscore = 0;
    private BorderPane createPane(){
        BorderPane root = new BorderPane();
        //Ttitle
        Label tittle = new Label("                 Tic Tac Toe");
        tittle.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold");
        root.setTop(tittle);

        //Board
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(150,150);
                button.setStyle("-fx-font-size : 20pt; -fx-font-weight : bold");
                button.setOnAction(event->buttonClicked(button));
                grid.add(button,j,i);
                buttons[i][j] = button;
            }
        }
        root.setCenter(grid);

        //Score
        HBox score = new HBox(20);
        playerXSc = new Label("Player X : 0");
        playerXSc.setStyle("-fx-font-size : 17pt; -fx-font-weight : bold");
        PlayerOSc = new Label("                              Player O : 0");
        PlayerOSc.setStyle("-fx-font-size : 17pt; -fx-font-weight : bold");
        score.getChildren().addAll(playerXSc,PlayerOSc);

        root.setBottom(score);
        return root;
    }
    private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                button.setText("");
            }
        }
    }
    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXscore++;
            playerXSc.setText("Player X : " + playerXscore);
        }
        else{
            PlayerOscore++;
            PlayerOSc.setText("                     Player O : " + PlayerOscore);
        }
    }
    private void checkWinner(){
        //Row
        for(int i=0; i<3; i++){
            if(!buttons[i][0].getText().equals("") && buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())){
                String winner = buttons[i][1].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();
            }
        }

        //Columns
        for(int i=0; i<3; i++){
            if(!buttons[0][i].getText().equals("") && buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText())){
                String winner = buttons[i][1].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();
            }
        }

        //Diagonal
        if(!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[2][2].getText())){
            String winner = buttons[0][0].getText();
            showWinner(winner);
            updateScore(winner);
            resetBoard();
        }
        if(!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[1][2].getText()) &&
                buttons[1][2].getText().equals(buttons[2][2].getText())){
            String winner = buttons[0][2].getText();
            showWinner(winner);
            updateScore(winner);
            resetBoard();
        }
        boolean isTie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(buttons[i][j].getText().equals("")) isTie = false;
            }
        }
        if(isTie){
            updateScore("X");
            updateScore("O");
            resetBoard();
        }
    }
    private void showWinner(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Congratulations " + winner + "!! you won the game");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private void buttonClicked(Button button){
        if(button.getText().equals("")){
            if(playerX){
                button.setText("X");
                playerX = false;
            }
            else{
                button.setText("O");
                playerX = true;
            }
        }
        checkWinner();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createPane());
        stage.setTitle("Hello! Haritha");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}