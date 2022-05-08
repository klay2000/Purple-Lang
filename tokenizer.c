#include "tokenizer.h"

struct Token* push_token(struct Token* parent, struct Token* child){
    parent.next_token = child;
    return child;
}

struct Token* recursive_tokenization(struct Token* t, char* text){
    char c = *text;
    Token n
    if(c == 0){
        
    }
    else if(c == '('){

    }
    else if(c == ')'){

    }
    else if(c == '{'){

    }
    else if(c == '}'){

    }
    else if(c == '\"'){

    }

    return push_token(recursive_tokenization(t, char*text))

}

struct Token* tokenize(char* text){
    struct Token start
    return recursive_tokenization(&start, text)
}