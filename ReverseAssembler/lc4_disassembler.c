/* 
Joo-nam Kim
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "lc4_memory.h"

int getBitsByShift(int decNum,int shift);
void addAssemblyToList(char* whichOne, struct row_of_memory* opc);
void addImmediateToList(struct row_of_memory* opc);
int getImmediateTwosComplement(int contents);

int reverse_assemble (struct row_of_memory* memory) 
{
	/*variable declarations*/
	int subOpcode;
	int addIMM;
	int immOrNot;
	int sixTeenDigits;
	struct row_of_memory* opc = search_opcode(memory, 0001);
	
	/*check for correct allocation*/
	if (opc == NULL) {
		printf("Allocation failed.\n");
		return 0;
	}
	
	struct row_of_memory* current = opc; 
	
	addIMM = (opc -> contents)&(0x1f);
	
	while (current != NULL) {
		
		subOpcode = getBitsByShift(current -> contents, 3); // use helper functions to do the work. 
		
		switch(subOpcode) {

			case 0:
				addAssemblyToList("ADD ", current);
				break;
			case 1:
				addAssemblyToList("MUL ", current);
				break;
		
			case 2:
				addAssemblyToList("SUB ", current);
				break;
			case 3:
				addAssemblyToList("DIV ", current);
				break;
			default :
				//Get the 5th bit to check whether it's an immediate ADD. 
				immOrNot = ((current -> contents >> 5) & 0x1);
				if (immOrNot == 1 && (current -> contents) > 0x1000) { //If Immediate AND starts with 0b0001
					
					addImmediateToList(current);
				}
				break;
			}
			current = current -> next;
	}
	
	return 0 ;
}

void addImmediateToList(struct row_of_memory* opc) {
	
	// Check if within the legal boundary.
	if (opc -> contents >= 0x1000 && opc -> contents <= 0x1fff) {
		int destReg = getBitsByShift(opc -> contents, 9); // get destination register
		int srcReg = getBitsByShift(opc -> contents, 6);  // get source register
		int addIMM = (opc -> contents >> 0)&(0xf);		  // get immediate value
		int immOrNot = ((opc -> contents >> 4) & 0x1);	  // check for immediate
		char* instruction = (char*)malloc(sizeof(char) * 18);
		
		if (immOrNot == 1) {
			addIMM = getImmediateTwosComplement(opc -> contents); //get the last 4 bits. 
			sprintf(instruction, "ADD R%d, R%d, #%d", destReg, srcReg, addIMM); // make a string.
		}
		if (immOrNot == 0) {
		
			sprintf(instruction, "ADD R%d, R%d, #%d", destReg, srcReg, addIMM);
		}
		opc -> assembly = malloc(sizeof(char) * 18);
		strcpy(opc -> assembly, instruction);
		free(instruction);
	}
	
	
}
//helper function to get an immediate value
int getImmediateTwosComplement(int contents) {
	
	contents = contents & 0x1f; //get the necessary content by bit masking. 
	if (contents == 31) { 
		return -1; 
	}
	return contents;
}

//add assembly to list helper function.
void addAssemblyToList(char* whichOne, struct row_of_memory* opc) {
	
	if (opc -> contents >= 0x1000 && opc -> contents <= 0x1fff) {
		int destReg = getBitsByShift(opc -> contents, 9);
		int srcReg = getBitsByShift(opc -> contents, 6);
		int srcRegTwo = getBitsByShift(opc -> contents, 0);
		int subOpcode = getBitsByShift(opc -> contents, 3);
		char* instruction = (char*) malloc (sizeof(char) * 18);
		
		sprintf(instruction, "%sR%d, R%d, R%d",whichOne, destReg, srcReg, srcRegTwo);
		opc -> assembly = malloc(strlen(instruction) + 1);
		strcpy(opc -> assembly, instruction);
		free(instruction);
	}
}


int getBitsByShift(int decNum,int shift) {
	
	return (decNum >> shift)&(0x7);
	
}
