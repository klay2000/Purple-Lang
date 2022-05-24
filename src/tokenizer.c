#include "tokenizer.h"
#include <stdlib.h>

static int is_whitespace(char* c){
    return *c == 13 | *c == '\n' | *c == '\t' | *c == ' ';
}

static struct Token* push_token(struct Token* parent, struct Token* child){
    parent->next_token = child;
    return parent;
}

static struct Token* recursive_tokenization(struct Token* t, char* text){
    struct Token* n = malloc(sizeof(*n));

    // skip whitespace
    while(is_whitespace(text)){
        *text++;
    }

    // eof
    if(*text == 0){
        n->type = eof;
        n->text = "";
    }

    // parenthesis
    else if(*text == '('){
        n->type = open_par;
        n->text = "(";
    }
    else if(*text == ')'){
        n->type = close_par;
        n->text = ")";
    }
    // braces
    else if(*text == ','){
        n->type = comma;
        n->text = ",";
    }
    // string literals
    else if(*text == '\"' | *text == '\''){
        n->type = literal;
        
        char term = *text;

        text++;
        int len = 0;
        char* start = text;

        while(*text != term){
            if(*text == '\\'){
                len+=1;
                text+=1;
            }

            len++;
            text++;
        }

        n->text = malloc(sizeof(char)*(len+1));

        for(int i = 0; i < len; i++){
            *(n->text+i) = *(start+i);
        }

        *(n->text+len) = 0;
    }
    // number literals
    else if(*text >= 48 && *text <= 57){
        n->type = literal;

        int len = 0;
        char* start = text;

        while(*text >= 48 && *text <= 57){
            len++;
            text++;
        }
        text--;

        n->text = malloc(sizeof(char)*len+1);

        for(int i = 0; i < len; i++){
            *(n->text+i) = *(start+i);
        }

        *(n->text+len) = 0;
    }
    // identifiers
    else if((*text >= 65 && *text <= 90)
          ||(*text >= 97 && *text <= 122)){
        
        n->type = identifier;

        int len = 0;
        char* start = text;

        while((*text >= 65 && *text <= 90)
            ||(*text >= 97 && *text <= 122)
            ||(*text >= 48 && *text <= 57)){
            len++;
            text++;
        }
        text--;

        n->text = malloc(sizeof(char)*len+1);

        for(int i = 0; i < len; i++){
            *(n->text+i) = *(start+i);
        }

        *(n->text+len) = 0;
    }

    if(n->type == eof){
        return push_token(t, n);
    }

    return push_token(t, recursive_tokenization(n, text + 1));


}

struct Token* tokenize(char* text){
    struct Token* start_token = malloc(sizeof(*start_token));
    start_token->type = start;
    start_token->text = malloc(sizeof(char));
    *(start_token->text) = 0;
    return recursive_tokenization(start_token, text);
}