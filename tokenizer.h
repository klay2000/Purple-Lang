#ifndef TOKENIZER
#define TOKENIZER

    enum Tokentype{
        identifier,
        literal,
        open_par,
        close_par,
        open_brace,
        close_brace
    }

    struct Token{
        token* next_token;
        char* text;
        enum Tokentype type;
    }

    struct Token* push_token(struct Token* t1, struct Token* t2);
    struct Token* recursive_tokenization(struct Token* t, char* text);
    struct Token* tokenize(char* text);

#endif