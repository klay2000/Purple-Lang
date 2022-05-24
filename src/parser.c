#include "tokenizer.h"
#include "parser.h"
#include <stdlib.h>

struct SyntaxNode* parse(struct Token* tokens){
	struct SyntaxNode* firstNode = malloc(sizeof(*firstNode));
	firstNode->token = tokens;
	firstNode->children = NULL;
	return recursive_parse(tokens+1, firstNode);
}

static struct SyntaxNode* recursive_parse(struct Token* tokens, struct SyntaxNode* lastNode){

	struct SyntaxNode* newNode;
	

	while(tokens->type != eof){

		if(tokens->type != open_par && tokens->type != close_par){
			newNode = malloc(sizeof(newNode));
			newNode->token = tokens;

			lastNode->children = push_node(lastNode->children, newNode);
			
			tokens++;
		}
		else if(tokens->type == open_par){
			newNode = recursive_parse(++tokens, peek_node(lastNode->children));

			lastNode->children = push_node(lastNode->children, newNode);
		}
		else{
			tokens ++;
			return lastNode;
		}
	}

	return lastNode;
}

struct SyntaxNodeStack* push_node(struct SyntaxNodeStack* list, struct SyntaxNode* node){
	struct SyntaxNodeStack* newList = malloc(sizeof(*newList));

	if(list == NULL){
		list = malloc(sizeof(list));
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