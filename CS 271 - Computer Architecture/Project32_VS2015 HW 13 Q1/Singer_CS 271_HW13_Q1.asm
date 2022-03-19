; HW 13 Q1
; Sum elements in 2 arrays and store them in another array
; Montana Singer

INCLUDE Irvine32.inc

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
  arrA DWORD 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
  arrB DWORD 7, 8, 6, 2, 4, 9, 1, 3, 5, 10
  arrC DWORD LENGTHOF arrB dup(0)

  toOut DB 64 dup(?)

.code

main PROC

    mov edi, OFFSET arrA    
    mov esi, OFFSET arrB    
    mov ebx, Offset arrC    
    mov eax, 0              

    mov ecx, LENGTHOF arrA 

    L1:
        mov eax, [edi]      ; Move current arrA element to eax
        add eax, [esi]      ; Add current arrB element to eax

        add edi, TYPE arrA  ; Next arrA element
        add esi, TYPE arrB  ; Next arrB element

        mov [ebx], eax      ; Move current eax value to current arrC element
        add ebx, TYPE arrC  ; Next arrC element

        loop L1

    mov esi, OFFSET arrC
    mov ecx, 4
    mov ebx, 4
	call DumpMem

	invoke ExitProcess,0
main endp
end main 