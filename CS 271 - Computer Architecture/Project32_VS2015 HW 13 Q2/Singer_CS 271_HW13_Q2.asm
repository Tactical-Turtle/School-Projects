; HW 13 Q2
; Linear search in a given array
; Montana Singer

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
  arrA DWORD 5, -9, 10, 7, 6, -3, 1, 2

.code

main PROC

    mov edi, OFFSET arrA      
    mov eax, 0    
	mov ebx, 10		; Value to be searched for
	mov edx, 0		; Counter for index

    mov ecx, LENGTHOF arrA 

    L1:
		inc edx
        mov eax, [edi]      ; Move current arrA element to eax

		cmp eax, ebx		; If arrA element = search value, exit loop
			je Next

        add edi, TYPE arrA  ; Next arrA element

        loop L1

	Next:
		cmp ecx, 0
			jle NotFound

		mov eax, edx		; Target found, load index of target
		jmp ProgramDone
	
	NotFound:
		mov eax, -1			; Target not found, load -1

	ProgramDone:


	invoke ExitProcess,0
main endp
end main 