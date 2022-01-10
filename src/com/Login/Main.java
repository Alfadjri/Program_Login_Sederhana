package com.Login;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
public class Main {
   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String option;
        String keluar=" ";
        String databaseinput[];
        do{
            menu();
            option = scan.next();
            switch(option){
                case "1" : 
                    tampialnSingIn();
                break;
                case "2" :
                    databaseinput = tampilanSingUp();
                    create(databaseinput);
                    tampialnSingIn();
                break;
                case "q" :
                    keluar = "q";
                break;
            }
        }while(keluar == "q");
        
    }
    static void sukses_login(){
        System.out.println("================================");
        System.out.println("|    mission accomplished      |");
        System.out.println("================================");
    }
    static void tampialnSingIn(){
        ClearConsole();
        Scanner scan = new Scanner(System.in);
        String user,password;
        System.out.println("================================");
        System.out.println("|             Sing In          |");
        System.out.print  ("| Username/Email : ");
        user = scan.nextLine();
        System.out.print  ("| Password       : ");
        password = scan.nextLine();
        System.out.println("================================");
        String[] data = {user,password};
        login(data);
    }

    static String[] tampilanSingUp(){
        ClearConsole();
        Scanner scan = new Scanner(System.in);
        String username,email,password;
        System.out.println("================================");
        System.out.println("|             Sing Up           ");
        System.out.print  ("| username : ");
        username = scan.nextLine();
        System.out.print  ("| email    : ");
        email = scan.nextLine();
        System.out.print  ("| Password : ");
        password = scan.nextLine();
        System.out.println("================================");
        String[] data = {username,email,password};
        return data;
    }
    static void menu(){
        ClearConsole();
        System.out.println("================================");
        System.out.println("|            Menu              |");
        System.out.println("| 1. Login                     |");
        System.out.println("| 2. Register                  |");
        System.out.println("| q. quit                      |");
        System.out.println("================================");
    }

    private static void create(String[] input){
        try(FileWriter fw = new FileWriter("myfile.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            for(int i = 0 ; i <3 ; i ++){
                out.println(input[i]);
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    protected static void login(String[] data ){
        String everything = "" ;
        try {
            BufferedReader br = new BufferedReader(new FileReader("myfile.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything= sb.toString();
            
            br.close();
        }catch(Exception e){

        }
        String[] split = everything.split("\n");
        if(split[0].equals(data[0])){
            if(split[2].equals(data[1])){
                sukses_login();
            }else{
                tampialnSingIn();
            }
        }else if ( split[1].equals(data[0])){
            if(split[2].equals(data[1])){
                sukses_login();
            }else{
                tampialnSingIn();
            }
        }else{
            tampialnSingIn();
        }
    }
}


