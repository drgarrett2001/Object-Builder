//Object Maker
// made by David Garrett
import java.util.*;

public class ObjectMaker {
    public static void main(String[] args){
        //region initialization
        int objects;
        int states;
        int scanType = 0;
        String name;
        String input;
        String filePath = null;
        Scanner in = new Scanner(System.in);
        String[][] varAry;
        //endregion

        //region object name
        System.out.println("What is the name of the program?");
        String classname = in.nextLine();
        //endregion

        //region scanner prompts
        System.out.println("Are you using a scanner? (y/n)");
        input = in.nextLine();
        if (input.matches("y")){
            scanType++;
            System.out.println("Does your program take in console input(1) or a file(2)?");
            input=in.nextLine();
            if (input.matches("2")) {
                scanType++;
                System.out.println("Is the file from a file path(1) or a command line argument(2)?");
                input=in.nextLine();
                if(input.matches("1")){
                    System.out.println("What is the file path?");
                    filePath = in.nextLine();
                }
                else if (input.matches("2")){
                    filePath="args[0]";
                }
            }
        }
        //endregion scanner prompts

        //region object prompts
        NewStack<String[][]> obj = new NewStack<>();
        System.out.println("How many objects?");
        input = in.nextLine();
        objects = Integer.parseInt(input);

        for(int i = 0; i < objects; i++){
            System.out.println("What is the name of object #" + (i+1) + "?");
            input = in.nextLine();
            System.out.println("How many states are there for object " + input + "?");
            states = Integer.parseInt(in.nextLine());

            varAry= new String[4][states+1];
            varAry[0][states] = input;

            for( int j = 0; j < states; j++){
                System.out.println("What is the name of state #" + (j+1) + "?");
                varAry[0][j] = in.nextLine();
                varAry[1][j] = varAry[0][j].substring(0,1).toUpperCase() + varAry[0][j].substring(1);
                System.out.println("What is the data type of " + varAry[0][j] + "?");
                varAry[2][j] = in.nextLine();
                System.out.println("What is the initial value of " + varAry[0][j] + "?");
                varAry[3][j] = in.nextLine();
            }//end J loop
            obj.push(varAry);
        } //end I loop
        //endregion object prompts

        //region program class

        //region header & imports
        System.out.println();
        System.out.println("import java.util.*;");
        System.out.println("import java.io.*;");
        //main class
        System.out.println("public class "+ classname + " {");
        System.out.print(" \tpublic static void main(String[] args)");
        if ( 2 == scanType)
            System.out.print(" throws IOException ");
        System.out.println("{\n");
        //endregion

        //region instantiations & scanner
        System.out.println("\t\t//instantiations");
        if(1==scanType) {  //scanner reads from console
            System.out.println(" \tScanner in = new Scanner(System.in);");
            System.out.println("\n\n\n");
        }
        else if(2==scanType){ //scanner uses a file
            System.out.println("\t\tFile fileIn = new File(\""+ filePath + "\");");
            System.out.println(" \tScanner file = new Scanner(fileIn);");
            System.out.println("\t\ttry { // begin code here");
            System.out.println("\n\t\t\t\n\t\t\t\n\t\t\t");
            System.out.println("\t\t} catch (Exception e) {\n\t\t\te.printStackTrace();\n\t\t}//end try and exception statement");
        }
        //endregion

        //region end of main
        System.out.println("\t } //end main");
        System.out.println("} //end " + classname);
        //endregion end of main

        //endregion program class

        System.out.println("\n\n");

        //region Print Objects
        for(int i = 0; i< objects; i++){
            //initialization of state
            varAry = obj.pop();
            states = varAry[0].length-1;
            name = varAry[0][states];

            System.out.println("} //region "+ name);
            System.out.println("class " + name + " {");

            //region initialization
            System.out.println("\t//region initialization");
            for(int j = 0; j < states; j++){
                    System.out.print("\t private " + varAry[2][j] + " " + varAry[0][j]);
                    if(!(varAry[3][j].equals("") || varAry[3][j]==null))
                        System.out.print(" = " + varAry[3][j]);
                    System.out.println(";");
            }
            System.out.println("\t//endregion initialization");
            System.out.println();
            //endregion initialization

            //region constructors
            //empty constructor
            System.out.println("\t//region constructors");
            System.out.println("\t public " + name + "() {}//emptyConstructor");
            System.out.println();

            //full constructor
            System.out.print("\t public " + name +"(" );
            for(int j = 0; j < states; j++){
                if(varAry[0][j] != null){
                    if(j>0){
                        System.out.print(", ");
                    }
                    System.out.print(varAry[2][j] + " " + varAry[0][j]);
                }
            }
            System.out.print(") { //full constructor");
            //body
            for(int j = 0; j < states; j++){
                if(varAry[0][j] != null){
                    System.out.println("\t \t this." + varAry[0][j] + " = " + varAry[0][j] + ";");
                }
            }
            System.out.println("\t  }");
            System.out.print("\t//endregion constructors");
            //endregion constructors
            
            System.out.println();
            System.out.println();
            System.out.println();

            //region setters
            System.out.println("\t//region setters");
            for(int j = 0; j < states; j++){
                if(varAry[0][j] != null){
                    System.out.println(" \t public void set" + varAry[1][j] + "(" + varAry[2][j] + " " + varAry[0][j] + ") {");
                    System.out.println(" \t \t this." + varAry[0][j] + " = " + varAry[0][j] + ";");
                    System.out.println("\t }");
                    System.out.println();
                }
            }
            System.out.println("\t//endregion setters");
            //endregion setters

            //region getters
            System.out.println("\t//region getters");
            for(int j = 0; j < states; j++){
                if(varAry[0][j] != null){
                    System.out.println(" \t public "+ varAry[2][j] + " get"+ varAry[1][j] + "() {");
                    System.out.println(" \t \t return this." + varAry[0][j] + ";");
                    System.out.println("\t }");
                    System.out.println();
                }
            }
            System.out.println("\t//endregion getters");
            //endregion getters
            System.out.println("} //endregion "+ name);
            System.out.println();
            System.out.println();
        }//endregion Print Objects
    }//end main
}//end ObjectMaker