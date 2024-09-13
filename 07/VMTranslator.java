
import java.io.*;
import java.util.ArrayList;

public class VMTranslator {
    public static void main(String[] args) {
        String file = "BasicTest";
        String filePath = file + "/" + file + ".vm";
        // filePath = "StackTest/StackDebug.vm";
        ArrayList<String> linesWithoutComments = new ArrayList<>();
        String finalOutput = "";
        /*
         * Read the file line by line and remove comments
         */
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("//")) {
                    line = line.substring(0, line.indexOf("//"));
                }
                
                line = line.trim();
                if (!line.isEmpty()) {
                    linesWithoutComments.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /*
         * Parse the lines and generate the output
         */
        int count = 0;
        for (String line2 : linesWithoutComments) {
            String[] words = line2.split("\\s+");
            String ans = new String(""); // Split by any number of whitespaces
            if (words[0].equals("push")) {
                System.out.println("push " + words[1] + " " + words[2]);
                if (words[1].equals("constant")) {
                    ans += "@" + words[2] + "\n";
                    ans += "D=A\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                    System.out.println(ans);
                } else if (words[1].equals("local")) {
                    ans += "@LCL\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "A=D\n";
                    ans += "D=M\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                    System.out.println(ans);
                } else if (words[1].equals("argument")) {
                    ans += "@ARG\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "A=D\n";
                    ans += "D=M\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                } else if (words[1].equals("this")) {
                    ans += "@THIS\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "A=D\n";
                    ans += "D=M\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                } else if (words[1].equals("that")) {
                    ans += "@THAT\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "A=D\n";
                    ans += "D=M\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                } else if (words[1].equals("temp")) {
                    ans += "@5\n";
                    ans += "D=A\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "A=D\n";
                    ans += "D=M\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                    System.out.println(ans);
                } else if (words[1].equals("pointer")) {
                    ans += "@3\n";
                    ans += "D=A\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "A=D\n";
                    ans += "D=M\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                    System.out.println(ans);
                } else if (words[1].equals("static")) {
                    ans += "@static." + words[2] + "\n";
                    ans += "D=M\n";
                    ans += "@SP\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "M=M+1\n";
                    System.out.println(ans);
                } 
            } else if (words[0].equals("pop")) {
                System.out.println("pop " + words[1] + " " + words[2]);
                if (words[1].equals("local")) {
                    ans += "@LCL\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n"; // Get the address
                    ans += "@R13\n";
                    ans += "M=D\n"; // Store the address in R13
                    ans += "@SP\n";
                    ans += "AM=M-1\n"; // Decrement SP
                    ans += "D=M\n"; // Get the value
                    ans += "@R13\n"; // Get the address
                    ans += "A=M\n"; // Go to the address
                    ans += "M=D\n"; // Store the value
                    System.out.println(ans);
                } else if (words[1].equals("argument")) {
                    ans += "@ARG\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "@R13\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "AM=M-1\n";
                    ans += "D=M\n";
                    ans += "@R13\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    System.out.println(ans);
                } else if (words[1].equals("this")) {
                    ans += "@THIS\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "@R13\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "AM=M-1\n";
                    ans += "D=M\n";
                    ans += "@R13\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    System.out.println(ans);
                } else if (words[1].equals("that")) {
                    ans += "@THAT\n";
                    ans += "D=M\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "@R13\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "AM=M-1\n";
                    ans += "D=M\n";
                    ans += "@R13\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    System.out.println(ans);
                } else if (words[1].equals("temp")) {
                    ans += "@5\n";
                    ans += "D=A\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "@R13\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "AM=M-1\n";
                    ans += "D=M\n";
                    ans += "@R13\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    System.out.println(ans);
                } else if (words[1].equals("pointer")) {
                    ans += "@3\n";
                    ans += "D=A\n";
                    ans += "@" + words[2] + "\n";
                    ans += "D=D+A\n";
                    ans += "@R13\n";
                    ans += "M=D\n";
                    ans += "@SP\n";
                    ans += "AM=M-1\n";
                    ans += "D=M\n";
                    ans += "@R13\n";
                    ans += "A=M\n";
                    ans += "M=D\n";
                    System.out.println(ans);
                } else if (words[1].equals("static")) {
                    ans += "@SP\n";
                    ans += "AM=M-1\n";
                    ans += "D=M\n";
                    ans += "@static." + words[2] + "\n";
                    ans += "M=D\n";
                    System.out.println(ans);
                }
            } else if (words[0].equals("add")) {
                System.out.println("add");
                ans += "@SP\n";
                ans += "AM=M-1\n";
                ans += "D=M\n";
                ans += "A=A-1\n";
                ans += "M=D+M\n";
                System.out.println(ans);
            } else if (words[0].equals("sub")) {
                System.out.println("sub");
                ans += "@SP\n";
                ans += "AM=M-1\n";
                ans += "D=M\n";
                ans += "A=A-1\n";
                ans += "M=M-D\n";
                System.out.println(ans);
            } else if (words[0].equals("neg")) {
                System.out.println("neg");
                ans += "@SP\n";
                ans += "A=M-1\n";
                ans += "M=-M\n";
                System.out.println(ans);
            } else if (words[0].equals("eq")) {
                System.out.println("eq");
                ans += "@SP\n";
                ans += "AM=M-1\n";
                ans += "D=M\n";
                ans += "A=A-1\n";
                ans += "D=M-D\n"; // D = x - y
                ans += "@TRUE" + count + "\n"; // If x == y, jump to TRUE
                ans += "D;JEQ\n";
                ans += "@SP\n"; // If x != y, set M = 0
                ans += "A=M-1\n";
                ans += "M=0\n";
                ans += "@CONTINUE" + count + "\n";
                ans += "0;JMP\n";
                ans += "(TRUE" + count + ")\n";
                ans += "@SP\n";
                ans += "A=M-1\n";
                ans += "M=-1\n"; // If x == y, set M = -1 (True)
                ans += "(CONTINUE" + count + ")\n";
                count++;
                System.out.println(ans);
            } else if (words[0].equals("gt")) {
                System.out.println("gt");
                ans += "@SP\n";
                ans += "AM=M-1\n";
                ans += "D=M\n";
                ans += "A=A-1\n";
                ans += "D=M-D\n"; // D = x - y
                ans += "@TRUE" + count + "\n"; // If x > y, jump to TRUE
                ans += "D;JGT\n";
                ans += "@SP\n"; // If x <= y, set M = 0
                ans += "A=M-1\n";
                ans += "M=0\n";
                ans += "@CONTINUE" + count + "\n";
                ans += "0;JMP\n";
                ans += "(TRUE" + count + ")\n";
                ans += "@SP\n";
                ans += "A=M-1\n";
                ans += "M=-1\n"; // If x > y, set M = -1 (True)
                ans += "(CONTINUE" + count + ")\n";
                count++;
                System.out.println(ans);
            } else if (words[0].equals("lt")) {
                System.out.println("lt");
                ans += "@SP\n";
                ans += "AM=M-1\n";
                ans += "D=M\n";
                ans += "A=A-1\n";
                ans += "D=M-D\n"; // D = x - y
                ans += "@TRUE" + count + "\n"; // If x < y, jump to TRUE
                ans += "D;JLT\n";
                ans += "@SP\n"; // If x >= y, set M = 0
                ans += "A=M-1\n";
                ans += "M=0\n";
                ans += "@CONTINUE" + count + "\n";
                ans += "0;JMP\n";
                ans += "(TRUE" + count + ")\n";
                ans += "@SP\n";
                ans += "A=M-1\n";
                ans += "M=-1\n"; // If x < y, set M = -1 (True)
                ans += "(CONTINUE" + count + ")\n";
                count++;
                System.out.println(ans);
            } else if (words[0].equals("and")) {
                System.out.println("and");
                ans += "@SP\n";
                ans += "AM=M-1\n";
                ans += "D=M\n";
                ans += "A=A-1\n";
                ans += "M=D&M\n";
                System.out.println(ans);
            } else if (words[0].equals("or")) {
                System.out.println("or");
                ans += "@SP\n";
                ans += "AM=M-1\n";
                ans += "D=M\n";
                ans += "A=A-1\n";
                ans += "M=D|M\n";
                System.out.println(ans);
            } else if (words[0].equals("not")) {
                System.out.println("not");
                ans += "@SP\n";
                ans += "A=M-1\n";
                ans += "M=!M\n";
                System.out.println(ans);
            } 
            // Write ans to the output file
            System.out.print(ans);
            finalOutput += ans;
        }
        // Write the final output to a file
        try {
            FileWriter myWriter = new FileWriter(file + ".asm");
            myWriter.write(finalOutput);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
