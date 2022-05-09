#include <stdio.h>
#include "tokenizer.h"
#include "parser.h"
#include "interpreter.h"

int main( int argc, char* argv ){
	struct Token* i = tokenize(argv);
	while(i->type != eof){
		printf("%d", i->type);
		i = i->next_token;
	}
	return 0;
}
