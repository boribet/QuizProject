
import java.io.*;
import java.util.*;

public class Quiz {

    /* Class attributes */
    private Scanner scanner;
    private Questions questions;
    private challenger challengers;
    private String mode;

    /**
     * Constructor to initialize variables
     */
    public Quiz() {
        scanner = new Scanner(System.in);
        challengers = new challenger();
    }

    /**
     * log method let challenger login or register and return challenger name
     *
     * @return String
     */
    public String log() {
        // login or rigester
        System.out.println("Press L to Login");
        System.out.println("Press R to Register");
        System.out.println("Press Q to Quit");

        String c = scanner.next().toLowerCase(); // get challenger choice
        while (!c.equals("q")) { // termination condition
            switch (c) {
                case "l": // login 
                    System.out.println("Enter your username");
                    String name = scanner.next(); // get name
                    System.out.println("Enter your password");
                    String password = scanner.next(); // get password
                    if (challengers.found(name)) // check name
                    {
                        if (challengers.getPassword(name).equals(password)) // check password
                        {
                            return name; // return name
                        }
                    }
                    System.out.println("Error in name or in password"); // error message
                    break;
                case "r": // register
                    System.out.println("Enter Your Name");
                    name = scanner.next(); // get name
                    if (!challengers.found(name)) { // check name if found in system
                        System.out.println("Enter New Password");
                        password = scanner.next();    // get new password
                        challengers.add(name, password); // add challenger to system
                        return name; // return name
                    }
                     {
                        System.out.println("This player already exists"); // if challenger name found in system
                    }
                    break;
            }
            System.out.println("L -> Login");
            System.out.println("R -> Register");
            System.out.println("Q -> Quit");
            c = scanner.next().toLowerCase();
        }
        return ""; // for q choice
    }

    /**
     * scores method print scores of given challenger
     *
     * @param name String
     */
    public void scores(String name) {
        if (!challengers.found(name)) // if challenger not found
        {
            System.out.println("Player Not Found");
        } else // if challenger found
        if (challengers.score(name) == null) // if no scores
        {
            System.out.println("No Scores Yet");
        } else if (challengers.score(name).isEmpty()) {
            System.out.println("No Scores Yet");
        } else // print scores
        {
            System.out.println(challengers.score(name));
        }
    }

    /**
     * single method represent quiz for single challenger
     *
     * @param name String
     */
    public void single(String name, String mod) {
        questions = new Questions(mod); // get new questions
        System.out.println("This Quiz will have 10 Questions"); // user message
         System.out.println("For each correct questions you will receive 1 point");
         System.out.println("If you get the question wrong, skip or cheat then no point will be awarded to you");
         // user message// user message
        int score = 0; // variable to store challenger score
        for (int i = 1; i <= 10; i++) { // 10 questions
            System.out.println(i+")"); //Updates the quetsion number
            String[] ques = questions.getQuestion().split("!!!"); // call getQuestion method to get question
            // print question
            System.out.println(ques[0] + "\n[S] Skip\t\t [C]Cheat");
            String answer = ques[1]; // keep answer
            String choice = scanner.next(); // get challenger choice
            
            if (choice.equals("c")) { // challenger see the answer
                System.out.println("The correct answer was " + answer);
                
                continue;
            }
            if (choice.equals("s")) { // challenger skip question
                
                continue;
            }
            if (choice.equalsIgnoreCase(answer)) { // challenger answer is true
                System.out.println("Good job! You win 1 point");
                score++;
            } else { // challenger answer is false
                System.out.println("Wrong answer");
            }
            System.out.println("");
        }
        // print score to challenger 
        System.out.println("Your score is " + score);
        challengers.addScore(name, score); // add challenger score
    }

    /**
     * singleVSComputer method represent quiz for single challenger VS computer
     *
     * @param name String
     */
    public void singleVSComputer(String name, String mod) {

        double coff = 0;
        System.out.println("\nE) Easy\nM) Median\nH) Hard");
        String str = scanner.next();
        while (!(str.equalsIgnoreCase("e") || str.equalsIgnoreCase("m") || str.equalsIgnoreCase("H"))) {
            System.out.println("Invalid input. Please try again");
            System.out.println("\nE) Easy\nM) Median\nH) Hard");
            str = scanner.next();
        }

        if (str.equalsIgnoreCase("e")) {
            coff = 0.3;
        }
        if (str.equalsIgnoreCase("m")) {
            coff = 0.5;
        }
        if (str.equalsIgnoreCase("h")) {
            coff = 0.8;
        }

        questions = new Questions(mod); // get new questions
        System.out.println("This Quiz will have 10 Questions");
        System.out.println("You will play first and then it is the computer's turn");
        System.out.println("If you skip cheat or fail no point will be awarded to you ");
        System.out.println("The computer will still get to play if you skip or cheat");// user message
        System.out.println();
        int score = 0; // variable to store challenger score
        int cScore = 0; // variable to store computer score
        for (int i = 1; i <= 10; i++) { // 10 questions
            System.out.println("Q)" + i);
            String[] ques = questions.getQuestion().split("!!!"); // call getQuestion method to get question
            String[] cQues = questions.getQuestion().split("!!!"); // call getQuestion method to get question
            System.out.println("");
            // challenger round
            System.out.println("-------------------");
            System.out.println(name + "'s Round");
            System.out.println("-------------------");
            // print question
            System.out.println(ques[0] + "\n[S] Skip\t\t [C]Cheat");
            String answer = ques[1]; // keep answer
            String choice = scanner.next(); // get challenger choice
            if (choice.equals("c")) { // challenger see the answer
                System.out.println("The correct answer was " + answer);
               
            }
            if (choice.equalsIgnoreCase("s")) { // challenger skip question
               
            }
            if (choice.equalsIgnoreCase(answer)) { // challenger answer is true
                System.out.println("Good job! You win 1 point");
                score++;
            } else if (!choice.equals("s") && !choice.equals("c")) { // challenger answer is false
                System.out.println("Wrong answer");
            }

            System.out.println("");
            System.out.print("-------------------");
            System.out.print("Computer's Round  ");
            System.out.println("-------------------");
            // print question
            System.out.println(cQues[0] + "\n[S] Skip\t\t [W]show Answer");
            answer = cQues[1]; // keep answer
            System.out.println();
            String wrongChoices[] = new String[]{"Spain", "Venison", "Avocado", "India", "Fishing", "Almond", "Mexico", "Carbohydrates", "Corn", "Marmalade"};
            double rand = Math.random() ; 
            
            if (coff >= rand) {
                choice = answer;
            } else {
                choice = wrongChoices[(int) ((Math.random() * 7) + 1)];
            }
            if (choice.equalsIgnoreCase("c")) { // computer see the answer
                System.out.println("Answer is " + answer);
                System.out.println("The computer doesn't win a point");
                continue;
            }
            if (choice.equalsIgnoreCase("s")) { // computer skip question
                System.out.println("The computer doesn't win a point");
                continue;
            }
            if (choice.equalsIgnoreCase(answer)) { // computer answer is true
                System.out.println(choice + " - The computer got it right");
                cScore++;
            } else { // computer answer is true
                System.out.println(choice + " - The computer got it wrong");
            }
            System.out.println("");
        }
        // print challenger and computer scores
        System.out.println("Your score is " + score);
        System.out.println("The computer's score is " + cScore);
        challengers.addScore(name, score); // add challenger score
    }

    /**
     * multiPlayer method represent quiz for challenger VS challenger
     *
     * @param name String
     */
    public void multiPlayer(String name, String mod) {
        questions = new Questions(mod); // get new questions
        System.out.println("Player Number2 Login\n");
        String otherStudentName = log();
        while (otherStudentName.equals(name)) {
            System.out.println("You cannot play against yourself");
            otherStudentName = log();
        }

        System.out.println("");
        System.out.println("Hello " + otherStudentName);
        System.out.println("");
        
        System.out.println(name + " Quiz VS " + otherStudentName ); // user message
        System.out.print("Each of you will answer 10 questions");
        int score1 = 0; // variable to store challenger 1 score
        int score2 = 0; // variable to store challenger 2 score
        for (int i = 1; i <= 10; i++) { // 10 questions
            System.out.println(i+")");
            String[] ques1 = questions.getQuestion().split("!!!"); // call getQuestion method to get question
            String[] ques2 = questions.getQuestion().split("!!!"); // call getQuestion method to get question

            // challenger 1 round 
            System.out.println("");
            System.out.println("-------------------");
            System.out.println(name + " Round");
            System.out.println("-------------------");
            // print question
            System.out.println(ques1[0] + "\n[S] Skip\t\t [C]Cheat");
            String answer = ques1[1]; // keep answer
            String choice = scanner.next(); // get challenger 1 choice
            if (choice.equals("c")) { // challenger 1 see the answer
                System.out.println("The answer was " + answer);
                System.out.println(name + ", you have not won this point.");
            }
            if (choice.equals("s")) { // challenger 1 skip question
                System.out.println(name + ", you have not won this point. ");
            }
            if (choice.equalsIgnoreCase(answer)) { // challenger 1 answeris true
                System.out.println(name + " wins 1 point");
                score1++;
            } else if (!choice.equals("s") && !choice.equals("c")) { // challenger 1 answer is false
                System.out.println(name + " wins 1 point");
            }

            // challenger 2 round
            System.out.println("");
            System.out.println("-------------------");
            System.out.println(otherStudentName + " Round");
            System.out.println("-------------------");
            // print question
            System.out.println(ques2[0] + "\n[S] Skip\t\t [C]Cheat");
            answer = ques2[1]; // keep answer
            choice = scanner.next(); // get challenger 2 choice
            if (choice.equals("c")) { // challenger 2 see the answer
                System.out.println("The answer was " + answer);
                System.out.println(otherStudentName + ", you have not won this point.");
            }
            if (choice.equals("s")) { // challenger 2 skip question
                System.out.println(otherStudentName + ", you have not won this point.");
            }
            if (choice.equalsIgnoreCase(answer)) { // challenger 2 answer is true
                System.out.println(otherStudentName + " wins 1 point");
                score2++;
            } else if (!choice.equals("s") && !choice.equals("c")) { // challenger 2 answer is false
                System.out.println(otherStudentName + " wins 1 point");
            }
            System.out.println("");
        }
        // print challengers scores
        System.out.println(name + "'s score is: " + score1);
        System.out.println(otherStudentName + "'s score is: " + score2);
        challengers.addScore(name, score1); // add score to challenger1
        challengers.addScore(otherStudentName, score2); // add score to challenger 2

    }

    /**
     * mod method return mode of quiz (food or capitalized)
     *
     * @return String
     */
    private String mod() {
        // prompt user to choose
        System.out.println("Choose the quiz you want to play");
        System.out.println("F) Food");
        System.out.println("C) Capitals");
        // get user choice
        String str = scanner.next();
        while (!(str.equalsIgnoreCase("f") || str.equalsIgnoreCase("c"))) {
            System.out.println("Wrong Choice");
            System.out.println("F) Food");
            System.out.println("C) Capitals");
            str = scanner.next();
        }
        return str.equalsIgnoreCase("f") ? "a" : "b";
    }

    /**
     * showMenu method return challenger choice from this menu
     *
     * @return String
     */
    public String showMenu() {
        // print menu
        System.out.println("S for Single Player Mode");
        System.out.println("C for Single Player VS Computer Mode");
        System.out.println("M for Multichallenger Mode");
        System.out.println("L to List previous scores");
        System.out.println("Q to Quit");
        String choice = scanner.next().toLowerCase(); // get challenger choice

        // check challenger choice
        while (!choice.equals("s") && !choice.equals("c") && !choice.equals("m") && !choice.equals("q") && !choice.equals("l")) {
            System.out.println("Invalid scanner\nPlease Enter valid choice");
            choice = scanner.next().toLowerCase();
        }
        return choice; // return challenger choice
    }

    // main method to run program
    public static void main(String[] args) throws FileNotFoundException {
        Quiz quiz = new Quiz(); // declare object from Quiz
        String str;

        System.out.println("-----------------------------------------------");
        System.out.println("           WELCOME TO OUR QUIZZ                  ");
        System.out.println("-----------------------------------------------");
        System.out.println("");

        String name = quiz.log(); // call login method to get challenger name
        if (name.equals("")) {
            System.exit(0); // if challenger dont enter name exit from the program
        }
        System.out.println("");
        System.out.println("Hello " + name);
        System.out.println("");
        // get quiz mode
        String mod = quiz.mod();

        str = quiz.showMenu(); // get challenger choice 
        while (!str.equals("q")) { // termination condition
            switch (str) {
                case "s": // Single challenger choice
                    System.out.println();
                    quiz.single(name, mod);  // call method single choice
                    System.out.println();
                    break;
                case "c": // VS computer choice 
                    System.out.println();
                    quiz.singleVSComputer(name, mod); // call method single VS computer
                    System.out.println();
                    break;
                case "m": // multichallenger choice
                    System.out.println();
                    quiz.multiPlayer(name, mod); // call method multichallenger choice
                    System.out.println();
                    break;
                case "l": // list scores choice
                    System.out.println();
                    quiz.scores(name); // call method list scores
                    System.out.println();
                    break;
            }
            str = quiz.showMenu(); // rechoice
        }
    }

    public class Questions {

        /* Class attributes */
        ArrayList<String> questions;
        ArrayList<String> answers;
        String mode;

        /**
         * Constructor to initialize variables
         * @param mode
         */
        public Questions(String mode) {
            questions = new ArrayList<>();
            answers = new ArrayList<>();
            this.mode = mode;
            // call load method to get questions data from file
            load();
            // call shuffle method to rearrangment questions positions
            reArrange();
        }

        /**
         * getQuestion method return first come question and push it in last of
         * array
         *
         * @return String
         */
        public String getQuestion() {
            String removedQ = questions.remove(0);
            String removedA = answers.remove(0);
            questions.add(removedQ);
            answers.add(removedA);
            return removedQ + "!!!" + removedA;
        }

        /**
         * reArrange method rearrangment questions positions
         */
        private void reArrange() {
            int number = questions.size() * questions.size(); // shuffles number 
            for (int i = 0; i < number; i++) {
                int p1 = (int) (Math.random() * questions.size()); // get random questions index
                int p2 = (int) (Math.random() * questions.size()); // get other random questions index

                while (p2 == p1) { // if two random is the same change other p until it is different
                    p2 = (int) (Math.random() * questions.size());
                }

                // swap two questions
                String temp = questions.get(p1);
                questions.set(p1, questions.get(p2));
                questions.set(p2, temp);

                // swap two questions
                String temp1 = answers.get(p1);
                answers.set(p1, answers.get(p2));
                answers.set(p2, temp1);
            }
        }

        /**
         * load method get questions data from file
         */
        private void load() {
            Scanner scanQ = null; // declare scanner object to read file
            Scanner scanA = null;
            try {
                // if quiz mode a load food questions and answers
                if (mode.equals("a")) {
                    scanQ = new Scanner(new FileReader("foodquestions.txt")); // file name
                    scanA = new Scanner(new FileReader("foodanswers.txt"));
                }
                // if quiz mode b load capitalized questions and answers
                if (mode.equals("b")) {
                    scanQ = new Scanner(new FileReader("capitals.txt")); // file name
                    scanA = new Scanner(new FileReader("answerscapitals.txt"));
                }

                while (scanQ.hasNextLine()) { // one question per line
                    questions.add(scanQ.nextLine());
                    answers.add(scanA.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error : Questions File not Found"); // if cant open file
            }
        }
    }
}
