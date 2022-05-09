#include "tokenizer.h"

struct Token* push_token(struct Token* parent, struct Token* child){
    parent->next_token = child;
    return child;
}

struct Token* recursive_tokenization(struct Token* t, char* text){
    char c = *text;
    struct Token n;
    if(c == 0){
        n.type = eof;
    }
    else if(c == '('){
        n.type = open_par;
    }
    else if(c == ')'){
        n.type = close_par;
    }
    else if(c == '{'){
        n.type = open_brace;
    }
    else if(c == '}'){
        n.type = close_brace;
    }
    // else if(c == '\"'){

    // }

    return push_token(&n, recursive_tokenization(t, text + 1));

}

struct Token* tokenize(char* text){
    struct Token start_token;
    start_token.type = start;
    return recursive_tokenization(&start_token, text);
}