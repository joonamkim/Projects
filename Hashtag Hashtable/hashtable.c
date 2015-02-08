/*
	Joo-nam Kim
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hashtable.h"
#include <assert.h>

/*
 * For the given string refered to by the pointer "string", 
 * calculate the hashcode and update the variable pointed to by "value".
 * 
 * Return 1 if successful, and 0 if an error occurred 
 * (e.g. if the string is null).
 */
int hash(char* string, unsigned long* value)
{
	unsigned long hash = 0; 
	
	if(string == NULL || value == NULL) return 0; 
	
	while (*string != '\0') {
		
		hash += *string;
		
		string++; 
		
	}
	*value = hash;
	return 1; 
}
/*
 * Given a list of node pointers, find the node that has the maximum value.
 * return the location of the found node.
 */
int find_max_location(node* list[]) {
	
	int i;
	int j;
	unsigned long max = 0;
	int location = 0;
	
	for (i = 0; i < CAPACITY; i++) { // 1) Find the max value in the node list.
		if (max < list[i] -> count) {
			max = list[i] -> count;
		}
	}
	
	for (j = 0; j < CAPACITY; j++) { // 2) Find its location.
		if (max == list[j] -> count) {
			location = j;
		}
	}
	
	return location;
	
}
/*
 *	Find the maximum value node out of a single linked list.
 *  Return the node.
 */
node* find_max_node(node* n) {
	
	node* pointer = n;
	node* head = n;
	node* returnNode = malloc(sizeof(node)); 
	unsigned long max = 0;
	
	while (head != NULL) { // Loop through the given linked list and find the max node. 
		if (head -> count > max) {
			max = head -> count;
		}
		head = head -> next;
	}
	
	while (pointer != NULL) {  // Find the node that has the maximum value (for returning purpose).
		if (pointer -> count == max) { 
			
			returnNode = pointer; 
		}
		pointer = pointer -> next;
	}
	
	return returnNode;
}

/*
 * Lowercase given string. 
 */ 
char* lower_string(char* string) {

	int i;
	
	for (i = 0; i <= strlen(string) ; i++){
	      if (string[i] >= 65 && string[i] <= 90) {
			  string[i] = string[i] + 32;
		  } 
	}
	return string;
}

/*
 * Add the string to the hashtable in the appropriate "bucket".
 * 
 * Return 1 if successful, and 0 if an error occurred 
 * (e.g. if the string is null, if memory could not be allocated,
 * or if the string is already in the hashtable).
 */
int put(char* string, hashtable* h)
{
	unsigned long temp;
	int error = hash(string, &temp);
	unsigned long bucket_number = temp % CAPACITY;
	
	if (get(string, h) == 1) {
		return 0;
	}
	
	if(string == NULL || error == 0 || h == NULL) { 
		return 0;
	}
	
	node* head = h->list[bucket_number];
	
	
	node* new = malloc(sizeof(node));
	new->value = malloc(strlen(string) + 1);
	
	if (new == NULL || new->value == NULL) return 0;
	
	strcpy(new->value, string);
	
	new -> next = head; 
	h->list[bucket_number] = new; 
	h->list[bucket_number] -> count += 1; // increment initial count;
	return 1; 

}


/*
 * Determine whether the specified string is in the hashtable.
 * Return 1 if it is found, 0 if it is not (or if it is null).
 */
int get(char* string, hashtable* h)
{
	unsigned long temp;
	int error = hash(string, &temp);
	unsigned long bucket_number = temp % CAPACITY;
	
	if(string == NULL || error == 0) { 
		return 0;
	}
	node* head = h->list[bucket_number];
	
	if (head == NULL) return 0;
	
	while (head != NULL) {
		
		if (strcmp(string, head->value) == 0) {
			head -> count++;
			return 1; 
		}
		head = head -> next;
		
	}
	return 0; 
	
}


