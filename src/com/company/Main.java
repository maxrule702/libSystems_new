package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {


    private static File bookfile = new File("bookList.txt");
    private static File LoginCheck = new File("LoginFile.txt");
    private static Object Globalusername = "";


    public static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void CreateFile() {
        try {
            if (bookfile.createNewFile()) {
                LoginCheck.createNewFile();
                System.out.println("File created: " + bookfile.getName());
                System.out.println("File created: " + LoginCheck.getName());
            } else {
                System.out.println("Files already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void LoginCheck() {

        System.out.println("would you like to login or register?");
        Scanner input = new Scanner(System.in);
        while (true) {
            String userinput = input.nextLine();
            if (userinput.equals("login")) {
                Login();
                break;
            }
            if (userinput.equals("register")) {
                register();
                Login();
                break;
            }
            if (!userinput.equals("login") || (!userinput.equals("register"))) {
                System.out.println("please check your spelling");
            }
        }
    }

    public static void Login() {
        File file = new File("LoginFile.txt");
        int check = 0;
        int allLinesCheck = 0;
        try {
            while (check != 1) {
                Scanner scanner = new Scanner(file);
                int lineNum = 0;
                String userSearch = getInput("please enter your username , if you see this message more than once youve enter your details incorrectly");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    lineNum++;
                    allLinesCheck++;
                    if (line.contains(userSearch)) {
                        System.out.println("welcome back " + userSearch);

                        String passSearch = getInput("please enter your password");
                        String passwordline = Files.readAllLines(Paths.get("LoginFile.txt")).get(lineNum);
                        if (passSearch.equalsIgnoreCase(passwordline)) {
                            System.out.println("correct");
                            check++;
                            break;
                        }


                    } else {
                        if (allLinesCheck > lineNum) {
                            System.out.println("username or password not found");

                        }
                    }
                }
            }
            mainMenu();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void register() {
        String username = getInput("Enter your name");
        String password = getInput("please enter your password");
        File file = new File("LoginFile.txt");
        String userName = (username);
        FileWriter writer = null;
        try {
            writer = new FileWriter("LoginFile.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            writer.write(userName);
            writer.write("\n");
            writer.write(password);
            writer.write("\n");
            writer.close();
            System.out.println("user has been successfully created welcome " + username);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String WritingToFile() throws IOException {
        String bookTitle = getInput("Enter your book's name");
        int ISBN = Integer.parseInt(getInput("ISBN"));
        String Author = getInput("Enter your Author's name");
        String genre = getInput("Enter your genre type");
        String info = (bookTitle + "," + ISBN + "," + Author + "," + genre);
        FileWriter writer = new FileWriter("bookList.txt", true);
        BufferedWriter bw = new BufferedWriter(writer);
        writer.write(info);
        writer.write("\n");
        writer.close();
        return info;
    }

    public static String ReadingFromFile() throws IOException {
        String data = null;
        try {
            File myObj = new File("bookList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    public static String searchingFile() throws IOException {
        Scanner in = null;
        try {
            String userSearch = getInput("Enter your search");
            File file = new File("bookList.txt");
            in = new Scanner(file);
            while (in.hasNext()) {
                String line = in.nextLine();
                if (line.contains(userSearch))
                    System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String Options() throws IOException {
        Scanner input = new Scanner(System.in);
       int loop =0;
        try {
            while (loop != 1) {
                System.out.println("would you like to chose another function [1, = yes] [2 = no]");
                int userinput = input.nextInt();
                if (userinput == 1) {
                    loop++;
                    mainMenu();
                }
                if (userinput == 2) {
                    System.out.println("goodbye");
                    loop++;
                } else {
                    System.out.println("invalid input try again");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String mainMenu() throws IOException {
        while (true) {
            int MenuOptions = Integer.parseInt(getInput("what option would you like to chose 1. inputting new book 2. viewing all available books 3. searching for a specific book"));
            if (MenuOptions == 1) {
                WritingToFile();
                Options();
                break;
            }
            if (MenuOptions == 2) {
                ReadingFromFile();
                Options();
                break;
            }
            if (MenuOptions == 3) {
                searchingFile();
                Options();
                break;
            } else {
                System.out.println("invalid option try again");
            }

        }
        return null;
    }








    public static void main (String[]args) throws IOException {

        //CreateFile();
        LoginCheck();
        //WritingToFile();
        //ReadingFromFile();
        //searchingFile();

    }
}






