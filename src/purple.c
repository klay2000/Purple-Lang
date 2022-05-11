#include <stdio.h>
#include "tokenizer.h"
#include "parser.h"
#include "interpreter.h"

int main( int argc, char* argv ){
	char* str = "() {} \"tes\\\'\"t\" 1234567890 idenTest()";
	struct Token* i = tokenize(str);
	while(i->type != eof){
		printf("%d: %s\n", i->type, i->text);
		i = i->next_token;
	}
	return 0;
}
