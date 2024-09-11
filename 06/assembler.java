import java.io.*;
import java.util.*;

public class assembler {
    public static void main(String[] args) {
        String filePath = "Max.asm";
        ArrayList<String> linesWithoutComments = new ArrayList<>();
        

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
                    // Split the line into words
                    String[] words = line.split("\\s+"); // Split by any number of whitespaces
                    String temp = "";
                    for (String word : words) {
                        temp += word;
                        // if (!word.isEmpty()) {
                        //     linesWithoutComments.add(word);
                        // }
                    }
                    linesWithoutComments.add(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        /*
         * Create Symbol Table
         */

        HashMap<String, Integer> symbolTable = new HashMap<>();
        symbolTable.put("R0", 0);
        symbolTable.put("R1", 1);
        symbolTable.put("R2", 2);
        symbolTable.put("R3", 3);
        symbolTable.put("R4", 4);
        symbolTable.put("R5", 5);
        symbolTable.put("R6", 6);
        symbolTable.put("R7", 7);
        symbolTable.put("R8", 8);
        symbolTable.put("R9", 9);
        symbolTable.put("R10", 10);
        symbolTable.put("R11", 11);
        symbolTable.put("R12", 12);
        symbolTable.put("R13", 13);
        symbolTable.put("R14", 14);
        symbolTable.put("R15", 15);
        symbolTable.put("SCREEN", 16384);
        symbolTable.put("KBD", 24576);
        symbolTable.put("SP", 0);
        symbolTable.put("LCL", 1);
        symbolTable.put("ARG", 2);
        symbolTable.put("THIS", 3);
        symbolTable.put("THAT", 4);

        int nextAvailableAddress = 16;
        for (int i = 0; i < linesWithoutComments.size(); i++) {
            String line = linesWithoutComments.get(i);
            if (line.startsWith("(")) {
                String symbol = line.substring(1, line.length() - 1);
                symbolTable.put(symbol, i);
                linesWithoutComments.remove(i);
                i--;
            }
        }
        HashMap<String, String> comp = new HashMap<>();
        HashMap<String, String> dest = new HashMap<>();
        HashMap<String, String> jump = new HashMap<>();
        comp.put("0", "0101010");
        comp.put("1", "0111111");
        comp.put("-1", "0111010");
        comp.put("D", "0001100");
        comp.put("A", "0110000");
        comp.put("!D", "0001101");
        comp.put("!A", "0110001");
        comp.put("-D", "0001111");
        comp.put("-A", "0110011");
        comp.put("D+1", "0011111");
        comp.put("A+1", "0110111");
        comp.put("D-1", "0001110");
        comp.put("A-1", "0110010");
        comp.put("D+A", "0000010");
        comp.put("D-A", "0010011");
        comp.put("A-D", "0000111");
        comp.put("D&A", "0000000");
        comp.put("D|A", "0010101");
        comp.put("M", "1110000");
        comp.put("!M", "1110001");
        comp.put("-M", "1110011");
        comp.put("M+1", "1110111");
        comp.put("M-1", "1110010");
        comp.put("D+M", "1000010");
        comp.put("D-M", "1010011");
        comp.put("M-D", "1000111");
        comp.put("D&M", "1000000");
        comp.put("D|M", "1010101");
        dest.put("null", "000");
        dest.put("M", "001");
        dest.put("D", "010");
        dest.put("MD", "011");
        dest.put("A", "100");
        dest.put("AM", "101");
        dest.put("AD", "110");
        dest.put("AMD", "111");
        jump.put("null", "000");
        jump.put("JGT", "001");
        jump.put("JEQ", "010");
        jump.put("JGE", "011");
        jump.put("JLT", "100");
        jump.put("JNE", "101");
        jump.put("JLE", "110");
        jump.put("JMP", "111");
        
        ArrayList<String> binary = new ArrayList<>();

        for (int i = 0; i < linesWithoutComments.size(); i++) {
            String line = linesWithoutComments.get(i);
            if (line.startsWith("@")) {
                String symbol = line.substring(1);
                // Check if symbol is a number
                if (symbol.matches("[0-9]+")) {
                    int value = Integer.parseInt(symbol);
                    String binaryValue = Integer.toBinaryString(value);
                    binaryValue = "0".repeat(16 - binaryValue.length()) + binaryValue;
                    binary.add(binaryValue);
                } else {
                    if (symbolTable.containsKey(symbol)) {
                        int value = symbolTable.get(symbol);
                        String binaryValue = Integer.toBinaryString(value);
                        binaryValue = "0".repeat(16 - binaryValue.length()) + binaryValue;
                        binary.add(binaryValue);
                    } else {
                        symbolTable.put(symbol, nextAvailableAddress);
                        nextAvailableAddress++;
                        int value = symbolTable.get(symbol);
                        String binaryValue = Integer.toBinaryString(value);
                        binaryValue = "0".repeat(16 - binaryValue.length()) + binaryValue;
                        binary.add(binaryValue);
                    }
                }
            } else {
                String[] parts = line.split("=");
                String destPart = parts.length == 1 ? "null" : parts[0];
                String[] parts2 = parts[parts.length - 1].split(";");
                String compPart = parts2[0];
                String jumpPart = parts2.length == 1 ? "null" : parts2[1];
                String destBinary = dest.get(destPart);
                String compBinary = comp.get(compPart);
                String jumpBinary = jump.get(jumpPart);
                String binaryValue = "111" + compBinary + destBinary + jumpBinary;
                binary.add(binaryValue);
            }
        }


        // Output the processed lines
        for (String word : binary) {
            System.out.println(word);
        }
    }
}