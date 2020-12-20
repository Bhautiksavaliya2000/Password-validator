import java.util.*;

class PlayerDetails {
     
    String player1,player2;
    Scanner input = new Scanner(System.in);
    public String name1()
    {
        System.out.println("Enter name of player1.=>");
        player1 = input.next();
        return player1;
    }
    public String name2()
    {
        System.out.println("Enter name of player2.=>");
        player2 = input.next();
        return player2;
    }
    public char choise()
    {
        System.out.println("Choose the symbol. (o / x) =>");
        
        while(true)
        {
        char symbol = input.next().charAt(0);
        
        if(symbol == 'o')
        {
            return 'o';
        }
        else if(symbol == 'x')
        {
            return 'x';
        }
        else
            System.out.println("Invalid input choose right symbol. try again.");
        }
    }
}

class Layout
{
    void showLayout()
    {
        System.out.println("A sample layout is :");
        System.out.println(" 1 | 2 | 3\n 4 | 5 | 6\n 7 | 8 | 9 ");
    }    
}

class WhoIsWinner
{
    int i,count=0;
    int checkRWD(int R,int C,char mat[][],char sy)
    {
        // TO find if diagonal.
        if((R==0&&C==0)||(R==1)&&(C==1)||(R==2)&&(C==2))
        {
             for(i=0;i<3;i++)                  
            {
                if(mat[i][i] == sy)
                count++;
            }
            if(count == 3) {
                System.out.println(sy + " won the game.");
                return 1;
            }
        }
        else if((R==0)&&(C==2) || (R==2)&&(C==0) )
        {
            if(mat[0][2]==sy && mat[1][1]==sy && mat[2][0]==sy) {
                System.out.println(sy + " won the game");
                return 1;
            }
        } 
        count=0;
        for(i=0;i<3;i++)                  // To find if raw.
        {
            if(mat[R][i] == sy)
              count++;
        }
        if(count == 3) {
            System.out.println(sy + " won the game");
            return 1;
        }
        count = 0;
        for(i=0;i<3;i++)                 // To find if column.
        {
            if(mat[i][C] == sy)
                count++;
        }
        if(count == 3) {
            System.out.println(sy + " won the game");       
            return 1;
        }
        count = 0;
        return 0;
    }
}

class PlayGame
{
    Scanner input = new Scanner(System.in);
    PlayerDetails ob2 = new PlayerDetails();                  // ob2 = for containing player1's symbol.
    
    char [][]matrix = new char[3][3];
    int [] record = new int[9];
    WhoIsWinner win = new WhoIsWinner();
    
    public void gamePlay(char p1symbol,char p2symbol)
    {
        int stop=0;
        matrix[2][2] = '9';
        matrix[0][0] = '1'; matrix[0][1] = '2';matrix[0][2] = '3';matrix[1][0] = '4';
        matrix[1][1] = '5';matrix[1][2] = '6';matrix[2][0] = '7';matrix[2][1] = '8';
        
        try {
        for(int z=1;z<=9;z++)
        {
            //    FOR PLAYER1.
            if(z%2 != 0)
            {
                System.out.println("Player1's turn =>");
                do {
                int temp = input.nextInt();
                
                // to chechk whether number is repeted.
                for(int i=0;i<z;i++)
                {
                    if(record[i] == temp )            
                    {
                        System.out.println("This number was entered once. Re-Enter another number.");
                        temp = input.nextInt();
                    }
                    else
                        record[z] = temp;
                }
                
                if(temp>0 && temp<10)
                {
                    if(temp >=1 && temp <= 3) {
                        matrix[0][temp-1] = p1symbol;
                        stop = win.checkRWD(0, temp-1, matrix, p1symbol);
                    }

                    if(temp >=4 && temp <= 6) {
                        matrix[1][temp-4] = p1symbol;
                        stop = win.checkRWD(1,temp-4, matrix, p1symbol);
                    }

                    if(temp>=7 && temp <= 9){
                          matrix[2][temp-7] = p1symbol;
                        stop = win.checkRWD(2,temp-7, matrix, p1symbol);
                    }
                    break;
                }
                else
                    System.out.println("Invalid input. Try again");
                }while(true);
                
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)                    // Display placevalue in matrix.
                   {
                      System.out.print(matrix[i][j] + " | ");
                   }
                   System.out.println("");
                }     
            } 
               // FOR PLAYER2.
               
            else
            {
                System.out.println("Player2's turn :\n");
                
                do {
                int temp = input.nextInt();
                
                //to check whether the number is repeted.
                 for(int i=0;i<z;i++)
                {
                    if(record[i] == temp )            
                    {
                        System.out.println("This number was entered once. Re-Enter another number.");
                        temp = input.nextInt();
                    }
                    else
                        record[z] = temp;
                }
              
                if(temp>0 && temp<10)
                {
                    if(temp >=1 && temp <= 3) {
                        matrix[0][temp-1] = p2symbol;
                        stop = win.checkRWD(0, temp-1, matrix,p2symbol);
                    }

                    if(temp >=4 && temp <= 6) {
                        matrix[1][temp-4] = p2symbol;
                        stop = win.checkRWD(1,temp-4, matrix, p2symbol);
                    }

                    if(temp>=7 && temp <= 9){
                          matrix[2][temp-7] = p2symbol;
                        stop = win.checkRWD(2,temp-7, matrix, p2symbol);
                    }
                    break;
                }
                else
                    System.out.println("Invalid input. Try again");
                }while(true);
            
                for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)                    // Display placevalue in matrix.
                   {
                      System.out.print(matrix[i][j] + " | ");
                   }
                   System.out.println("");
                }        
            }
            if(stop == 1)
                break;
        }
        if(stop==0)
            System.out.println("The game is draw.");
    }
        catch(Exception e)
        {}
       
    }
}


public class TTCmain
{
    public static String player1,player2;
    
    public static void main(String []args)
    {
        char symbol1,symbol2;
        PlayerDetails ob1 = new PlayerDetails();
        player1 = ob1.name1();
        player2 = ob1.name2();
        symbol1 = ob1.choise();
        if(symbol1 == 'x')
            symbol2 = 'o';
        else 
            symbol2 = 'x';
        
        
        Layout layout = new Layout();
        layout.showLayout();
        
        new PlayGame().gamePlay(symbol1,symbol2);
    }
} 
  