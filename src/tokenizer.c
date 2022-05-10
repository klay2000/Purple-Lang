#include "tokenizer.h"
#include <stdlib.h>

struct Token* push_token(struct Token* parent, struct Token* child){
    parent->next_token = child;
    return parent;
}

struct Token* recursive_tokenization(struct Token* t, char* text){
    char* c = text;
    struct Token* n = malloc(sizeof(*n));
    if(*c == 0){
        n->type = eof;
        n->text = c;
    }
    else if(*c == '('){
        n->type = open_par;
        n->text = c;
    }
    else if(*c == ')'){
        n->type = close_par;
        n->text = c;
    }
    else if(*c == '{'){
        n->type = open_brace;
        n->text = c;
    }
    else if(*c == '}'){
        n->type = close_brace;
        n->text = c;
    }
    // else if(c == '\"'){

    // }

    if(n->type == eof){
        return n;
    }

    return push_token(t, recursive_tokenization(n, text + 1));


}

struct Token* tokenize(char* text){
    struct Token* start_token = malloc(sizeof(*start_token));
    start_token->type = start;
    return recursive_tokenization(start_token, text);
}