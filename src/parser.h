#ifndef PARSER
#define PARSER

#include "tokenizer.h"

struct SyntaxNode{
    struct SyntaxNodeStack* children;
    struct Token* token;
};

struct SyntaxNodeStack{
    struct SyntaxNode* node;
    struct SyntaxNodeStack* child;
};

struct SyntaxNode* parse(struct Token* tokens);

static struct SyntaxNode* recursive_parse(struct Token* token, struct SyntaxNode* lastNodes);
struct SyntaxNodeStack* push_node(struct SyntaxNodeStack* list, struct SyntaxNode* node);
struct SyntaxNodeStack* pop_node(struct SyntaxNodeStack* list);
struct SyntaxNode* peek_node(struct SyntaxNodeStack* list);

#endif