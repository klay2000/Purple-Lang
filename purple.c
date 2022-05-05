#include <stdio.h>
#include "tokenizer.h"
#include "parser.h"
#include "interpreter.h"

int main( int argc, char* argv[] ){
	interpret(parse(tokenize(argv, argc)))
	return 0;
}
