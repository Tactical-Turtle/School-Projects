; HW 12
;Sum of Fibonacci using a loop
; Montana Singer

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data

.code
main proc

    mov esi, 1
    mov ebx, 0
    mov edx, 1
    mov ecx, 7
	mov eax, 0

Fib: cmp ecx, 0
	jle next
    mov esi, ebx
    add esi, edx
    mov ebx, edx
	add eax, edx
    mov edx, esi
	dec ecx
	jmp Fib

Next:
    


	invoke ExitProcess,0
main endp
end main 