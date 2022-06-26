#include "tokenizer.h"
#include "parser.h"
#include <stdlib.h>

struct SyntaxNode* parse(struct Token* tokens){
	struct SyntaxNode* firstNode = malloc(sizeof(struct SyntaxNode));
	firstNode->token = tokens;
	firstNode->children = NULL;
	return recursive_parse(tokens->next_token, firstNode);
}

static struct SyntaxNode* recursive_parse(struct Token* tokens, struct SyntaxNode* lastNode){

	struct SyntaxNode* newNode;
	
	while(tokens->type != eof){
		if(tokens->type != open_par && tokens->type != close_par){
			newNode = malloc(sizeof(struct SyntaxNode));
			newNode->token = tokens;

			lastNode->children = push_node(lastNode->children, newNode);
			
			tokens = tokens->next_token;
		}
		else if(tokens->type == open_par){
			recursive_parse(tokens->next_token, peek_node(lastNode->children));

			tokens = tokens->next_token;
		}
		else{
			tokens = tokens->next_token;
			break;
		}
	}

	return lastNode;
}

struct SyntaxNodeStack* push_node(struct SyntaxNodeStack* list, struct SyntaxNode* node){
	struct SyntaxNodeStack* newList = malloc(sizeof(struct SyntaxNodeStack));

	if(list == NULL){
		list = malloc(sizeof(struct SyntaxNodeStack));
		list->child = NULL;
		list->node = NULL;
	}

	newList->child = list;
	newList->node = node;

	return newList;
}

struct SyntaxNode* peek_node(struct SyntaxNodeStack* list){
	return list->node;
}

struct SyntaxNodeStack* pop_node(struct SyntaxNodeStack* list){
	struct SyntaxNodeStack* newList = list->child;
	free(list->node);
	free(list);
	return newList;
}