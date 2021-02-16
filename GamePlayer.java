/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GamePlayer{       

       public static void main(String[] args) {
              
              //create game instance
              Game game = new Game();
              
              //welcome user
              System.out.println("Beginning the game!");
              
              //initialize player chars
              char P1 = 'b';
              char P2 = 'r';
              
              char activePlayer = P1;
              
              while(game.isPlaying() == true){
                     
                     if(activePlayer == P1){
                            System.out.print("Player 1's move : ");
                            activePlayer = P2;
                     }
                     else{
                            System.out.print("Player 2's move : ");
                            activePlayer = P1;
                     }
              
                     int move;
                     boolean invalidInput = true;
                     
                     while(invalidInput){
                            try{
                                   move = getInput();
                                   game.pickColumn(activePlayer,move);
                                   //if no mismatch exception caught, input is valid
                                   invalidInput= false;
                            }catch(InputMismatchException e){
                                   System.out.println("Error: Not a column number! Please select a valid column");
                            }
                     }
                     
                     System.out.println(""); 
                     game.displayGame(); // displays the game
              }
              
              System.out.println("Winner is: " + game.getWinner() + "!"); //gets the winner
       }

       
       
       /**
        * 
        * @return user-inputted integer that is a valid column 
        */
       public static int getInput(){
              
              Scanner userInput = new Scanner(System.in);       
              int move = 0;
              boolean flag = true;
              move = userInput.nextInt();
              //recursively check until valid move
              if(move > 6 || move < 0){
                     System.out.println("Error: Invalid Column: Please select a valid column");
                     return getInput();
              }
              else  
                     return move;
       }
}
