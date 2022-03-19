; HW 11
; Montana Singer

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	val1 sword 1
	val2 sword 2
	val3 sword 3

.code
main proc
	;-val2 + 7 - val3 + val1

	mov Bx, val2	; Move val2 (2) to eax
	neg Bx			; Negate eax, becomes -2
	add Bx, 7		; Add 7
	sub Bx, val3	; Subtract val3 (3)
	add Bx, val1	; Add val1 (1)
	
	;The value is stored in Bx, in this case it is 3

	;If Bx is positive, store its negative value in Cx

	cmp Bx, 0			; Compare Bx (3) to 0 (checking if positive)
	jle NextInstruction	; If condition is not met (Bx is negative, jump)
	mov Cx, Bx			; Store Bx in Cx
	neg Cx				; Store negative value of Bx in Cx
	jmp Next			

	NextInstruction:
		mov Cx, Bx		; Bx was already negative, so store in Cx
		
	Next:
		;If Cx <= -100, multiply by 10
		cmp Cx, -100	; Compare Cx to -100
		
		jg ValueIsGreaterThan	; Value is greater than -100

		imul Cx, 10			; Multiply by 10

		ValueIsGreaterThan:


	invoke ExitProcess,0
main endp
end main