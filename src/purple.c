#include <stdio.h>
#include "tokenizer.h"
#include "parser.h"
#include "interpreter.h"

int main( int argc, char* argv ){
	char* str = "() {} \"test\" idenTest()";
	struct Token* i = tokenize(str);
	while(i->type != eof){
		if(i->type != start)
			printf("%d: %c\n", i->type, *(i->text));
		i = i->next_token;
	}
	return 0;
}
