import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;

class WordList{
    int randomFiver;
    String randomFiverWord;
    
public WordList(){
    try{

        //opens and reads my words file
        File newFile = new File("C:/Users/itsam/CS112/MyWork/Project01/words.txt"); 
        Scanner allWords = new Scanner(newFile);
        //arraylists to create arrays with all the words and with only the five character words
        List<String> listOfAllWords = new ArrayList<String>();
        List<String> listOfFivers = new ArrayList<String>();
        //count,str,and checker variables help with the while and for loops that fill my AllWords and Fivers arrays
        int count = 0;
        String str = "";
        String checker = "";
        
        //while loop that takes all the words in words.txt and puts them into an array
        
        while (allWords.hasNextLine()){
        str = allWords.nextLine();
        listOfAllWords.add(str);
        count++;
        }
        String[] allWordsArray = listOfAllWords.toArray(new String[0]);

        //for loop that goes through every word in AllWords array and checks if they have a length of 5 and then saves it to my Fivers array
        for(int i = 0; i < allWordsArray.length; i++){
         checker = allWordsArray[i];
         if(checker.length() == 5){
            listOfFivers.add(checker);
         }
        }
        String[] fivers = listOfFivers.toArray(new String[0]);
        //the following variables calculate the random word
        randomFiver = (int)(Math.random()*(fivers.length));
        randomFiverWord = "" + fivers[randomFiver];

         }catch(Exception e){
          e.printStackTrace();   
        }
    }

    //getRandomWord method that returns a random word in the fivers array
        public String getRandomWord(){
            return this.randomFiverWord;
        }
        //this method is called by the wordle class to check that the user input is only letters and no symbols or numbers
        public boolean onlyLetters(String str){
            return str.matches("^[a-zA-Z]*$");
        }
}
public class Wordle{
    public static void main(String[] args){
        //opening statements of the game
        System.out.println("Welcome to Wordle!");
        System.out.print("You have 6 chances to guess our mystery 5 letter word!!!");
        
        WordList newObj = new WordList();
        Scanner user = new Scanner(System.in);
        String userInput = "";
        int count = 1;

        //Sets the random word to a string
        String x = newObj.getRandomWord();
        System.out.println("word is " + x);

        //while statement that goes through each 6 guesses and checks each char of the word against the randomFiver
        while(count <= 6){
        
        //This block of code pulls a random word and prompts the user to enter a guess. It also transforms that guess to lower case
        System.out.println("");
        System.out.println("Guess " + count + ": ");
        userInput = user.nextLine();
        userInput = userInput.toLowerCase();

        //first if statement that ensures that the user input is all letters and has a length of 5
        if(newObj.onlyLetters(userInput) == true && userInput.length() == 5){
        for(int j = 0; j < userInput.length(); j++){
        String userWord = "" + userInput.charAt(j);
        
        //first, it checks if the char is not in the string at all, if it is, then it checks if it is in the right location
        if(!x.contains(userWord)){
        System.out.print("_ ");
        }else if(x.contains(userWord)){
         if(userInput.charAt(j) == x.charAt(j)){
            String correctLetter = "" + userInput.charAt(j);
            System.out.print(correctLetter + " ");
            
         }else{
            String begOfWord = userInput.substring(0,j);
            String restOfWord = userInput.substring((j+1),5);
            if(begOfWord.contains(userWord)){
               System.out.print("_ "); 
            }else if(restOfWord.contains(userWord)){
                System.out.print("[" + userInput.charAt(j) + "] ");
            }else{
            System.out.print("[" + userInput.charAt(j) + "] ");
         }
        }
        }
        
    }
    count++;
    //else statement that prompts the user to reenter a correct input
    }else{
    System.out.println("Enter a five letter WORD!");
}
        //this prints a congradulations statement if the user input is completely equal to the random word 
        if(userInput.equals(x)){
            System.out.println("Congrats you guessed the word :)");
            break;
            }
    }

    //this if statement checks if the last guess is incorrect and prints the correct word
    if(!(userInput.equals(x))){
        System.out.println("");
        System.out.println("Better luck next time!");
        System.out.println("The word was " + x);
    }

}
}





