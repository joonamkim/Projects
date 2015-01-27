/* 
Joo-nam Kim
*/
#include <stdio.h>
#include <stdlib.h>
#include "lc4_memory.h"

struct row_of_memory* add_to_list    (struct row_of_memory* head, 
				      short unsigned int address, 
				      short unsigned int contents) 
{
/* takes in head pointer to a linked list: head
	   creates a new node with address & contents passed in
	   adds node to end of linked list pointed to by head
	   if list is empty, new node is now head
	   always returns head pointer */
	
	struct row_of_memory* current = NULL;
	struct row_of_memory* newNode = NULL;
	
	/* allocate memory for a single node */
	newNode = (struct row_of_memory*)malloc(sizeof(struct row_of_memory));

	if (newNode == NULL){
		printf("Could not allocate a new node!\n");
	}
	
	/* populate fields in newly allocated node w/ address&contents */
	newNode -> address = address;
	newNode -> label = NULL;
	newNode -> contents = contents;
	newNode -> assembly = NULL;
	newNode -> next = NULL;
	
	
	/* add node to the end of linked list pointed to by "head"
  	 if list doesn't exist, create 1st node */
	/* if head==NULL, node created is the new head of the list! */
	if (head == NULL){
		head = newNode;

	}
	/* otherwise, traverse linked list until we reach the end */
	else{
		current = head;
		while (current -> next != NULL){
			current = current -> next;
		}
		current -> next = newNode;
	}
	/* always return pointer to head of linked list */
	return head;

}

struct row_of_memory* search_address (struct row_of_memory* head,
				      short unsigned int address )
{

	struct row_of_memory* current;

	/* traverse linked list, searching each node for "address"  */
	current = head;
	while (current != NULL) {

		/* return pointer to node in list that matches */
		if (current -> address == address) {
			return current ;
		}
		current = current -> next;
	}
	/* if address isn't found return NULL */
	return NULL;
}

struct row_of_memory* search_opcode  (struct row_of_memory* head,
				      short unsigned int opcode  )
{
	
	

	/* return NULL if list is empty or if no matching nodes */
	if (head == NULL) {
		printf("head is null\n");
		return 0;
	}

	struct row_of_memory* current = head;
	/* traverse linked list until node is found with matching opcode
   AND "assembly" field of node is empty */
	/* return pointer to node in list that matches */
	while (current -> next != NULL){
		if ((((current -> contents) & (61440)) == (opcode << 12)) && (current -> assembly == NULL)) {
			return current;
		}
		current = current -> next;
	}
	return NULL ;
}

void print_list (struct row_of_memory* head )
{
	/* make sure head isn't NULL */
	if (head == NULL) {
		printf("%s\n", "The list is empty");
		//exit(0);

	} else {

		/* print out a header */
		struct row_of_memory* current = head;

		/* traverse linked list, print contents of each node */
		printf("<label>\t\t<address>\t<contents>\t<assembly>");
		printf("\n");

		while (current != NULL) {

			printf("%s\t\t", current -> label);
			printf("%x\t\t", current -> address);
			printf("%x\t\t", current -> contents);
			printf("%s",current -> assembly);
			printf("\n");
			current = current -> next;
		}
	}
}
struct row_of_memory* delete_list    (struct row_of_memory* head ) 
{
	/* delete entire list node by node */
	struct row_of_memory* current = head;
	struct row_of_memory* next;
       if (head == NULL){
         return NULL;
       }
       while (1){
           if (current->next != NULL){
			   next = current->next;
			   free(current->label);
			   free(current->assembly);
			   free(current);
			   current = next;
		   } else {
			   free(current->label);
			   free(current->assembly);
			   free(current->next);
			   free(current);
			   break;
		   }
	   }
	   return NULL ;
}

