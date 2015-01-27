/* 
Joo-nam Kim
*/
#include <stdio.h>
#include <string.h>
#include "lc4_memory.h"
#include "lc4_loader.h"
#include "lc4_disassembler.h"

/* program to mimic LC4's program & data memory */
int main () {

	/**
	 * main() holds the linked list &
	 * only calls functions in other files 
	 */
	

	/* step 1: create head pointer to linked list: memory 	*/
	struct row_of_memory* memory = NULL ;
	
	
	
	/* step 2: call function: get_file() in lc4_loader.c 	*/
	/*   TODO: call function & check for errors		*/
	FILE *filename;
	filename = get_file();
	if (filename == 0) {
		printf ("File Read Error.\n");
		return 1;
	}
	/* step 3: call function: parse_file() in lc4_loader.c 	*/
	/*   TODO: call function & check for errors		*/
	//parse_file(filename, &memory);
	
	if (parse_file(filename, &memory) != 0) {
		printf ("Error occured while parsing file!\n");
	}
	
	/* step 4: call function: reverse_assemble() in lc4_disassembler.c */
	/*   TODO: call function & check for errors		*/
	if (reverse_assemble(memory) != 0) {
		printf ("Error occured while reversing assembly\n");
		
	}
	// Close file and check for errors. If Error, return 2.
	fclose(filename);
	
	/* step 5: call function: print_list() in lc4_memory.c 	*/
	/*   TODO: call function 				*/
	print_list(memory);

	/* step 6: call function: delete_list() in lc4_memory.c */
	/*   TODO: call function & check for errors		*/
	if (delete_list(memory) != NULL) {
		printf ("Error occured while deleting list\n");
	}
	
	/* only return 0 if everything works properly */
	return 0 ;
}


