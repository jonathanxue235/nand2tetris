// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.

//// Replace this comment with your code.

(LOOP)
@i
M=0 // i = 0
@SCREEN
D=A 
@addr
M=D // addr = SCREEN
@KBD
D=M
@SET0
D;JEQ // if KBD = 0, jump SET0
@SET1 // JMP SET1
0;JMP

(SET1)
@val
M=-1 // val = -1
@SCREENLOOP
0;JMP

(SET0)
@val
M=0 // val = 0
@SCREENLOOP
0;JMP

(SCREENLOOP)
@i
D=M
@8192
D=D-A // i -= 8192
@LOOP
D;JEQ // if i = 8192: JMP LOOP

@val
D=M
@addr
A=M
//M=D // RAM[addr] = val
M=D
@i
M=M+1 // i = i + 1
@addr
M=M+1
@SCREENLOOP
0;JMP


(END)
@END
0;JMP