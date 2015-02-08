/*
	Joo-nam Kim
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hashtable.h"
#include <assert.h>

FILE* get_file(char*);

int main(int argc, char* argv []) 
{
	FILE *filename; 
	filename = get_file(argv[1]);
	char c; 
	char one_word[1000]; 
	int i;
	int j;
	int max = 0;
	int up_to_ten = 10;
	node* final_list[CAPACITY];
	
	hashtable* h = malloc(sizeof(hashtable));
	
	
	
	do {
		c = fscanf(filename, "%s", one_word);
		char* lowered = malloc (strlen(one_word) + 1);
		lowered = lower_string(one_word);
		

		
		if (one_word[0] == '#') {
			if (get(lowered, h) == 0) { // if not in hashtable already				
				put(lowered, h); 	// insert to hashtable and corresponding linked lists. 
			} 
			
		}
		
		
	} while(c != EOF);
	
	for (i = 0; i < CAPACITY; i++) { // start finding max values
		
		node* node_for_max = h->list[i];
		final_list[i] = find_max_node(node_for_max);

	}
	
	int max_location;
	for (j = 0; j < up_to_ten; j++) {

		max_location = find_max_location(final_list);
		
		printf("%s   %lu\n", final_list[max_location]->value, final_list[max_location]->count);
		final_list[max_location]->count = 0;

	}

	free(h);
	fclose(filename);

	
}

FILE* get_file(char* input)
{

	
	FILE *filename = fopen(input, "r");
	
		if (filename == NULL) { // File error handling.
			return 0;
		}
		
	return filename;
}
