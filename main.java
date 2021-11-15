import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.*;
import java.lang.*;
import java.io.*;

public class main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String name = "";
        String[] greetings = {"Hi, what's your name?", "Hey there, what's your name?"};
        String[] clarifications = {"Could you repeat?", "Could you rephrase that?", "Could you please say 'my name is: ....'"};
        Map<String, String> teams = new HashMap<>();
        teams.put("patriots", "6-3");
        ArrayList rankings = new ArrayList<String>();
        Scanner rankingsScanner = new Scanner(new File("rankings.txt"));
        while (rankingsScanner.hasNextLine()) {
          rankings.add(rankingsScanner.nextLine());
        }
        //System.out.println(String.join(", ", rankings));
        randomResponse(greetings);
        //boolean conversation = true;
        outer: while (true) {
            String resp = sc.nextLine();
            //System.out.println(resp);
            //String res = resp.toLowerCase();
            String[] arg = resp.split(" ");
            for (int i = 0; i < arg.length; i++) {
                if (teams.containsKey(arg[i])) {
                    System.out.println(teams.get(arg[i]));
                    continue outer;
                }
            }
            


            if (resp.toLowerCase().contains("name is ")) {
                System.out.print("Hi, ");
                name = resp.substring(resp.lastIndexOf("name is ")+8, resp.length());
                System.out.println(name + ", nice to meet you!");
                resp = sc.nextLine();
                
                boolean flag = true;

                while (flag){
                 System.out.println("What do you need to know about fantasy football rankings?");
                 resp = sc.nextLine();
                 for (int i = rankings.size()-1; i >= 0; i--){
                    if (resp.contains(Integer.toString(i+1)) && resp.toLowerCase().contains("player")){
                     System.out.println(rankings.get(i));
                     break;
                     }
                  }

                  System.out.println("Type 1 to continue and 0 to stop");
                  if (sc.nextLine().equals("0")) {
                    break outer;
                  } else continue;
                  
                }
    
            } else if (resp.toLowerCase().contains("goodbye")) {
                System.out.println("Goodbye");
                break;

            } else {
                randomResponse(clarifications);
            }
        }
        System.out.println("End of conversation");

        sc.close();
    }

    public static void randomResponse(String[] responses) {
        System.out.println(responses[new Random().nextInt(responses.length)]);
    }


}