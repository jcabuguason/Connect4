
package connect4;

import java.util.Scanner;

public class Game{
       
       private final int numColumns = 7;//  constant speciifying number of columns desired on grid
       private final int numRows = 6; // constant defining number of rows desired on grid 
       private char [][] board ;// define matrix for game board
       private int [] currentRow; // define array for status of columns filled and by how much
       private char winner; // winner of game       
       
       
       /**
        *  Constructor for new Game object
        */
       public Game(){
              this.board = new char[numRows][numColumns]; //define board with desired number of rows and columns
              this.currentRow = new int[numColumns]; // create array for status of columns filled and the quanity they are 
              this.winner = '-'; // winner default is '-' if game is inactive or has not yet been determined 
              
              //initialize board with placeholder values for each index
              for(int row = 0; row < numRows; row++)
                     for(int col = 0; col < numColumns; col++)
                            board[row][col] = '_';
       }
       
       
       /**
        * Effects: returns this.winner 
        */
       public char getWinner(){
              return this.winner; 
       }
       
       /**
        * . Modifies this.currentRow
        * Effects: fills next open row of the desired column with the player char  
        * @param player - char value that represents player token
        * @param column - column that player wants to fill
        */
       public void pickColumn(char player, int column){
              
                     if (currentRow[column] < numRows)
                            board[currentRow[column]++][column] = player;
                     else{
                            //recursively ask for valid input if column picked is full
                            Scanner scanS = new Scanner(System.in);
                            System.out.println("Error: that column is full ! Please pick a different column.");
                            pickColumn(player, scanS.nextInt());
                     }
       }         
       
       /**
        * . Modifies: this.winner , if connect 4 found
        *   Effects: returns the status of the game, returns false if game is finished, returns true if game is still active
        */
       public boolean isPlaying(){
              boolean full = isBoardFull();
              boolean connect4 = checkForConnect4();
         
              if(full && !connect4)
                     this.winner = '=';
              if( full || connect4)
                     return false;
              
              return true;
       }
       
       /**
        *  Effects: returns true if no more available columns to place token in, false if otherwise
        */
       public boolean isBoardFull(){
              for(int column = 0; column < currentRow.length; column++){
                     if(currentRow[column] < 6 )
                            return false;
              }
              return true;
       }
       
       /**
        * effects: prints out the board using text
        */
      public void displayGame(){              
               for(int row = numRows-1; row >-1; row--){
                     for(int col = 0; col < numColumns; col++){
                            System.out.print(board[row][col] + " ");
                     }
                     System.out.println("");
              }
       }
    
      /**
       * . Effects: returns  true if there are 4 same-color pieces connected either horizontally,vertically, or diagonally , otherwise false
       */
       private boolean checkForConnect4(){
           if(checkRows() || checkColumns() || checkDiagonals())
                  return true;   
           else 
                  return false;
       }

       /**
        * . Effects: returns true if there are 4 same color pieces connected horizontally in any row, otherwise false
        */
       private boolean checkRows(){
           int count = 0;
           char currentChar = 'n';         
           //check horizontally through each row for connect four
           for(int row = 0; row < numRows; row++){
                     for(int col = 0; col < numColumns; col++){
                            if(board[row][col] == currentChar){
                                   count++;
                                   //System.out.println("count: " + count + "board["+row+"]["+col+"]");
                                   if(count == 3 && (currentChar =='b'  || currentChar == 'r') ){
                                          this.winner = currentChar;
                                          return true;
                                   }
                            }else{
                                   currentChar = board[row][col];
                                   count = 0;
                            }
                     }
                     count = 0;
                     currentChar = 'n';
              }
              return false;
       }
       
       /**
        * Effects: returns true if there are any 4 same color pieces connected vertically in a column, otherwise false
        */
       private boolean checkColumns(){
              //use array to keep track of how much each column is filled by
             int [] columnCount = new int[numColumns]; 
             char currentChar = 'n';
            
             //check vertically through each column for connect four
            for(int col = 0; col < numColumns; col++){   
                     for(int row = 0; row < numRows; row++){
                            if(board[row][col] == currentChar){
                                   columnCount[col]++;
                                   if(columnCount[col] == 3 && (currentChar =='b'  || currentChar == 'r') ){
                                          this.winner = currentChar; // game is won and winner is set to last person who made a move
                                          return true;
                                   }
                            }else{
                                   currentChar = board[row][col]; 
                                   columnCount[col] = 0;
                            }
                     }
                     columnCount[col] = 0;
                     currentChar = 'n';
              }
            //if no four connected horizontal pieces
              return false;
       }
       
       /**
        * Effects: returns  true if there are four same color pieces connected diagonally in any direction, otherwise false 
        */
       private boolean checkDiagonals(){           
              //initialize variables needed for diagonal verification
              int temp;
              int count = 0; // count pieces that are connected
              char currentChar = 'n';          
           
              // checking diagonal rows from left side of board, traversing up one column and one row at a time for a given path
              for(int row = 0; row < numRows; row++){
                     temp = row;    
                     
                     for(int col = 0; col < numColumns; col++){
                            if(temp >= numRows)
                                   break;
                            if(board[temp][col] == currentChar){
                                     count++;
                                     if(count == 3 && (currentChar =='b'  || currentChar == 'r') ){
                                            // game is won and winner is set to last person who made a move
                                          this.winner = currentChar;
                                          return true;
                                   }
                              }else{
                                     currentChar = board[temp][col];
                                     count = 0;
                              }
                            temp++;
                     }
                     currentChar ='n';
                     count = 0;     
              }
             //check diagonal rows from bottom side of board, traversing up one column and one row at a time for a given path
             for(int col = 1; col < numColumns; col++){
                     temp = col;    
                     for(int row = 0; row < numRows; row++){
                            //break and check next diagonal
                            if(temp >= numColumns)
                                   break;
                            if(board[row][temp] == currentChar){
                                     count++;
                                     if(count == 3 && (currentChar =='b'  || currentChar == 'r') ){
                                            // game is won and winner is set to last person who made a move
                                          this.winner = currentChar;
                                          return true;
                                   }
                              }else{
                                     currentChar = board[row][temp];
                                     count = 0;
                              }
                            temp++;
                     }       
                     //reinitialize values for next diagonal
                     currentChar ='n';
                     count = 0;  
              }
             //checking diagonal rows starting from right side of the board, traversing left one column and up one row at a time for a given path
               for(int row = 0; row < board.length; row++){
                     temp = row;    
                     for(int col = numColumns-1; col> -1; col--){
                            if(temp >= numRows)
                                   break;
                            if(board[temp][col] == currentChar){
                                     count++;
                                     if(count == 3 && (currentChar =='b'  || currentChar == 'r') ){
                                            // game is won and winner is set to last person who made a move
                                          this.winner = currentChar;
                                          return true;
                                   }
                              }else{
                                     currentChar = board[temp][col];
                                     count = 0;
                              }
                            temp++;
                     }       
                     //reinitialize values for next diagonal
                     currentChar ='n';
                     count = 0;
              } 
              //checking diagonal rows from bottom side of board, traversing left one column and up one row at a time for a given path
              for(int col = numColumns-1; col > -1; col--){
                     temp = col;    
                     for(int row = 0; row < numRows; row++){
                            //if minimum column reached then break from this diagonal row
                            if(temp < 0)
                                   break;
                            //if current element has same player key as before 
                            if(board[row][temp] == currentChar){
                                     count++;
                                     //if connect 4 , set winner and return true
                                     if(count == 3 && (currentChar =='b'  || currentChar == 'r') ){
                                            // game is won and winner is set to last person who made a move
                                          this.winner = currentChar;
                                          return true;
                                   }
                              }
                            else{
                                     currentChar = board[row][col]; 
                                     count = 0;     //reset count 
                              }
                            //down one column left
                            temp--;
                     }       
                     //reinitialize values for next diagonal
                     currentChar = 'n';
                     count = 0;    
              }       
              //if no diagonal connect 4 found then return false
              return false;
       }
}
