// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.

//// Replace this comment with your code.
// for i = 0; i < R1; i++
//     R2 = R2 + R0



@i
M=0 // i = 0

@R2
M=0 // Reset R2

(LOOP)
@i
D=M

@R1
D=D-M

@END
D;JGE   // if i >= R1, Go to END


@R0
D=M

@R2
M=D+M

@i
M=M+1

@LOOP
0;JMP



(END)
@END // Replace with final line
0;JMP


