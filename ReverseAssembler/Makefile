#
# Makefile for CIT 593 LC4 project
#

# this rule invokes the lc4: rule below
#
all: lc4

# this rule partially compiles lc4_memory.c, to the target: lc4_memory.o
#
lc4_memory.o: lc4_memory.c lc4_memory.h
	clang -c lc4_memory.c -g

lc4_loader.o: lc4_loader.c lc4_loader.h
	clang -c lc4_loader.c -g

lc4_disassembler.o: lc4_disassembler.c lc4_disassembler.h
	clang -c lc4_disassembler.c -g

lc4: lc4.c lc4_memory.o lc4_loader.o lc4_disassembler.o
	clang lc4.c lc4_memory.o lc4_loader.o lc4_disassembler.o -o lc4 -g


clean:
	rm -rf *.o linkedlist.tar


clobber: clean
	rm -rf lc4

tarball: 
	tar -cvf linkedlist.tar .

	
