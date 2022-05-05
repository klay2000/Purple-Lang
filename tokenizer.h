#ifndef TOKENIZER
#define TOKENIZER

    enum Tokentype{
        variable,
        literal,
        open_par,
        close_par,
        open_brace,
        close_brace
    }

    struct Token{
        token* next_token;
        char* text;
        Tokentype type;
    }

#endif