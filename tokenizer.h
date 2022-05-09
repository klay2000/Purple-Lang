#ifndef TOKENIZER
#define TOKENIZER

enum Tokentype{
    start,
    identifier,
    literal,
    open_par,
    close_par,
    open_brace,
    close_brace,
    eof
};

struct Token{
    struct Token* next_token;
    char* text;
    enum Tokentype type;
};

struct Token* push_token(struct Token* parent, struct Token* child);
struct Token* recursive_tokenization(struct Token* t, char* text);
struct Token* tokenize(char* text);

#endif