/* 
Joo-nam Kim
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lc4_memory.h"
short unsigned int returnTwoBytes(FILE * file);
void addToLinkedList(FILE * file, struct row_of_memory** memory);
struct row_of_memory* add_to_list_end    (struct row_of_memory* head, 
				      short unsigned int address, 
				      short unsigned int contents);
/* declarations of functions that must defined in lc4_loader.c */

FILE* get_file()
{
	char input[123]; 
	printf("Please enter the name of the file you would like to convert: ");
	scanf("%s", input);
	
	FILE *filename = fopen(input, "rb");
	
		if (filename == NULL) { // File error handling.
			return 0;
		}
		
	return filename;
}

int parse_file (FILE* my_obj_file, struct row_of_memory** memory)
{
	
	/*variable declarations*/
	FILE *filename = my_obj_file; 
	short unsigned int address;
	short unsigned int n;
	int first;
	int second;
	unsigned int combined; 
	int i; 
	char *final_label;
	struct row_of_memory* node =  *memory;
	struct row_of_memory* current = NULL; 
	
	
	do {
		
		first = fgetc(filename);
		if(first == EOF) break;
		
		second = fgetc(filename);
		if(second == EOF) break;

		combined = (first << 8) | second; // combine first & second to make a content to test with.
		
		
		if (combined == 0xcade) { //Code header
			
			addToLinkedList(filename, memory); // add to linked list
			
		}
		else if (combined == 0xdada) { // Data header
			
			
			addToLinkedList(filename, memory);
		}

		else if (combined == 0xc3b7) { // Label
			
			address = returnTwoBytes(filename);
			n = returnTwoBytes(filename) ;
			node = search_address(*memory, address);
			
			if (node == NULL) { // If no address exists, create one and add to the end of the linked list. 
				
				//node = add_to_list_end(*memory, address, 0);
				*memory = add_to_list(*memory, address, 0);
				node = search_address(*memory, address);
			
			}
			final_label = (char*) malloc (n + 1); // allocate memory to hold a label. 
												  // n = length of the string + 1
			*final_label = '\0'; 
			
			for (i = 0; i < n; i++) { // Get each byte and concatenate to final_label
				
				first = fgetc(filename);	
				char temp = (char) first;
				strcat(final_label, &temp);
				
			}
			node -> label = malloc(n * sizeof(char) + 1); // Allocate memory to hold a label
			strcpy(node -> label, final_label);			  // Copy string into label node. 
			free(final_label);
		}
	} while (1);
		
	return 0 ;
}

/*return the first two bytes given a file*/
short unsigned int returnTwoBytes(FILE * file) {
	int first; 
	int second;
	first = fgetc(file);
	second = fgetc(file);
	return (first << 8) | second;
}

/*Add to LinkedList helper file*/
void addToLinkedList(FILE * file, struct row_of_memory** memory) {
	
	int i; 
	unsigned int combined;
	short unsigned int address;
	short unsigned int n; 
	unsigned int firstContent; 
	
	address = returnTwoBytes(file);
	n = returnTwoBytes(file);
	firstContent = returnTwoBytes(file);
	
	//Add the first content and get the pointer of the list. 
	*memory = add_to_list(*memory, address, firstContent);
	address += 1; // Increment address before going into the loop.
	
	//Using 'n', add the contents to linked list.  
	for (i = 1; i < n; i++) {
		combined = returnTwoBytes(file);
		add_to_list(*memory, address, combined);
		address += 1;
	}
}

