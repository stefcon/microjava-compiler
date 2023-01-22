package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column


%xstate COMMENT


%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"class" 	{ return new_symbol(sym.CLASS, yytext()); }
"extends"   { return new_symbol(sym.EXT, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }

"new"		{ return new_symbol(sym.NEW, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"int"		{ return new_symbol(sym.INT, yytext()); }
"char"		{ return new_symbol(sym.CHAR, yytext()); }
"bool"		{ return new_symbol(sym.BOOL, yytext()); }

"true"		{ return new_symbol(sym.BOOL, true); }
"false"		{ return new_symbol(sym.BOOL, false); }

"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"while"		{ return new_symbol(sym.WHILE, yytext()); }
"foreach"	{ return new_symbol(sym.FOREACH, yytext()); }

"if"		{ return new_symbol(sym.IF, yytext()); }
"else" 		{ return new_symbol(sym.ELSE, yytext()); }

"++"		{ return new_symbol(sym.INC, yytext()); }
"--"		{ return new_symbol(sym.DEC, yytext()); }
"=>"		{ return new_symbol(sym.ARROW, yytext()); }
"*"			{ return new_symbol(sym.MUL, yytext()); }
"/"			{ return new_symbol(sym.DIV, yytext()); }
"%"			{ return new_symbol(sym.MOD, yytext()); }
"||"		{ return new_symbol(sym.OR, yytext()); }
"&&"		{ return new_symbol(sym.AND, yytext()); }
"."			{ return new_symbol(sym.DOT, yytext()); }
<YYINITIAL> "+" 		{ return new_symbol(sym.PLUS, yytext()); }
<YYINITIAL> "-" 		{ return new_symbol(sym.MINUS, yytext()); }
"=="		{ return new_symbol(sym.EQ, yytext()); }

"!="		{ return new_symbol(sym.NE, yytext()); }
">"			{ return new_symbol(sym.GT, yytext()); }
">="		{ return new_symbol(sym.GE, yytext()); }
"<"			{ return new_symbol(sym.LT, yytext()); }
"<="		{ return new_symbol(sym.LE, yytext()); }

"=" 		{ return new_symbol(sym.ASSIGN, yytext()); }

";" 		{ return new_symbol(sym.SEMI, yytext()); }
":"			{ return new_symbol(sym.COL, yytext()); }	
"," 		{ return new_symbol(sym.COMMA, yytext()); }

"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"["			{ return new_symbol(sym.LBRACKET, yytext()); }
"]"			{ return new_symbol(sym.RBRACKET, yytext()); }

<YYINITIAL> "//" 	{ yybegin(COMMENT); }
<COMMENT> .      	{ yybegin(COMMENT); }
<COMMENT> "\r\n" 	{ yybegin(YYINITIAL); }

'[\040-\176]' {return new_symbol(sym.CHAR, new Character(yytext().charAt(1)));}

[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






