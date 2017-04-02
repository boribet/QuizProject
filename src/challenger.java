
import java.io.*;
import java.util.*;

public class challenger {
    /* Class attributes */
    private HashMap<String, Chalenger> challengers ;

    /**
     * Constructor to initialize variables
     */
    public challenger() {
        challengers = new HashMap<>();
        // call load method to get challengers data from file
        load();
    
    }
    
    /**
     * add method set new challenger to map and database file
     * 
     * @param name String 
     * @param password String
     */
    public void add(String name, String password){
        challengers.put(name, new Chalenger(name,password,null)) ;
        // call updatedatabase method to update data file
        file();
    }
    
    /**
     * addScore method set new score to given challenger 
     * 
     * @param name String 
     * @param score int
     */
    public void addScore(String name, int score){
        challengers.get(name).addScore(score);
        // call updatedatabase method to update data file
        file();
    }
    
    /**
     * getPassword method return password of given student
     * 
     * @param name String 
     * @return String
     */
    public String getPassword(String name){
        return challengers.get(name).getPassword() ;
    }
    
    /**
     * found method return if given challenger found in map or not
     * 
     * @param name String 
     * @return boolean
     */
    public boolean found(String name){
        return challengers.containsKey(name);
    }
    
    /**
     * score method return scores of given student
     * 
     * @param name String 
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> score(String name){
        return challengers.get(name).getScores();
    }
    
    /**
     * updateDataBase method update data base file as map in this class 
     */
    private void file() {
        PrintWriter output = null; // declare printwriter object to write in file
        try{
            output = new PrintWriter(new File("Players.txt")); // file name
            Object[] keys = challengers.keySet().toArray(); // get all name of challengers from map
            for(int i = 0 ; i < keys.length ; i++){ // iterate map by keys
                String name = (String)keys[i] ; // name of challenger
                Chalenger challenger = challengers.get(name); // challenger object 
                String password = challenger.getPassword() ; // challenger password
                ArrayList<Integer> list = challenger.getScores() ; // challenger scores
                String scores = "" ; // string to list scores
                if(list != null) // if there are scores
                    for(int j = 0 ; j < list.size() ; j++)
                        scores += list.get(j) + " " ; // list scores
                output.println(name + " " + password + " " + scores); // write challenger information
            }
        }catch(FileNotFoundException e){
            System.out.println("Error: File Not Found"); // if cant open file
        }
        output.close(); // close printwriter
    }
    
    /**
     * load method copy challengers from database file to map
     */
    private void load() {
        Scanner scan = null; // declare scanner object to read file
        try {
            scan = new Scanner(new FileReader("Players.txt")); // file name 
            while (scan.hasNextLine()) { // termination condition
                String[] line = scan.nextLine().split(" ") ; // file line
                String name = line[0] ; // name 
                String password = line[1] ; // password
                ArrayList<Integer> scores = new ArrayList<>();
                for(int i = 2 ; i < line.length ; i++){ // scores
                    scores.add(Integer.parseInt(line[i]));
                }    
                challengers.put(name, new Chalenger(name, password, scores)); // add challenger object with key name
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error : Players File not Found"); // if cant open file
        }
        scan.close(); // close scanner
    }
    
    private class Chalenger {

    /* Class attributes */
    private String name;
    private String password;
    private ArrayList<Integer> scores;

    /**
     * Constructor to initialize variables
     */
    public Chalenger(String name, String password, ArrayList<Integer> scores) {
        this.name = name;
        this.password = password;
        this.scores = scores;
        this.scores = scores;
    }

    /**
     * addScore method add new score to current challenger
     *
     * @param score int
     */
    public void addScore(int score) {
        if (scores == null) { // if no scores yet 
            scores = new ArrayList<>(); // intialize array
            scores.add(score); // add first score
            scores.sort(new Comparator<Integer>() { // sort scores in ranked order
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 == o2) {
                        return 0;
                    }
                    return o1 > o2 ? 1 : -1;
                }
            });
        } else {
            scores.add(score); // add score
            scores.sort(new Comparator<Integer>(){  // sort scores in ranked order
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 == o2) {
                        return 0;
                    }
                    return o1 > o2 ? 1 : -1;
                }
            });
        }
    }

    /**
     * getName method return challenger name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * getPassword method return challenger password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * getScores method return challenger scores
     *
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getScores() {
        return scores;
    }

    /**
     * setName method set challenger name
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setPassword method set challenger password
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * setScores method set challenger scores
     *
     * @param scores ArrayList<Integer>
     */
    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }
}
}
