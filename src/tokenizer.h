#ifndef TOKENIZER
#define TOKENIZER

enum Tokentype{
    start,
    identifier,
    literal,
    open_par,
    close_par,
    comma,
    eof
};

struct Token{
    struct Token* next_token;
    char* text;
    enum Tokentype type;
};

struct Token* tokenize(char* text);

static struct Token* push_token(struct Token* parent, struct Token* child);
static struct Token* recursive_tokenization(struct Token* t, char* text);
static int is_whitespace(char* c);

#endif