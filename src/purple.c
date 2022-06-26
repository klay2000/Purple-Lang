#include <stdio.h>
#include "tokenizer.h"
#include "parser.h"
#include "interpreter.h"

int main( int argc, char* argv ){
	char* str = "test(hi)";
	struct Token* tokens = tokenize(str);
	struct SyntaxNode* root = parse(tokens);
	return 0;
}
