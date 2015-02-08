all: tweets

tweets:
	clang -o tweets tweets.c hashtable.c 
clean:
	rm -rf *.o tweets.tar


clobber: clean
	rm -rf tweets

tarball: 
	tar -cvf tweets.tar .