// $ANTLR 3.2 debian-7ubuntu3 src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g 2014-09-03 10:47:32

    package fr.inria.linuxtools.ctf.parser;
    import java.util.Set;
    import java.util.HashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class CTFParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALIGNTOK", "CONSTTOK", "CHARTOK", "DOUBLETOK", "ENUMTOK", "EVENTTOK", "FLOATINGPOINTTOK", "FLOATTOK", "INTEGERTOK", "INTTOK", "LONGTOK", "SHORTTOK", "SIGNEDTOK", "STREAMTOK", "STRINGTOK", "STRUCTTOK", "TRACETOK", "TYPEALIASTOK", "TYPEDEFTOK", "UNSIGNEDTOK", "VARIANTTOK", "VOIDTOK", "BOOLTOK", "COMPLEXTOK", "IMAGINARYTOK", "ENVTOK", "CLOCKTOK", "CALLSITETOK", "NANNUMBERTOK", "INFINITYTOK", "NINFINITYTOK", "SEPARATOR", "COLON", "ELIPSES", "ASSIGNMENT", "TYPE_ASSIGNMENT", "LT", "GT", "OPENBRAC", "CLOSEBRAC", "LPAREN", "RPAREN", "LCURL", "RCURL", "TERM", "POINTER", "SIGN", "ARROW", "DOT", "BACKSLASH", "DIGIT", "OCT_DIGIT", "OCT_PREFIX", "NONZERO_DIGIT", "HEX_DIGIT", "HEX_PREFIX", "INTEGER_TYPES_SUFFIX", "OCTAL_LITERAL", "DECIMAL_LITERAL", "HEX_LITERAL", "OCTAL_ESCAPE", "UNICODE_ESCAPE", "HEXADECIMAL_ESCAPE", "ESCAPE_SEQUENCE", "STRINGPREFIX", "SINGLEQUOTE", "CHAR_CONTENT", "CHARACTER_LITERAL", "DOUBLEQUOTE", "STRING_CONTENT", "STRING_LITERAL", "WS", "COMMENT_OPEN", "COMMENT_CLOSE", "COMMENT", "LINE_COMMENT", "NONDIGIT", "IDENTIFIER", "ROOT", "EVENT", "STREAM", "TRACE", "ENV", "CLOCK", "CALLSITE", "DECLARATION", "SV_DECLARATION", "TYPE_SPECIFIER_LIST", "TYPE_DECLARATOR_LIST", "TYPE_DECLARATOR", "STRUCT", "STRUCT_NAME", "STRUCT_BODY", "ALIGN", "CTF_EXPRESSION_TYPE", "CTF_EXPRESSION_VAL", "CTF_LEFT", "CTF_RIGHT", "UNARY_EXPRESSION_STRING", "UNARY_EXPRESSION_STRING_QUOTES", "UNARY_EXPRESSION_DEC", "UNARY_EXPRESSION_HEX", "UNARY_EXPRESSION_OCT", "LENGTH", "TYPEDEF", "TYPEALIAS", "TYPEALIAS_TARGET", "TYPEALIAS_ALIAS", "INTEGER", "STRING", "FLOATING_POINT", "ENUM", "ENUM_CONTAINER_TYPE", "ENUM_ENUMERATOR", "ENUM_NAME", "ENUM_VALUE", "ENUM_VALUE_RANGE", "ENUM_BODY", "VARIANT", "VARIANT_NAME", "VARIANT_TAG", "VARIANT_BODY", "DECLARATOR"
    };
    public static final int SIGN=50;
    public static final int LT=40;
    public static final int TYPEDEFTOK=22;
    public static final int VARIANT_NAME=123;
    public static final int ENV=86;
    public static final int INTEGER_TYPES_SUFFIX=60;
    public static final int POINTER=49;
    public static final int TRACE=85;
    public static final int HEX_PREFIX=59;
    public static final int INTTOK=13;
    public static final int SEPARATOR=35;
    public static final int ENUMTOK=8;
    public static final int COMPLEXTOK=27;
    public static final int IMAGINARYTOK=28;
    public static final int STREAMTOK=17;
    public static final int EOF=-1;
    public static final int UNARY_EXPRESSION_OCT=106;
    public static final int ENUM_VALUE=119;
    public static final int UNSIGNEDTOK=23;
    public static final int ENUM_NAME=118;
    public static final int RPAREN=45;
    public static final int CHAR_CONTENT=70;
    public static final int STRING_LITERAL=74;
    public static final int UNARY_EXPRESSION_STRING_QUOTES=103;
    public static final int ALIGNTOK=4;
    public static final int FLOATTOK=11;
    public static final int STRUCT_BODY=96;
    public static final int ENUM_BODY=121;
    public static final int COMMENT_CLOSE=77;
    public static final int STRINGTOK=18;
    public static final int COMMENT=78;
    public static final int STREAM=84;
    public static final int UNARY_EXPRESSION_HEX=105;
    public static final int UNARY_EXPRESSION_DEC=104;
    public static final int FLOATINGPOINTTOK=10;
    public static final int LINE_COMMENT=79;
    public static final int CTF_EXPRESSION_TYPE=98;
    public static final int DOUBLETOK=7;
    public static final int TYPE_DECLARATOR=93;
    public static final int CHARACTER_LITERAL=71;
    public static final int STRUCT_NAME=95;
    public static final int OCTAL_ESCAPE=64;
    public static final int VARIANT=122;
    public static final int NANNUMBERTOK=32;
    public static final int ENUM_ENUMERATOR=117;
    public static final int FLOATING_POINT=114;
    public static final int DECLARATOR=126;
    public static final int SIGNEDTOK=16;
    public static final int CHARTOK=6;
    public static final int WS=75;
    public static final int INTEGERTOK=12;
    public static final int VARIANT_BODY=125;
    public static final int NONDIGIT=80;
    public static final int OCT_PREFIX=56;
    public static final int GT=41;
    public static final int TYPEALIAS_TARGET=110;
    public static final int DECIMAL_LITERAL=62;
    public static final int BACKSLASH=53;
    public static final int CLOSEBRAC=43;
    public static final int TERM=48;
    public static final int BOOLTOK=26;
    public static final int CTF_RIGHT=101;
    public static final int TYPE_DECLARATOR_LIST=92;
    public static final int STRING_CONTENT=73;
    public static final int TYPE_ASSIGNMENT=39;
    public static final int ENUM_CONTAINER_TYPE=116;
    public static final int DOUBLEQUOTE=72;
    public static final int ENUM_VALUE_RANGE=120;
    public static final int DECLARATION=89;
    public static final int LENGTH=107;
    public static final int INFINITYTOK=33;
    public static final int LPAREN=44;
    public static final int STRINGPREFIX=68;
    public static final int CTF_EXPRESSION_VAL=99;
    public static final int ESCAPE_SEQUENCE=67;
    public static final int OCT_DIGIT=55;
    public static final int UNICODE_ESCAPE=65;
    public static final int CALLSITETOK=31;
    public static final int SINGLEQUOTE=69;
    public static final int IDENTIFIER=81;
    public static final int HEX_LITERAL=63;
    public static final int ALIGN=97;
    public static final int DIGIT=54;
    public static final int DOT=52;
    public static final int STRUCTTOK=19;
    public static final int ENVTOK=29;
    public static final int TYPEALIASTOK=21;
    public static final int OPENBRAC=42;
    public static final int CLOCK=87;
    public static final int INTEGER=112;
    public static final int TYPEALIAS=109;
    public static final int CALLSITE=88;
    public static final int EVENTTOK=9;
    public static final int NINFINITYTOK=34;
    public static final int TYPEDEF=108;
    public static final int VOIDTOK=25;
    public static final int TYPE_SPECIFIER_LIST=91;
    public static final int OCTAL_LITERAL=61;
    public static final int COMMENT_OPEN=76;
    public static final int HEX_DIGIT=58;
    public static final int STRUCT=94;
    public static final int EVENT=83;
    public static final int LONGTOK=14;
    public static final int ROOT=82;
    public static final int CTF_LEFT=100;
    public static final int CLOCKTOK=30;
    public static final int TRACETOK=20;
    public static final int COLON=36;
    public static final int HEXADECIMAL_ESCAPE=66;
    public static final int LCURL=46;
    public static final int VARIANTTOK=24;
    public static final int VARIANT_TAG=124;
    public static final int ENUM=115;
    public static final int ELIPSES=37;
    public static final int RCURL=47;
    public static final int TYPEALIAS_ALIAS=111;
    public static final int UNARY_EXPRESSION_STRING=102;
    public static final int ARROW=51;
    public static final int ASSIGNMENT=38;
    public static final int SHORTTOK=15;
    public static final int SV_DECLARATION=90;
    public static final int NONZERO_DIGIT=57;
    public static final int CONSTTOK=5;
    public static final int STRING=113;

    // delegates
    // delegators

    protected static class Symbols_scope {
        Set<String> types;
    }
    protected Stack Symbols_stack = new Stack();


        public CTFParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CTFParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CTFParser.tokenNames; }
    public String getGrammarFileName() { return "src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g"; }


        public CTFParser(TokenStream input, boolean verbose) {
            this(input);
            this.verbose = verbose;
        }

        /**
          * This method is overriden to disable automatic error recovery.
          * On a mismatched token, it simply re-throw an exception.
          */
        @Override
        protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
            throw new MismatchedTokenException(ttype, input);
        }

        /**
         * Checks if a given name has been defined has a type.
         * From: http://www.antlr.org/grammar/1153358328744/C.g
         *
         * @param name The name to check.
         * @return True if is is a type, false otherwise.
         */
        boolean isTypeName(String name) {
            for (int i = Symbols_stack.size() - 1; i >= 0; i--) {
                Symbols_scope scope = (Symbols_scope) Symbols_stack.get(i);
                if (scope.types.contains(name)) {
                    return true;
                }
            }
            return false;
        }

        void addTypeName(String name) {
            ((Symbols_scope)Symbols_stack.peek()).types.add(name);
            if (verbose) {
                debug_print("New type: " + name);
            }
        }

        boolean _inTypedef = false;

        void typedefOn() {
            debug_print("typedefOn");
            _inTypedef = true;
        }

        void typedefOff() {
            debug_print("typedefOff");
            _inTypedef = false;
        }

        boolean inTypedef() {
            return _inTypedef;
        }

        boolean _inTypealiasAlias = false;

        void typealiasAliasOn() {
            debug_print("typealiasAliasOn");
            _inTypealiasAlias = true;
        }

        void typealiasAliasOff() {
             debug_print("typealiasAliasOff");
            _inTypealiasAlias = false;
        }

        boolean inTypealiasAlias() {
            return _inTypealiasAlias;
        }

        void debug_print(String str) {
            if (verbose) {
                System.out.println(str);
            }
        }

        /* Prints rule entry and exit while parsing */
        boolean verbose = false;


    public static class parse_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parse"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:177:1: parse : ( declaration )+ EOF -> ^( ROOT ( declaration )+ ) ;
    public final CTFParser.parse_return parse() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CTFParser.parse_return retval = new CTFParser.parse_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        CTFParser.declaration_return declaration1 = null;


        CommonTree EOF2_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");

            ((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:182:3: ( ( declaration )+ EOF -> ^( ROOT ( declaration )+ ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:182:5: ( declaration )+ EOF
            {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:182:5: ( declaration )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=CONSTTOK && LA1_0<=ENUMTOK)||(LA1_0>=FLOATINGPOINTTOK && LA1_0<=SIGNEDTOK)||(LA1_0>=STRINGTOK && LA1_0<=STRUCTTOK)||(LA1_0>=TYPEDEFTOK && LA1_0<=IMAGINARYTOK)) ) {
                    alt1=1;
                }
                else if ( (LA1_0==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {
                    alt1=1;
                }
                else if ( (LA1_0==EVENTTOK||LA1_0==STREAMTOK||(LA1_0>=TRACETOK && LA1_0<=TYPEALIASTOK)||(LA1_0>=ENVTOK && LA1_0<=CALLSITETOK)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:182:5: declaration
            	    {
            	    pushFollow(FOLLOW_declaration_in_parse449);
            	    declaration1=declaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_declaration.add(declaration1.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_parse452); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF2);



            // AST REWRITE
            // elements: declaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 182:22: -> ^( ROOT ( declaration )+ )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:182:25: ^( ROOT ( declaration )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ROOT, "ROOT"), root_1);

                if ( !(stream_declaration.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_declaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_declaration.nextTree());

                }
                stream_declaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "parse"

    public static class numberLiteral_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "numberLiteral"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:185:1: numberLiteral : ( SIGN )* ( HEX_LITERAL -> ^( UNARY_EXPRESSION_HEX HEX_LITERAL ( SIGN )* ) | DECIMAL_LITERAL -> ^( UNARY_EXPRESSION_DEC DECIMAL_LITERAL ( SIGN )* ) | OCTAL_LITERAL -> ^( UNARY_EXPRESSION_OCT OCTAL_LITERAL ( SIGN )* ) ) ;
    public final CTFParser.numberLiteral_return numberLiteral() throws RecognitionException {
        CTFParser.numberLiteral_return retval = new CTFParser.numberLiteral_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SIGN3=null;
        Token HEX_LITERAL4=null;
        Token DECIMAL_LITERAL5=null;
        Token OCTAL_LITERAL6=null;

        CommonTree SIGN3_tree=null;
        CommonTree HEX_LITERAL4_tree=null;
        CommonTree DECIMAL_LITERAL5_tree=null;
        CommonTree OCTAL_LITERAL6_tree=null;
        RewriteRuleTokenStream stream_SIGN=new RewriteRuleTokenStream(adaptor,"token SIGN");
        RewriteRuleTokenStream stream_OCTAL_LITERAL=new RewriteRuleTokenStream(adaptor,"token OCTAL_LITERAL");
        RewriteRuleTokenStream stream_HEX_LITERAL=new RewriteRuleTokenStream(adaptor,"token HEX_LITERAL");
        RewriteRuleTokenStream stream_DECIMAL_LITERAL=new RewriteRuleTokenStream(adaptor,"token DECIMAL_LITERAL");

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:186:3: ( ( SIGN )* ( HEX_LITERAL -> ^( UNARY_EXPRESSION_HEX HEX_LITERAL ( SIGN )* ) | DECIMAL_LITERAL -> ^( UNARY_EXPRESSION_DEC DECIMAL_LITERAL ( SIGN )* ) | OCTAL_LITERAL -> ^( UNARY_EXPRESSION_OCT OCTAL_LITERAL ( SIGN )* ) ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:186:5: ( SIGN )* ( HEX_LITERAL -> ^( UNARY_EXPRESSION_HEX HEX_LITERAL ( SIGN )* ) | DECIMAL_LITERAL -> ^( UNARY_EXPRESSION_DEC DECIMAL_LITERAL ( SIGN )* ) | OCTAL_LITERAL -> ^( UNARY_EXPRESSION_OCT OCTAL_LITERAL ( SIGN )* ) )
            {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:186:5: ( SIGN )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SIGN) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:186:5: SIGN
            	    {
            	    SIGN3=(Token)match(input,SIGN,FOLLOW_SIGN_in_numberLiteral474); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SIGN.add(SIGN3);


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:187:7: ( HEX_LITERAL -> ^( UNARY_EXPRESSION_HEX HEX_LITERAL ( SIGN )* ) | DECIMAL_LITERAL -> ^( UNARY_EXPRESSION_DEC DECIMAL_LITERAL ( SIGN )* ) | OCTAL_LITERAL -> ^( UNARY_EXPRESSION_OCT OCTAL_LITERAL ( SIGN )* ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case HEX_LITERAL:
                {
                alt3=1;
                }
                break;
            case DECIMAL_LITERAL:
                {
                alt3=2;
                }
                break;
            case OCTAL_LITERAL:
                {
                alt3=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:187:9: HEX_LITERAL
                    {
                    HEX_LITERAL4=(Token)match(input,HEX_LITERAL,FOLLOW_HEX_LITERAL_in_numberLiteral485); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_HEX_LITERAL.add(HEX_LITERAL4);



                    // AST REWRITE
                    // elements: SIGN, HEX_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 187:21: -> ^( UNARY_EXPRESSION_HEX HEX_LITERAL ( SIGN )* )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:187:24: ^( UNARY_EXPRESSION_HEX HEX_LITERAL ( SIGN )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_HEX, "UNARY_EXPRESSION_HEX"), root_1);

                        adaptor.addChild(root_1, stream_HEX_LITERAL.nextNode());
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:187:59: ( SIGN )*
                        while ( stream_SIGN.hasNext() ) {
                            adaptor.addChild(root_1, stream_SIGN.nextNode());

                        }
                        stream_SIGN.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:188:9: DECIMAL_LITERAL
                    {
                    DECIMAL_LITERAL5=(Token)match(input,DECIMAL_LITERAL,FOLLOW_DECIMAL_LITERAL_in_numberLiteral506); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DECIMAL_LITERAL.add(DECIMAL_LITERAL5);



                    // AST REWRITE
                    // elements: DECIMAL_LITERAL, SIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 188:25: -> ^( UNARY_EXPRESSION_DEC DECIMAL_LITERAL ( SIGN )* )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:188:28: ^( UNARY_EXPRESSION_DEC DECIMAL_LITERAL ( SIGN )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_DEC, "UNARY_EXPRESSION_DEC"), root_1);

                        adaptor.addChild(root_1, stream_DECIMAL_LITERAL.nextNode());
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:188:67: ( SIGN )*
                        while ( stream_SIGN.hasNext() ) {
                            adaptor.addChild(root_1, stream_SIGN.nextNode());

                        }
                        stream_SIGN.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:189:9: OCTAL_LITERAL
                    {
                    OCTAL_LITERAL6=(Token)match(input,OCTAL_LITERAL,FOLLOW_OCTAL_LITERAL_in_numberLiteral527); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_OCTAL_LITERAL.add(OCTAL_LITERAL6);



                    // AST REWRITE
                    // elements: OCTAL_LITERAL, SIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 189:23: -> ^( UNARY_EXPRESSION_OCT OCTAL_LITERAL ( SIGN )* )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:189:26: ^( UNARY_EXPRESSION_OCT OCTAL_LITERAL ( SIGN )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_OCT, "UNARY_EXPRESSION_OCT"), root_1);

                        adaptor.addChild(root_1, stream_OCTAL_LITERAL.nextNode());
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:189:63: ( SIGN )*
                        while ( stream_SIGN.hasNext() ) {
                            adaptor.addChild(root_1, stream_SIGN.nextNode());

                        }
                        stream_SIGN.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "numberLiteral"

    public static class primaryExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "primaryExpression"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:193:1: primaryExpression : ( ( IDENTIFIER )=> IDENTIFIER -> ^( UNARY_EXPRESSION_STRING IDENTIFIER ) | ( ctfKeyword )=> ctfKeyword -> ^( UNARY_EXPRESSION_STRING ctfKeyword ) | ( STRING_LITERAL )=> STRING_LITERAL -> ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL ) | numberLiteral | enumConstant | CHARACTER_LITERAL );
    public final CTFParser.primaryExpression_return primaryExpression() throws RecognitionException {
        CTFParser.primaryExpression_return retval = new CTFParser.primaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER7=null;
        Token STRING_LITERAL9=null;
        Token CHARACTER_LITERAL12=null;
        CTFParser.ctfKeyword_return ctfKeyword8 = null;

        CTFParser.numberLiteral_return numberLiteral10 = null;

        CTFParser.enumConstant_return enumConstant11 = null;


        CommonTree IDENTIFIER7_tree=null;
        CommonTree STRING_LITERAL9_tree=null;
        CommonTree CHARACTER_LITERAL12_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleSubtreeStream stream_ctfKeyword=new RewriteRuleSubtreeStream(adaptor,"rule ctfKeyword");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:194:3: ( ( IDENTIFIER )=> IDENTIFIER -> ^( UNARY_EXPRESSION_STRING IDENTIFIER ) | ( ctfKeyword )=> ctfKeyword -> ^( UNARY_EXPRESSION_STRING ctfKeyword ) | ( STRING_LITERAL )=> STRING_LITERAL -> ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL ) | numberLiteral | enumConstant | CHARACTER_LITERAL )
            int alt4=6;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:194:5: ( IDENTIFIER )=> IDENTIFIER
                    {
                    IDENTIFIER7=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_primaryExpression565); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER7);



                    // AST REWRITE
                    // elements: IDENTIFIER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 195:7: -> ^( UNARY_EXPRESSION_STRING IDENTIFIER )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:195:10: ^( UNARY_EXPRESSION_STRING IDENTIFIER )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_STRING, "UNARY_EXPRESSION_STRING"), root_1);

                        adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:196:5: ( ctfKeyword )=> ctfKeyword
                    {
                    pushFollow(FOLLOW_ctfKeyword_in_primaryExpression591);
                    ctfKeyword8=ctfKeyword();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ctfKeyword.add(ctfKeyword8.getTree());


                    // AST REWRITE
                    // elements: ctfKeyword
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 196:32: -> ^( UNARY_EXPRESSION_STRING ctfKeyword )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:196:35: ^( UNARY_EXPRESSION_STRING ctfKeyword )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_STRING, "UNARY_EXPRESSION_STRING"), root_1);

                        adaptor.addChild(root_1, stream_ctfKeyword.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:197:5: ( STRING_LITERAL )=> STRING_LITERAL
                    {
                    STRING_LITERAL9=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_primaryExpression611); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING_LITERAL.add(STRING_LITERAL9);



                    // AST REWRITE
                    // elements: STRING_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 198:7: -> ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:198:10: ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_STRING_QUOTES, "UNARY_EXPRESSION_STRING_QUOTES"), root_1);

                        adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:200:5: numberLiteral
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_numberLiteral_in_primaryExpression636);
                    numberLiteral10=numberLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, numberLiteral10.getTree());

                    }
                    break;
                case 5 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:201:5: enumConstant
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_enumConstant_in_primaryExpression642);
                    enumConstant11=enumConstant();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstant11.getTree());

                    }
                    break;
                case 6 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:202:5: CHARACTER_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CHARACTER_LITERAL12=(Token)match(input,CHARACTER_LITERAL,FOLLOW_CHARACTER_LITERAL_in_primaryExpression648); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHARACTER_LITERAL12_tree = (CommonTree)adaptor.create(CHARACTER_LITERAL12);
                    adaptor.addChild(root_0, CHARACTER_LITERAL12_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "primaryExpression"

    public static class postfixExpressionSuffix_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "postfixExpressionSuffix"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:205:1: postfixExpressionSuffix : ( OPENBRAC unaryExpression CLOSEBRAC | (ref= DOT | ref= ARROW ) IDENTIFIER -> ^( $ref ^( UNARY_EXPRESSION_STRING IDENTIFIER ) ) );
    public final CTFParser.postfixExpressionSuffix_return postfixExpressionSuffix() throws RecognitionException {
        CTFParser.postfixExpressionSuffix_return retval = new CTFParser.postfixExpressionSuffix_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ref=null;
        Token OPENBRAC13=null;
        Token CLOSEBRAC15=null;
        Token IDENTIFIER16=null;
        CTFParser.unaryExpression_return unaryExpression14 = null;


        CommonTree ref_tree=null;
        CommonTree OPENBRAC13_tree=null;
        CommonTree CLOSEBRAC15_tree=null;
        CommonTree IDENTIFIER16_tree=null;
        RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:206:3: ( OPENBRAC unaryExpression CLOSEBRAC | (ref= DOT | ref= ARROW ) IDENTIFIER -> ^( $ref ^( UNARY_EXPRESSION_STRING IDENTIFIER ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==OPENBRAC) ) {
                alt6=1;
            }
            else if ( ((LA6_0>=ARROW && LA6_0<=DOT)) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:206:5: OPENBRAC unaryExpression CLOSEBRAC
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    OPENBRAC13=(Token)match(input,OPENBRAC,FOLLOW_OPENBRAC_in_postfixExpressionSuffix661); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OPENBRAC13_tree = (CommonTree)adaptor.create(OPENBRAC13);
                    adaptor.addChild(root_0, OPENBRAC13_tree);
                    }
                    pushFollow(FOLLOW_unaryExpression_in_postfixExpressionSuffix663);
                    unaryExpression14=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression14.getTree());
                    CLOSEBRAC15=(Token)match(input,CLOSEBRAC,FOLLOW_CLOSEBRAC_in_postfixExpressionSuffix665); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:207:5: (ref= DOT | ref= ARROW ) IDENTIFIER
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:207:5: (ref= DOT | ref= ARROW )
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==DOT) ) {
                        alt5=1;
                    }
                    else if ( (LA5_0==ARROW) ) {
                        alt5=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 0, input);

                        throw nvae;
                    }
                    switch (alt5) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:207:6: ref= DOT
                            {
                            ref=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpressionSuffix675); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_DOT.add(ref);


                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:207:16: ref= ARROW
                            {
                            ref=(Token)match(input,ARROW,FOLLOW_ARROW_in_postfixExpressionSuffix681); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARROW.add(ref);


                            }
                            break;

                    }

                    IDENTIFIER16=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_postfixExpressionSuffix684); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER16);



                    // AST REWRITE
                    // elements: ref, IDENTIFIER
                    // token labels: ref
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_ref=new RewriteRuleTokenStream(adaptor,"token ref",ref);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 208:7: -> ^( $ref ^( UNARY_EXPRESSION_STRING IDENTIFIER ) )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:208:10: ^( $ref ^( UNARY_EXPRESSION_STRING IDENTIFIER ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ref.nextNode(), root_1);

                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:208:17: ^( UNARY_EXPRESSION_STRING IDENTIFIER )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_STRING, "UNARY_EXPRESSION_STRING"), root_2);

                        adaptor.addChild(root_2, stream_IDENTIFIER.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "postfixExpressionSuffix"

    public static class postfixExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "postfixExpression"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:211:1: postfixExpression : ( primaryExpression ( postfixExpressionSuffix )* | ctfSpecifierHead ( postfixExpressionSuffix )+ );
    public final CTFParser.postfixExpression_return postfixExpression() throws RecognitionException {
        CTFParser.postfixExpression_return retval = new CTFParser.postfixExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.primaryExpression_return primaryExpression17 = null;

        CTFParser.postfixExpressionSuffix_return postfixExpressionSuffix18 = null;

        CTFParser.ctfSpecifierHead_return ctfSpecifierHead19 = null;

        CTFParser.postfixExpressionSuffix_return postfixExpressionSuffix20 = null;



        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:212:3: ( primaryExpression ( postfixExpressionSuffix )* | ctfSpecifierHead ( postfixExpressionSuffix )+ )
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:212:5: primaryExpression ( postfixExpressionSuffix )*
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_primaryExpression_in_postfixExpression716);
                    primaryExpression17=primaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primaryExpression17.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:212:23: ( postfixExpressionSuffix )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==OPENBRAC||(LA7_0>=ARROW && LA7_0<=DOT)) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:212:23: postfixExpressionSuffix
                    	    {
                    	    pushFollow(FOLLOW_postfixExpressionSuffix_in_postfixExpression718);
                    	    postfixExpressionSuffix18=postfixExpressionSuffix();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, postfixExpressionSuffix18.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:213:5: ctfSpecifierHead ( postfixExpressionSuffix )+
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ctfSpecifierHead_in_postfixExpression725);
                    ctfSpecifierHead19=ctfSpecifierHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ctfSpecifierHead19.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:213:22: ( postfixExpressionSuffix )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==OPENBRAC||(LA8_0>=ARROW && LA8_0<=DOT)) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:213:22: postfixExpressionSuffix
                    	    {
                    	    pushFollow(FOLLOW_postfixExpressionSuffix_in_postfixExpression727);
                    	    postfixExpressionSuffix20=postfixExpressionSuffix();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, postfixExpressionSuffix20.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "postfixExpression"

    public static class unaryExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unaryExpression"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:216:1: unaryExpression : postfixExpression ;
    public final CTFParser.unaryExpression_return unaryExpression() throws RecognitionException {
        CTFParser.unaryExpression_return retval = new CTFParser.unaryExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.postfixExpression_return postfixExpression21 = null;



        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:217:3: ( postfixExpression )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:217:5: postfixExpression
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_postfixExpression_in_unaryExpression743);
            postfixExpression21=postfixExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, postfixExpression21.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"

    public static class enumConstant_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumConstant"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:221:1: enumConstant : ( STRING_LITERAL -> ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL ) | IDENTIFIER -> ^( UNARY_EXPRESSION_STRING IDENTIFIER ) | ctfKeyword -> ^( UNARY_EXPRESSION_STRING ctfKeyword ) );
    public final CTFParser.enumConstant_return enumConstant() throws RecognitionException {
        CTFParser.enumConstant_return retval = new CTFParser.enumConstant_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STRING_LITERAL22=null;
        Token IDENTIFIER23=null;
        CTFParser.ctfKeyword_return ctfKeyword24 = null;


        CommonTree STRING_LITERAL22_tree=null;
        CommonTree IDENTIFIER23_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");
        RewriteRuleSubtreeStream stream_ctfKeyword=new RewriteRuleSubtreeStream(adaptor,"rule ctfKeyword");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:222:3: ( STRING_LITERAL -> ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL ) | IDENTIFIER -> ^( UNARY_EXPRESSION_STRING IDENTIFIER ) | ctfKeyword -> ^( UNARY_EXPRESSION_STRING ctfKeyword ) )
            int alt10=3;
            switch ( input.LA(1) ) {
            case STRING_LITERAL:
                {
                alt10=1;
                }
                break;
            case IDENTIFIER:
                {
                alt10=2;
                }
                break;
            case ALIGNTOK:
            case EVENTTOK:
            case SIGNEDTOK:
            case STRINGTOK:
                {
                alt10=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:222:5: STRING_LITERAL
                    {
                    STRING_LITERAL22=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_enumConstant760); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING_LITERAL.add(STRING_LITERAL22);



                    // AST REWRITE
                    // elements: STRING_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 222:20: -> ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:222:23: ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_STRING_QUOTES, "UNARY_EXPRESSION_STRING_QUOTES"), root_1);

                        adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:223:5: IDENTIFIER
                    {
                    IDENTIFIER23=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumConstant774); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER23);



                    // AST REWRITE
                    // elements: IDENTIFIER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 223:16: -> ^( UNARY_EXPRESSION_STRING IDENTIFIER )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:223:19: ^( UNARY_EXPRESSION_STRING IDENTIFIER )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_STRING, "UNARY_EXPRESSION_STRING"), root_1);

                        adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:224:5: ctfKeyword
                    {
                    pushFollow(FOLLOW_ctfKeyword_in_enumConstant788);
                    ctfKeyword24=ctfKeyword();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ctfKeyword.add(ctfKeyword24.getTree());


                    // AST REWRITE
                    // elements: ctfKeyword
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 224:16: -> ^( UNARY_EXPRESSION_STRING ctfKeyword )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:224:19: ^( UNARY_EXPRESSION_STRING ctfKeyword )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(UNARY_EXPRESSION_STRING, "UNARY_EXPRESSION_STRING"), root_1);

                        adaptor.addChild(root_1, stream_ctfKeyword.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumConstant"

    public static class declaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declaration"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:229:1: declaration : ( declarationSpecifiers ( declaratorList )? TERM -> {inTypedef()}? ^( DECLARATION ^( TYPEDEF declaratorList declarationSpecifiers ) ) -> ^( DECLARATION declarationSpecifiers ( declaratorList )? ) | ctfSpecifier TERM );
    public final CTFParser.declaration_return declaration() throws RecognitionException {
        CTFParser.declaration_return retval = new CTFParser.declaration_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TERM27=null;
        Token TERM29=null;
        CTFParser.declarationSpecifiers_return declarationSpecifiers25 = null;

        CTFParser.declaratorList_return declaratorList26 = null;

        CTFParser.ctfSpecifier_return ctfSpecifier28 = null;


        CommonTree TERM27_tree=null;
        CommonTree TERM29_tree=null;
        RewriteRuleTokenStream stream_TERM=new RewriteRuleTokenStream(adaptor,"token TERM");
        RewriteRuleSubtreeStream stream_declaratorList=new RewriteRuleSubtreeStream(adaptor,"rule declaratorList");
        RewriteRuleSubtreeStream stream_declarationSpecifiers=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifiers");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:235:3: ( declarationSpecifiers ( declaratorList )? TERM -> {inTypedef()}? ^( DECLARATION ^( TYPEDEF declaratorList declarationSpecifiers ) ) -> ^( DECLARATION declarationSpecifiers ( declaratorList )? ) | ctfSpecifier TERM )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=CONSTTOK && LA12_0<=ENUMTOK)||(LA12_0>=FLOATINGPOINTTOK && LA12_0<=SIGNEDTOK)||(LA12_0>=STRINGTOK && LA12_0<=STRUCTTOK)||(LA12_0>=TYPEDEFTOK && LA12_0<=IMAGINARYTOK)) ) {
                alt12=1;
            }
            else if ( (LA12_0==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {
                alt12=1;
            }
            else if ( (LA12_0==EVENTTOK||LA12_0==STREAMTOK||(LA12_0>=TRACETOK && LA12_0<=TYPEALIASTOK)||(LA12_0>=ENVTOK && LA12_0<=CALLSITETOK)) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:235:5: declarationSpecifiers ( declaratorList )? TERM
                    {
                    pushFollow(FOLLOW_declarationSpecifiers_in_declaration816);
                    declarationSpecifiers25=declarationSpecifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declarationSpecifiers.add(declarationSpecifiers25.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:235:27: ( declaratorList )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==POINTER||LA11_0==IDENTIFIER) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:235:27: declaratorList
                            {
                            pushFollow(FOLLOW_declaratorList_in_declaration818);
                            declaratorList26=declaratorList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_declaratorList.add(declaratorList26.getTree());

                            }
                            break;

                    }

                    TERM27=(Token)match(input,TERM,FOLLOW_TERM_in_declaration821); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TERM.add(TERM27);



                    // AST REWRITE
                    // elements: declaratorList, declarationSpecifiers, declaratorList, declarationSpecifiers
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 238:7: -> {inTypedef()}? ^( DECLARATION ^( TYPEDEF declaratorList declarationSpecifiers ) )
                    if (inTypedef()) {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:239:10: ^( DECLARATION ^( TYPEDEF declaratorList declarationSpecifiers ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:239:24: ^( TYPEDEF declaratorList declarationSpecifiers )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPEDEF, "TYPEDEF"), root_2);

                        adaptor.addChild(root_2, stream_declaratorList.nextTree());
                        adaptor.addChild(root_2, stream_declarationSpecifiers.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 240:7: -> ^( DECLARATION declarationSpecifiers ( declaratorList )? )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:240:10: ^( DECLARATION declarationSpecifiers ( declaratorList )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                        adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:240:46: ( declaratorList )?
                        if ( stream_declaratorList.hasNext() ) {
                            adaptor.addChild(root_1, stream_declaratorList.nextTree());

                        }
                        stream_declaratorList.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:241:5: ctfSpecifier TERM
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ctfSpecifier_in_declaration889);
                    ctfSpecifier28=ctfSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ctfSpecifier28.getTree());
                    TERM29=(Token)match(input,TERM,FOLLOW_TERM_in_declaration891); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (inTypedef()) {
                      typedefOff();
                  }

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declaration"

    public static class declarationSpecifiers_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declarationSpecifiers"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:244:1: declarationSpecifiers : ( storageClassSpecifier | typeQualifier | typeSpecifier )+ -> ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* ) ;
    public final CTFParser.declarationSpecifiers_return declarationSpecifiers() throws RecognitionException {
        CTFParser.declarationSpecifiers_return retval = new CTFParser.declarationSpecifiers_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.storageClassSpecifier_return storageClassSpecifier30 = null;

        CTFParser.typeQualifier_return typeQualifier31 = null;

        CTFParser.typeSpecifier_return typeSpecifier32 = null;


        RewriteRuleSubtreeStream stream_typeSpecifier=new RewriteRuleSubtreeStream(adaptor,"rule typeSpecifier");
        RewriteRuleSubtreeStream stream_typeQualifier=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifier");
        RewriteRuleSubtreeStream stream_storageClassSpecifier=new RewriteRuleSubtreeStream(adaptor,"rule storageClassSpecifier");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:245:3: ( ( storageClassSpecifier | typeQualifier | typeSpecifier )+ -> ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:245:5: ( storageClassSpecifier | typeQualifier | typeSpecifier )+
            {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:245:5: ( storageClassSpecifier | typeQualifier | typeSpecifier )+
            int cnt13=0;
            loop13:
            do {
                int alt13=4;
                switch ( input.LA(1) ) {
                case IDENTIFIER:
                    {
                    int LA13_2 = input.LA(2);

                    if ( ((( inTypealiasAlias() || isTypeName(input.LT(1).getText()) )&&(inTypealiasAlias() || isTypeName(input.LT(1).getText())))) ) {
                        alt13=3;
                    }


                    }
                    break;
                case TYPEDEFTOK:
                    {
                    alt13=1;
                    }
                    break;
                case CONSTTOK:
                    {
                    alt13=2;
                    }
                    break;
                case CHARTOK:
                case DOUBLETOK:
                case ENUMTOK:
                case FLOATINGPOINTTOK:
                case FLOATTOK:
                case INTEGERTOK:
                case INTTOK:
                case LONGTOK:
                case SHORTTOK:
                case SIGNEDTOK:
                case STRINGTOK:
                case STRUCTTOK:
                case UNSIGNEDTOK:
                case VARIANTTOK:
                case VOIDTOK:
                case BOOLTOK:
                case COMPLEXTOK:
                case IMAGINARYTOK:
                    {
                    alt13=3;
                    }
                    break;

                }

                switch (alt13) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:248:9: storageClassSpecifier
            	    {
            	    pushFollow(FOLLOW_storageClassSpecifier_in_declarationSpecifiers929);
            	    storageClassSpecifier30=storageClassSpecifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_storageClassSpecifier.add(storageClassSpecifier30.getTree());

            	    }
            	    break;
            	case 2 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:249:9: typeQualifier
            	    {
            	    pushFollow(FOLLOW_typeQualifier_in_declarationSpecifiers939);
            	    typeQualifier31=typeQualifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeQualifier.add(typeQualifier31.getTree());

            	    }
            	    break;
            	case 3 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:250:9: typeSpecifier
            	    {
            	    pushFollow(FOLLOW_typeSpecifier_in_declarationSpecifiers949);
            	    typeSpecifier32=typeSpecifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeSpecifier.add(typeSpecifier32.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);



            // AST REWRITE
            // elements: typeSpecifier, typeQualifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 251:6: -> ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:251:9: ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_SPECIFIER_LIST, "TYPE_SPECIFIER_LIST"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:251:31: ( typeQualifier )*
                while ( stream_typeQualifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeQualifier.nextTree());

                }
                stream_typeQualifier.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:251:46: ( typeSpecifier )*
                while ( stream_typeSpecifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeSpecifier.nextTree());

                }
                stream_typeSpecifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declarationSpecifiers"

    public static class declaratorList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declaratorList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:254:1: declaratorList : declarator ( SEPARATOR declarator )* -> ^( TYPE_DECLARATOR_LIST ( declarator )+ ) ;
    public final CTFParser.declaratorList_return declaratorList() throws RecognitionException {
        CTFParser.declaratorList_return retval = new CTFParser.declaratorList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SEPARATOR34=null;
        CTFParser.declarator_return declarator33 = null;

        CTFParser.declarator_return declarator35 = null;


        CommonTree SEPARATOR34_tree=null;
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleSubtreeStream stream_declarator=new RewriteRuleSubtreeStream(adaptor,"rule declarator");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:255:3: ( declarator ( SEPARATOR declarator )* -> ^( TYPE_DECLARATOR_LIST ( declarator )+ ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:255:5: declarator ( SEPARATOR declarator )*
            {
            pushFollow(FOLLOW_declarator_in_declaratorList979);
            declarator33=declarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarator.add(declarator33.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:255:16: ( SEPARATOR declarator )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==SEPARATOR) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:255:17: SEPARATOR declarator
            	    {
            	    SEPARATOR34=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_declaratorList982); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SEPARATOR.add(SEPARATOR34);

            	    pushFollow(FOLLOW_declarator_in_declaratorList984);
            	    declarator35=declarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_declarator.add(declarator35.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);



            // AST REWRITE
            // elements: declarator
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 256:7: -> ^( TYPE_DECLARATOR_LIST ( declarator )+ )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:256:10: ^( TYPE_DECLARATOR_LIST ( declarator )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_DECLARATOR_LIST, "TYPE_DECLARATOR_LIST"), root_1);

                if ( !(stream_declarator.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_declarator.hasNext() ) {
                    adaptor.addChild(root_1, stream_declarator.nextTree());

                }
                stream_declarator.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declaratorList"

    public static class abstractDeclaratorList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "abstractDeclaratorList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:259:1: abstractDeclaratorList : abstractDeclarator ( SEPARATOR abstractDeclarator )* -> ^( TYPE_DECLARATOR_LIST ( abstractDeclarator )+ ) ;
    public final CTFParser.abstractDeclaratorList_return abstractDeclaratorList() throws RecognitionException {
        CTFParser.abstractDeclaratorList_return retval = new CTFParser.abstractDeclaratorList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SEPARATOR37=null;
        CTFParser.abstractDeclarator_return abstractDeclarator36 = null;

        CTFParser.abstractDeclarator_return abstractDeclarator38 = null;


        CommonTree SEPARATOR37_tree=null;
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleSubtreeStream stream_abstractDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule abstractDeclarator");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:260:3: ( abstractDeclarator ( SEPARATOR abstractDeclarator )* -> ^( TYPE_DECLARATOR_LIST ( abstractDeclarator )+ ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:260:5: abstractDeclarator ( SEPARATOR abstractDeclarator )*
            {
            pushFollow(FOLLOW_abstractDeclarator_in_abstractDeclaratorList1014);
            abstractDeclarator36=abstractDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_abstractDeclarator.add(abstractDeclarator36.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:260:24: ( SEPARATOR abstractDeclarator )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==SEPARATOR) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:260:25: SEPARATOR abstractDeclarator
            	    {
            	    SEPARATOR37=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_abstractDeclaratorList1017); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SEPARATOR.add(SEPARATOR37);

            	    pushFollow(FOLLOW_abstractDeclarator_in_abstractDeclaratorList1019);
            	    abstractDeclarator38=abstractDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_abstractDeclarator.add(abstractDeclarator38.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);



            // AST REWRITE
            // elements: abstractDeclarator
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 261:7: -> ^( TYPE_DECLARATOR_LIST ( abstractDeclarator )+ )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:261:10: ^( TYPE_DECLARATOR_LIST ( abstractDeclarator )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_DECLARATOR_LIST, "TYPE_DECLARATOR_LIST"), root_1);

                if ( !(stream_abstractDeclarator.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_abstractDeclarator.hasNext() ) {
                    adaptor.addChild(root_1, stream_abstractDeclarator.nextTree());

                }
                stream_abstractDeclarator.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "abstractDeclaratorList"

    public static class storageClassSpecifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "storageClassSpecifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:264:1: storageClassSpecifier : TYPEDEFTOK ;
    public final CTFParser.storageClassSpecifier_return storageClassSpecifier() throws RecognitionException {
        CTFParser.storageClassSpecifier_return retval = new CTFParser.storageClassSpecifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TYPEDEFTOK39=null;

        CommonTree TYPEDEFTOK39_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:265:3: ( TYPEDEFTOK )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:265:5: TYPEDEFTOK
            {
            root_0 = (CommonTree)adaptor.nil();

            TYPEDEFTOK39=(Token)match(input,TYPEDEFTOK,FOLLOW_TYPEDEFTOK_in_storageClassSpecifier1049); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TYPEDEFTOK39_tree = (CommonTree)adaptor.create(TYPEDEFTOK39);
            adaptor.addChild(root_0, TYPEDEFTOK39_tree);
            }
            if ( state.backtracking==0 ) {
               typedefOn(); 
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "storageClassSpecifier"

    public static class typeSpecifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeSpecifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:268:1: typeSpecifier : ( FLOATTOK | INTTOK | LONGTOK | SHORTTOK | SIGNEDTOK | UNSIGNEDTOK | CHARTOK | DOUBLETOK | VOIDTOK | BOOLTOK | COMPLEXTOK | IMAGINARYTOK | structSpecifier | variantSpecifier | enumSpecifier | ctfTypeSpecifier | {...}? => typedefName );
    public final CTFParser.typeSpecifier_return typeSpecifier() throws RecognitionException {
        CTFParser.typeSpecifier_return retval = new CTFParser.typeSpecifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token FLOATTOK40=null;
        Token INTTOK41=null;
        Token LONGTOK42=null;
        Token SHORTTOK43=null;
        Token SIGNEDTOK44=null;
        Token UNSIGNEDTOK45=null;
        Token CHARTOK46=null;
        Token DOUBLETOK47=null;
        Token VOIDTOK48=null;
        Token BOOLTOK49=null;
        Token COMPLEXTOK50=null;
        Token IMAGINARYTOK51=null;
        CTFParser.structSpecifier_return structSpecifier52 = null;

        CTFParser.variantSpecifier_return variantSpecifier53 = null;

        CTFParser.enumSpecifier_return enumSpecifier54 = null;

        CTFParser.ctfTypeSpecifier_return ctfTypeSpecifier55 = null;

        CTFParser.typedefName_return typedefName56 = null;


        CommonTree FLOATTOK40_tree=null;
        CommonTree INTTOK41_tree=null;
        CommonTree LONGTOK42_tree=null;
        CommonTree SHORTTOK43_tree=null;
        CommonTree SIGNEDTOK44_tree=null;
        CommonTree UNSIGNEDTOK45_tree=null;
        CommonTree CHARTOK46_tree=null;
        CommonTree DOUBLETOK47_tree=null;
        CommonTree VOIDTOK48_tree=null;
        CommonTree BOOLTOK49_tree=null;
        CommonTree COMPLEXTOK50_tree=null;
        CommonTree IMAGINARYTOK51_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:269:3: ( FLOATTOK | INTTOK | LONGTOK | SHORTTOK | SIGNEDTOK | UNSIGNEDTOK | CHARTOK | DOUBLETOK | VOIDTOK | BOOLTOK | COMPLEXTOK | IMAGINARYTOK | structSpecifier | variantSpecifier | enumSpecifier | ctfTypeSpecifier | {...}? => typedefName )
            int alt16=17;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:269:5: FLOATTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    FLOATTOK40=(Token)match(input,FLOATTOK,FOLLOW_FLOATTOK_in_typeSpecifier1065); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOATTOK40_tree = (CommonTree)adaptor.create(FLOATTOK40);
                    adaptor.addChild(root_0, FLOATTOK40_tree);
                    }

                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:270:5: INTTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INTTOK41=(Token)match(input,INTTOK,FOLLOW_INTTOK_in_typeSpecifier1071); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTTOK41_tree = (CommonTree)adaptor.create(INTTOK41);
                    adaptor.addChild(root_0, INTTOK41_tree);
                    }

                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:271:5: LONGTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LONGTOK42=(Token)match(input,LONGTOK,FOLLOW_LONGTOK_in_typeSpecifier1077); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LONGTOK42_tree = (CommonTree)adaptor.create(LONGTOK42);
                    adaptor.addChild(root_0, LONGTOK42_tree);
                    }

                    }
                    break;
                case 4 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:272:5: SHORTTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    SHORTTOK43=(Token)match(input,SHORTTOK,FOLLOW_SHORTTOK_in_typeSpecifier1083); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SHORTTOK43_tree = (CommonTree)adaptor.create(SHORTTOK43);
                    adaptor.addChild(root_0, SHORTTOK43_tree);
                    }

                    }
                    break;
                case 5 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:273:5: SIGNEDTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    SIGNEDTOK44=(Token)match(input,SIGNEDTOK,FOLLOW_SIGNEDTOK_in_typeSpecifier1089); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SIGNEDTOK44_tree = (CommonTree)adaptor.create(SIGNEDTOK44);
                    adaptor.addChild(root_0, SIGNEDTOK44_tree);
                    }

                    }
                    break;
                case 6 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:274:5: UNSIGNEDTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    UNSIGNEDTOK45=(Token)match(input,UNSIGNEDTOK,FOLLOW_UNSIGNEDTOK_in_typeSpecifier1095); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    UNSIGNEDTOK45_tree = (CommonTree)adaptor.create(UNSIGNEDTOK45);
                    adaptor.addChild(root_0, UNSIGNEDTOK45_tree);
                    }

                    }
                    break;
                case 7 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:275:5: CHARTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CHARTOK46=(Token)match(input,CHARTOK,FOLLOW_CHARTOK_in_typeSpecifier1101); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHARTOK46_tree = (CommonTree)adaptor.create(CHARTOK46);
                    adaptor.addChild(root_0, CHARTOK46_tree);
                    }

                    }
                    break;
                case 8 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:276:5: DOUBLETOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    DOUBLETOK47=(Token)match(input,DOUBLETOK,FOLLOW_DOUBLETOK_in_typeSpecifier1107); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLETOK47_tree = (CommonTree)adaptor.create(DOUBLETOK47);
                    adaptor.addChild(root_0, DOUBLETOK47_tree);
                    }

                    }
                    break;
                case 9 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:277:5: VOIDTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    VOIDTOK48=(Token)match(input,VOIDTOK,FOLLOW_VOIDTOK_in_typeSpecifier1113); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VOIDTOK48_tree = (CommonTree)adaptor.create(VOIDTOK48);
                    adaptor.addChild(root_0, VOIDTOK48_tree);
                    }

                    }
                    break;
                case 10 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:278:5: BOOLTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    BOOLTOK49=(Token)match(input,BOOLTOK,FOLLOW_BOOLTOK_in_typeSpecifier1119); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOLTOK49_tree = (CommonTree)adaptor.create(BOOLTOK49);
                    adaptor.addChild(root_0, BOOLTOK49_tree);
                    }

                    }
                    break;
                case 11 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:279:5: COMPLEXTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    COMPLEXTOK50=(Token)match(input,COMPLEXTOK,FOLLOW_COMPLEXTOK_in_typeSpecifier1125); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMPLEXTOK50_tree = (CommonTree)adaptor.create(COMPLEXTOK50);
                    adaptor.addChild(root_0, COMPLEXTOK50_tree);
                    }

                    }
                    break;
                case 12 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:280:5: IMAGINARYTOK
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    IMAGINARYTOK51=(Token)match(input,IMAGINARYTOK,FOLLOW_IMAGINARYTOK_in_typeSpecifier1131); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IMAGINARYTOK51_tree = (CommonTree)adaptor.create(IMAGINARYTOK51);
                    adaptor.addChild(root_0, IMAGINARYTOK51_tree);
                    }

                    }
                    break;
                case 13 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:281:5: structSpecifier
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_structSpecifier_in_typeSpecifier1137);
                    structSpecifier52=structSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, structSpecifier52.getTree());

                    }
                    break;
                case 14 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:282:5: variantSpecifier
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_variantSpecifier_in_typeSpecifier1143);
                    variantSpecifier53=variantSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variantSpecifier53.getTree());

                    }
                    break;
                case 15 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:283:5: enumSpecifier
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_enumSpecifier_in_typeSpecifier1149);
                    enumSpecifier54=enumSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumSpecifier54.getTree());

                    }
                    break;
                case 16 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:284:5: ctfTypeSpecifier
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ctfTypeSpecifier_in_typeSpecifier1155);
                    ctfTypeSpecifier55=ctfTypeSpecifier();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ctfTypeSpecifier55.getTree());

                    }
                    break;
                case 17 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:285:5: {...}? => typedefName
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    if ( !(( inTypealiasAlias() || isTypeName(input.LT(1).getText()) )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "typeSpecifier", " inTypealiasAlias() || isTypeName(input.LT(1).getText()) ");
                    }
                    pushFollow(FOLLOW_typedefName_in_typeSpecifier1165);
                    typedefName56=typedefName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typedefName56.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeSpecifier"

    public static class typeQualifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeQualifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:288:1: typeQualifier : CONSTTOK ;
    public final CTFParser.typeQualifier_return typeQualifier() throws RecognitionException {
        CTFParser.typeQualifier_return retval = new CTFParser.typeQualifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CONSTTOK57=null;

        CommonTree CONSTTOK57_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:289:3: ( CONSTTOK )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:289:5: CONSTTOK
            {
            root_0 = (CommonTree)adaptor.nil();

            CONSTTOK57=(Token)match(input,CONSTTOK,FOLLOW_CONSTTOK_in_typeQualifier1178); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CONSTTOK57_tree = (CommonTree)adaptor.create(CONSTTOK57);
            adaptor.addChild(root_0, CONSTTOK57_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeQualifier"

    public static class alignAttribute_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "alignAttribute"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:292:1: alignAttribute : ALIGNTOK LPAREN unaryExpression RPAREN -> ^( ALIGN unaryExpression ) ;
    public final CTFParser.alignAttribute_return alignAttribute() throws RecognitionException {
        CTFParser.alignAttribute_return retval = new CTFParser.alignAttribute_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ALIGNTOK58=null;
        Token LPAREN59=null;
        Token RPAREN61=null;
        CTFParser.unaryExpression_return unaryExpression60 = null;


        CommonTree ALIGNTOK58_tree=null;
        CommonTree LPAREN59_tree=null;
        CommonTree RPAREN61_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ALIGNTOK=new RewriteRuleTokenStream(adaptor,"token ALIGNTOK");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:293:3: ( ALIGNTOK LPAREN unaryExpression RPAREN -> ^( ALIGN unaryExpression ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:293:5: ALIGNTOK LPAREN unaryExpression RPAREN
            {
            ALIGNTOK58=(Token)match(input,ALIGNTOK,FOLLOW_ALIGNTOK_in_alignAttribute1191); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ALIGNTOK.add(ALIGNTOK58);

            LPAREN59=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_alignAttribute1193); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN59);

            pushFollow(FOLLOW_unaryExpression_in_alignAttribute1195);
            unaryExpression60=unaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression60.getTree());
            RPAREN61=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_alignAttribute1197); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN61);



            // AST REWRITE
            // elements: unaryExpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 293:44: -> ^( ALIGN unaryExpression )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:293:47: ^( ALIGN unaryExpression )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALIGN, "ALIGN"), root_1);

                adaptor.addChild(root_1, stream_unaryExpression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "alignAttribute"

    public static class structBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structBody"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:297:1: structBody : LCURL ( structOrVariantDeclarationList )? RCURL -> ^( STRUCT_BODY ( structOrVariantDeclarationList )? ) ;
    public final CTFParser.structBody_return structBody() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CTFParser.structBody_return retval = new CTFParser.structBody_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LCURL62=null;
        Token RCURL64=null;
        CTFParser.structOrVariantDeclarationList_return structOrVariantDeclarationList63 = null;


        CommonTree LCURL62_tree=null;
        CommonTree RCURL64_tree=null;
        RewriteRuleTokenStream stream_LCURL=new RewriteRuleTokenStream(adaptor,"token LCURL");
        RewriteRuleTokenStream stream_RCURL=new RewriteRuleTokenStream(adaptor,"token RCURL");
        RewriteRuleSubtreeStream stream_structOrVariantDeclarationList=new RewriteRuleSubtreeStream(adaptor,"rule structOrVariantDeclarationList");

            ((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:302:3: ( LCURL ( structOrVariantDeclarationList )? RCURL -> ^( STRUCT_BODY ( structOrVariantDeclarationList )? ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:302:5: LCURL ( structOrVariantDeclarationList )? RCURL
            {
            LCURL62=(Token)match(input,LCURL,FOLLOW_LCURL_in_structBody1231); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURL.add(LCURL62);

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:302:11: ( structOrVariantDeclarationList )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=CONSTTOK && LA17_0<=ENUMTOK)||(LA17_0>=FLOATINGPOINTTOK && LA17_0<=SIGNEDTOK)||(LA17_0>=STRINGTOK && LA17_0<=STRUCTTOK)||(LA17_0>=TYPEDEFTOK && LA17_0<=IMAGINARYTOK)) ) {
                alt17=1;
            }
            else if ( (LA17_0==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {
                alt17=1;
            }
            else if ( (LA17_0==TYPEALIASTOK) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:302:11: structOrVariantDeclarationList
                    {
                    pushFollow(FOLLOW_structOrVariantDeclarationList_in_structBody1233);
                    structOrVariantDeclarationList63=structOrVariantDeclarationList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_structOrVariantDeclarationList.add(structOrVariantDeclarationList63.getTree());

                    }
                    break;

            }

            RCURL64=(Token)match(input,RCURL,FOLLOW_RCURL_in_structBody1236); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURL.add(RCURL64);



            // AST REWRITE
            // elements: structOrVariantDeclarationList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 303:7: -> ^( STRUCT_BODY ( structOrVariantDeclarationList )? )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:303:10: ^( STRUCT_BODY ( structOrVariantDeclarationList )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(STRUCT_BODY, "STRUCT_BODY"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:303:24: ( structOrVariantDeclarationList )?
                if ( stream_structOrVariantDeclarationList.hasNext() ) {
                    adaptor.addChild(root_1, stream_structOrVariantDeclarationList.nextTree());

                }
                stream_structOrVariantDeclarationList.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "structBody"

    public static class structSpecifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structSpecifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:306:1: structSpecifier : STRUCTTOK ( ( structName ( alignAttribute | ( structBody ( alignAttribute | ) ) | ) ) | ( structBody ( alignAttribute | ) ) ) -> ^( STRUCT ( structName )? ( structBody )? ( alignAttribute )? ) ;
    public final CTFParser.structSpecifier_return structSpecifier() throws RecognitionException {
        CTFParser.structSpecifier_return retval = new CTFParser.structSpecifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STRUCTTOK65=null;
        CTFParser.structName_return structName66 = null;

        CTFParser.alignAttribute_return alignAttribute67 = null;

        CTFParser.structBody_return structBody68 = null;

        CTFParser.alignAttribute_return alignAttribute69 = null;

        CTFParser.structBody_return structBody70 = null;

        CTFParser.alignAttribute_return alignAttribute71 = null;


        CommonTree STRUCTTOK65_tree=null;
        RewriteRuleTokenStream stream_STRUCTTOK=new RewriteRuleTokenStream(adaptor,"token STRUCTTOK");
        RewriteRuleSubtreeStream stream_structName=new RewriteRuleSubtreeStream(adaptor,"rule structName");
        RewriteRuleSubtreeStream stream_structBody=new RewriteRuleSubtreeStream(adaptor,"rule structBody");
        RewriteRuleSubtreeStream stream_alignAttribute=new RewriteRuleSubtreeStream(adaptor,"rule alignAttribute");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:307:3: ( STRUCTTOK ( ( structName ( alignAttribute | ( structBody ( alignAttribute | ) ) | ) ) | ( structBody ( alignAttribute | ) ) ) -> ^( STRUCT ( structName )? ( structBody )? ( alignAttribute )? ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:307:5: STRUCTTOK ( ( structName ( alignAttribute | ( structBody ( alignAttribute | ) ) | ) ) | ( structBody ( alignAttribute | ) ) )
            {
            STRUCTTOK65=(Token)match(input,STRUCTTOK,FOLLOW_STRUCTTOK_in_structSpecifier1264); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRUCTTOK.add(STRUCTTOK65);

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:308:3: ( ( structName ( alignAttribute | ( structBody ( alignAttribute | ) ) | ) ) | ( structBody ( alignAttribute | ) ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==IDENTIFIER) ) {
                alt21=1;
            }
            else if ( (LA21_0==LCURL) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:310:5: ( structName ( alignAttribute | ( structBody ( alignAttribute | ) ) | ) )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:310:5: ( structName ( alignAttribute | ( structBody ( alignAttribute | ) ) | ) )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:311:9: structName ( alignAttribute | ( structBody ( alignAttribute | ) ) | )
                    {
                    pushFollow(FOLLOW_structName_in_structSpecifier1289);
                    structName66=structName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_structName.add(structName66.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:312:9: ( alignAttribute | ( structBody ( alignAttribute | ) ) | )
                    int alt19=3;
                    switch ( input.LA(1) ) {
                    case ALIGNTOK:
                        {
                        alt19=1;
                        }
                        break;
                    case LCURL:
                        {
                        switch ( input.LA(2) ) {
                        case ALIGNTOK:
                        case EVENTTOK:
                        case STRING_LITERAL:
                            {
                            alt19=3;
                            }
                            break;
                        case IDENTIFIER:
                            {
                            int LA19_4 = input.LA(3);

                            if ( (LA19_4==SEPARATOR||LA19_4==ASSIGNMENT||LA19_4==RCURL) ) {
                                alt19=3;
                            }
                            else if ( ((LA19_4>=CONSTTOK && LA19_4<=ENUMTOK)||(LA19_4>=FLOATINGPOINTTOK && LA19_4<=SIGNEDTOK)||(LA19_4>=STRINGTOK && LA19_4<=STRUCTTOK)||(LA19_4>=TYPEDEFTOK && LA19_4<=IMAGINARYTOK)||LA19_4==POINTER||LA19_4==IDENTIFIER) ) {
                                alt19=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 19, 4, input);

                                throw nvae;
                            }
                            }
                            break;
                        case SIGNEDTOK:
                            {
                            int LA19_5 = input.LA(3);

                            if ( (LA19_5==SEPARATOR||LA19_5==ASSIGNMENT||LA19_5==RCURL) ) {
                                alt19=3;
                            }
                            else if ( ((LA19_5>=CONSTTOK && LA19_5<=ENUMTOK)||(LA19_5>=FLOATINGPOINTTOK && LA19_5<=SIGNEDTOK)||(LA19_5>=STRINGTOK && LA19_5<=STRUCTTOK)||(LA19_5>=TYPEDEFTOK && LA19_5<=IMAGINARYTOK)||LA19_5==POINTER||LA19_5==IDENTIFIER) ) {
                                alt19=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 19, 5, input);

                                throw nvae;
                            }
                            }
                            break;
                        case CONSTTOK:
                        case CHARTOK:
                        case DOUBLETOK:
                        case ENUMTOK:
                        case FLOATINGPOINTTOK:
                        case FLOATTOK:
                        case INTEGERTOK:
                        case INTTOK:
                        case LONGTOK:
                        case SHORTTOK:
                        case STRUCTTOK:
                        case TYPEALIASTOK:
                        case TYPEDEFTOK:
                        case UNSIGNEDTOK:
                        case VARIANTTOK:
                        case VOIDTOK:
                        case BOOLTOK:
                        case COMPLEXTOK:
                        case IMAGINARYTOK:
                        case RCURL:
                            {
                            alt19=2;
                            }
                            break;
                        case STRINGTOK:
                            {
                            int LA19_7 = input.LA(3);

                            if ( (LA19_7==SEPARATOR||LA19_7==ASSIGNMENT||LA19_7==RCURL) ) {
                                alt19=3;
                            }
                            else if ( ((LA19_7>=CONSTTOK && LA19_7<=ENUMTOK)||(LA19_7>=FLOATINGPOINTTOK && LA19_7<=SIGNEDTOK)||(LA19_7>=STRINGTOK && LA19_7<=STRUCTTOK)||(LA19_7>=TYPEDEFTOK && LA19_7<=IMAGINARYTOK)||LA19_7==LCURL||LA19_7==POINTER||LA19_7==IDENTIFIER) ) {
                                alt19=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 19, 7, input);

                                throw nvae;
                            }
                            }
                            break;
                        default:
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 19, 2, input);

                            throw nvae;
                        }

                        }
                        break;
                    case EOF:
                    case CONSTTOK:
                    case CHARTOK:
                    case DOUBLETOK:
                    case ENUMTOK:
                    case FLOATINGPOINTTOK:
                    case FLOATTOK:
                    case INTEGERTOK:
                    case INTTOK:
                    case LONGTOK:
                    case SHORTTOK:
                    case SIGNEDTOK:
                    case STRINGTOK:
                    case STRUCTTOK:
                    case TYPEDEFTOK:
                    case UNSIGNEDTOK:
                    case VARIANTTOK:
                    case VOIDTOK:
                    case BOOLTOK:
                    case COMPLEXTOK:
                    case IMAGINARYTOK:
                    case TYPE_ASSIGNMENT:
                    case LPAREN:
                    case TERM:
                    case POINTER:
                    case IDENTIFIER:
                        {
                        alt19=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }

                    switch (alt19) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:313:11: alignAttribute
                            {
                            pushFollow(FOLLOW_alignAttribute_in_structSpecifier1311);
                            alignAttribute67=alignAttribute();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_alignAttribute.add(alignAttribute67.getTree());

                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:315:11: ( structBody ( alignAttribute | ) )
                            {
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:315:11: ( structBody ( alignAttribute | ) )
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:316:13: structBody ( alignAttribute | )
                            {
                            pushFollow(FOLLOW_structBody_in_structSpecifier1347);
                            structBody68=structBody();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_structBody.add(structBody68.getTree());
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:317:13: ( alignAttribute | )
                            int alt18=2;
                            int LA18_0 = input.LA(1);

                            if ( (LA18_0==ALIGNTOK) ) {
                                alt18=1;
                            }
                            else if ( (LA18_0==EOF||(LA18_0>=CONSTTOK && LA18_0<=ENUMTOK)||(LA18_0>=FLOATINGPOINTTOK && LA18_0<=SIGNEDTOK)||(LA18_0>=STRINGTOK && LA18_0<=STRUCTTOK)||(LA18_0>=TYPEDEFTOK && LA18_0<=IMAGINARYTOK)||LA18_0==TYPE_ASSIGNMENT||LA18_0==LPAREN||LA18_0==LCURL||(LA18_0>=TERM && LA18_0<=POINTER)||LA18_0==IDENTIFIER) ) {
                                alt18=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 18, 0, input);

                                throw nvae;
                            }
                            switch (alt18) {
                                case 1 :
                                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:318:14: alignAttribute
                                    {
                                    pushFollow(FOLLOW_alignAttribute_in_structSpecifier1378);
                                    alignAttribute69=alignAttribute();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_alignAttribute.add(alignAttribute69.getTree());

                                    }
                                    break;
                                case 2 :
                                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:321:13: 
                                    {
                                    }
                                    break;

                            }


                            }


                            }
                            break;
                        case 3 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:325:9: 
                            {
                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:329:5: ( structBody ( alignAttribute | ) )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:329:5: ( structBody ( alignAttribute | ) )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:330:7: structBody ( alignAttribute | )
                    {
                    pushFollow(FOLLOW_structBody_in_structSpecifier1494);
                    structBody70=structBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_structBody.add(structBody70.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:331:7: ( alignAttribute | )
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ALIGNTOK) ) {
                        alt20=1;
                    }
                    else if ( (LA20_0==EOF||(LA20_0>=CONSTTOK && LA20_0<=ENUMTOK)||(LA20_0>=FLOATINGPOINTTOK && LA20_0<=SIGNEDTOK)||(LA20_0>=STRINGTOK && LA20_0<=STRUCTTOK)||(LA20_0>=TYPEDEFTOK && LA20_0<=IMAGINARYTOK)||LA20_0==TYPE_ASSIGNMENT||LA20_0==LPAREN||LA20_0==LCURL||(LA20_0>=TERM && LA20_0<=POINTER)||LA20_0==IDENTIFIER) ) {
                        alt20=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 0, input);

                        throw nvae;
                    }
                    switch (alt20) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:332:9: alignAttribute
                            {
                            pushFollow(FOLLOW_alignAttribute_in_structSpecifier1512);
                            alignAttribute71=alignAttribute();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_alignAttribute.add(alignAttribute71.getTree());

                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:335:7: 
                            {
                            }
                            break;

                    }


                    }


                    }
                    break;

            }



            // AST REWRITE
            // elements: structName, alignAttribute, structBody
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 337:5: -> ^( STRUCT ( structName )? ( structBody )? ( alignAttribute )? )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:337:8: ^( STRUCT ( structName )? ( structBody )? ( alignAttribute )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(STRUCT, "STRUCT"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:337:17: ( structName )?
                if ( stream_structName.hasNext() ) {
                    adaptor.addChild(root_1, stream_structName.nextTree());

                }
                stream_structName.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:337:29: ( structBody )?
                if ( stream_structBody.hasNext() ) {
                    adaptor.addChild(root_1, stream_structBody.nextTree());

                }
                stream_structBody.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:337:41: ( alignAttribute )?
                if ( stream_alignAttribute.hasNext() ) {
                    adaptor.addChild(root_1, stream_alignAttribute.nextTree());

                }
                stream_alignAttribute.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "structSpecifier"

    public static class structName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structName"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:340:1: structName : IDENTIFIER -> ^( STRUCT_NAME IDENTIFIER ) ;
    public final CTFParser.structName_return structName() throws RecognitionException {
        CTFParser.structName_return retval = new CTFParser.structName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER72=null;

        CommonTree IDENTIFIER72_tree=null;
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:341:3: ( IDENTIFIER -> ^( STRUCT_NAME IDENTIFIER ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:341:5: IDENTIFIER
            {
            IDENTIFIER72=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_structName1578); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER72);



            // AST REWRITE
            // elements: IDENTIFIER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 341:16: -> ^( STRUCT_NAME IDENTIFIER )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:341:19: ^( STRUCT_NAME IDENTIFIER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(STRUCT_NAME, "STRUCT_NAME"), root_1);

                adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "structName"

    public static class structOrVariantDeclarationList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structOrVariantDeclarationList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:344:1: structOrVariantDeclarationList : ( structOrVariantDeclaration )+ ;
    public final CTFParser.structOrVariantDeclarationList_return structOrVariantDeclarationList() throws RecognitionException {
        CTFParser.structOrVariantDeclarationList_return retval = new CTFParser.structOrVariantDeclarationList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.structOrVariantDeclaration_return structOrVariantDeclaration73 = null;



        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:345:3: ( ( structOrVariantDeclaration )+ )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:345:5: ( structOrVariantDeclaration )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:345:5: ( structOrVariantDeclaration )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=CONSTTOK && LA22_0<=ENUMTOK)||(LA22_0>=FLOATINGPOINTTOK && LA22_0<=SIGNEDTOK)||(LA22_0>=STRINGTOK && LA22_0<=STRUCTTOK)||(LA22_0>=TYPEDEFTOK && LA22_0<=IMAGINARYTOK)) ) {
                    alt22=1;
                }
                else if ( (LA22_0==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {
                    alt22=1;
                }
                else if ( (LA22_0==TYPEALIASTOK) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:345:5: structOrVariantDeclaration
            	    {
            	    pushFollow(FOLLOW_structOrVariantDeclaration_in_structOrVariantDeclarationList1599);
            	    structOrVariantDeclaration73=structOrVariantDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, structOrVariantDeclaration73.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "structOrVariantDeclarationList"

    public static class structOrVariantDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structOrVariantDeclaration"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:348:1: structOrVariantDeclaration : ( ( declarationSpecifiers ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) ) ) | typealiasDecl -> typealiasDecl ) TERM ;
    public final CTFParser.structOrVariantDeclaration_return structOrVariantDeclaration() throws RecognitionException {
        CTFParser.structOrVariantDeclaration_return retval = new CTFParser.structOrVariantDeclaration_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TERM78=null;
        CTFParser.declarationSpecifiers_return declarationSpecifiers74 = null;

        CTFParser.declaratorList_return declaratorList75 = null;

        CTFParser.structOrVariantDeclaratorList_return structOrVariantDeclaratorList76 = null;

        CTFParser.typealiasDecl_return typealiasDecl77 = null;


        CommonTree TERM78_tree=null;
        RewriteRuleTokenStream stream_TERM=new RewriteRuleTokenStream(adaptor,"token TERM");
        RewriteRuleSubtreeStream stream_declaratorList=new RewriteRuleSubtreeStream(adaptor,"rule declaratorList");
        RewriteRuleSubtreeStream stream_typealiasDecl=new RewriteRuleSubtreeStream(adaptor,"rule typealiasDecl");
        RewriteRuleSubtreeStream stream_declarationSpecifiers=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifiers");
        RewriteRuleSubtreeStream stream_structOrVariantDeclaratorList=new RewriteRuleSubtreeStream(adaptor,"rule structOrVariantDeclaratorList");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:349:3: ( ( ( declarationSpecifiers ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) ) ) | typealiasDecl -> typealiasDecl ) TERM )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:350:3: ( ( declarationSpecifiers ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) ) ) | typealiasDecl -> typealiasDecl ) TERM
            {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:350:3: ( ( declarationSpecifiers ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) ) ) | typealiasDecl -> typealiasDecl )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=CONSTTOK && LA24_0<=ENUMTOK)||(LA24_0>=FLOATINGPOINTTOK && LA24_0<=SIGNEDTOK)||(LA24_0>=STRINGTOK && LA24_0<=STRUCTTOK)||(LA24_0>=TYPEDEFTOK && LA24_0<=IMAGINARYTOK)) ) {
                alt24=1;
            }
            else if ( (LA24_0==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {
                alt24=1;
            }
            else if ( (LA24_0==TYPEALIASTOK) ) {
                alt24=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:351:7: ( declarationSpecifiers ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) ) )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:351:7: ( declarationSpecifiers ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) ) )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:352:8: declarationSpecifiers ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) )
                    {
                    pushFollow(FOLLOW_declarationSpecifiers_in_structOrVariantDeclaration1632);
                    declarationSpecifiers74=declarationSpecifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declarationSpecifiers.add(declarationSpecifiers74.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:353:10: ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) )
                    int alt23=2;
                    alt23 = dfa23.predict(input);
                    switch (alt23) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:355:12: {...}? => declaratorList
                            {
                            if ( !((inTypedef())) ) {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                throw new FailedPredicateException(input, "structOrVariantDeclaration", "inTypedef()");
                            }
                            pushFollow(FOLLOW_declaratorList_in_structOrVariantDeclaration1673);
                            declaratorList75=declaratorList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_declaratorList.add(declaratorList75.getTree());
                            if ( state.backtracking==0 ) {
                              typedefOff();
                            }


                            // AST REWRITE
                            // elements: declaratorList, declarationSpecifiers
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 356:14: -> ^( TYPEDEF declaratorList declarationSpecifiers )
                            {
                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:356:17: ^( TYPEDEF declaratorList declarationSpecifiers )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPEDEF, "TYPEDEF"), root_1);

                                adaptor.addChild(root_1, stream_declaratorList.nextTree());
                                adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:357:14: structOrVariantDeclaratorList
                            {
                            pushFollow(FOLLOW_structOrVariantDeclaratorList_in_structOrVariantDeclaration1713);
                            structOrVariantDeclaratorList76=structOrVariantDeclaratorList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_structOrVariantDeclaratorList.add(structOrVariantDeclaratorList76.getTree());


                            // AST REWRITE
                            // elements: structOrVariantDeclaratorList, declarationSpecifiers
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 358:14: -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList )
                            {
                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:358:17: ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SV_DECLARATION, "SV_DECLARATION"), root_1);

                                adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());
                                adaptor.addChild(root_1, stream_structOrVariantDeclaratorList.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:363:5: typealiasDecl
                    {
                    pushFollow(FOLLOW_typealiasDecl_in_structOrVariantDeclaration1772);
                    typealiasDecl77=typealiasDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typealiasDecl.add(typealiasDecl77.getTree());


                    // AST REWRITE
                    // elements: typealiasDecl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 363:19: -> typealiasDecl
                    {
                        adaptor.addChild(root_0, stream_typealiasDecl.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }

            TERM78=(Token)match(input,TERM,FOLLOW_TERM_in_structOrVariantDeclaration1784); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TERM.add(TERM78);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "structOrVariantDeclaration"

    public static class specifierQualifierList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "specifierQualifierList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:368:1: specifierQualifierList : ( typeQualifier | typeSpecifier )+ -> ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* ) ;
    public final CTFParser.specifierQualifierList_return specifierQualifierList() throws RecognitionException {
        CTFParser.specifierQualifierList_return retval = new CTFParser.specifierQualifierList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.typeQualifier_return typeQualifier79 = null;

        CTFParser.typeSpecifier_return typeSpecifier80 = null;


        RewriteRuleSubtreeStream stream_typeSpecifier=new RewriteRuleSubtreeStream(adaptor,"rule typeSpecifier");
        RewriteRuleSubtreeStream stream_typeQualifier=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifier");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:369:3: ( ( typeQualifier | typeSpecifier )+ -> ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:369:5: ( typeQualifier | typeSpecifier )+
            {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:369:5: ( typeQualifier | typeSpecifier )+
            int cnt25=0;
            loop25:
            do {
                int alt25=3;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==CONSTTOK) ) {
                    alt25=1;
                }
                else if ( ((LA25_0>=CHARTOK && LA25_0<=ENUMTOK)||(LA25_0>=FLOATINGPOINTTOK && LA25_0<=SIGNEDTOK)||(LA25_0>=STRINGTOK && LA25_0<=STRUCTTOK)||(LA25_0>=UNSIGNEDTOK && LA25_0<=IMAGINARYTOK)) ) {
                    alt25=2;
                }
                else if ( (LA25_0==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {
                    alt25=2;
                }


                switch (alt25) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:369:6: typeQualifier
            	    {
            	    pushFollow(FOLLOW_typeQualifier_in_specifierQualifierList1798);
            	    typeQualifier79=typeQualifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeQualifier.add(typeQualifier79.getTree());

            	    }
            	    break;
            	case 2 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:369:22: typeSpecifier
            	    {
            	    pushFollow(FOLLOW_typeSpecifier_in_specifierQualifierList1802);
            	    typeSpecifier80=typeSpecifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeSpecifier.add(typeSpecifier80.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);



            // AST REWRITE
            // elements: typeSpecifier, typeQualifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 370:7: -> ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:370:10: ^( TYPE_SPECIFIER_LIST ( typeQualifier )* ( typeSpecifier )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_SPECIFIER_LIST, "TYPE_SPECIFIER_LIST"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:370:32: ( typeQualifier )*
                while ( stream_typeQualifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeQualifier.nextTree());

                }
                stream_typeQualifier.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:370:47: ( typeSpecifier )*
                while ( stream_typeSpecifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeSpecifier.nextTree());

                }
                stream_typeSpecifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "specifierQualifierList"

    public static class structOrVariantDeclaratorList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structOrVariantDeclaratorList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:373:1: structOrVariantDeclaratorList : structOrVariantDeclarator ( SEPARATOR structOrVariantDeclarator )* -> ^( TYPE_DECLARATOR_LIST ( structOrVariantDeclarator )+ ) ;
    public final CTFParser.structOrVariantDeclaratorList_return structOrVariantDeclaratorList() throws RecognitionException {
        CTFParser.structOrVariantDeclaratorList_return retval = new CTFParser.structOrVariantDeclaratorList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SEPARATOR82=null;
        CTFParser.structOrVariantDeclarator_return structOrVariantDeclarator81 = null;

        CTFParser.structOrVariantDeclarator_return structOrVariantDeclarator83 = null;


        CommonTree SEPARATOR82_tree=null;
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleSubtreeStream stream_structOrVariantDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule structOrVariantDeclarator");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:374:3: ( structOrVariantDeclarator ( SEPARATOR structOrVariantDeclarator )* -> ^( TYPE_DECLARATOR_LIST ( structOrVariantDeclarator )+ ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:374:5: structOrVariantDeclarator ( SEPARATOR structOrVariantDeclarator )*
            {
            pushFollow(FOLLOW_structOrVariantDeclarator_in_structOrVariantDeclaratorList1835);
            structOrVariantDeclarator81=structOrVariantDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_structOrVariantDeclarator.add(structOrVariantDeclarator81.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:374:31: ( SEPARATOR structOrVariantDeclarator )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==SEPARATOR) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:374:32: SEPARATOR structOrVariantDeclarator
            	    {
            	    SEPARATOR82=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_structOrVariantDeclaratorList1838); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SEPARATOR.add(SEPARATOR82);

            	    pushFollow(FOLLOW_structOrVariantDeclarator_in_structOrVariantDeclaratorList1840);
            	    structOrVariantDeclarator83=structOrVariantDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_structOrVariantDeclarator.add(structOrVariantDeclarator83.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);



            // AST REWRITE
            // elements: structOrVariantDeclarator
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 375:7: -> ^( TYPE_DECLARATOR_LIST ( structOrVariantDeclarator )+ )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:375:10: ^( TYPE_DECLARATOR_LIST ( structOrVariantDeclarator )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_DECLARATOR_LIST, "TYPE_DECLARATOR_LIST"), root_1);

                if ( !(stream_structOrVariantDeclarator.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_structOrVariantDeclarator.hasNext() ) {
                    adaptor.addChild(root_1, stream_structOrVariantDeclarator.nextTree());

                }
                stream_structOrVariantDeclarator.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "structOrVariantDeclaratorList"

    public static class structOrVariantDeclarator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structOrVariantDeclarator"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:378:1: structOrVariantDeclarator : ( declarator ( COLON numberLiteral )? ) -> declarator ;
    public final CTFParser.structOrVariantDeclarator_return structOrVariantDeclarator() throws RecognitionException {
        CTFParser.structOrVariantDeclarator_return retval = new CTFParser.structOrVariantDeclarator_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COLON85=null;
        CTFParser.declarator_return declarator84 = null;

        CTFParser.numberLiteral_return numberLiteral86 = null;


        CommonTree COLON85_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_declarator=new RewriteRuleSubtreeStream(adaptor,"rule declarator");
        RewriteRuleSubtreeStream stream_numberLiteral=new RewriteRuleSubtreeStream(adaptor,"rule numberLiteral");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:379:3: ( ( declarator ( COLON numberLiteral )? ) -> declarator )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:381:5: ( declarator ( COLON numberLiteral )? )
            {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:381:5: ( declarator ( COLON numberLiteral )? )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:381:6: declarator ( COLON numberLiteral )?
            {
            pushFollow(FOLLOW_declarator_in_structOrVariantDeclarator1879);
            declarator84=declarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarator.add(declarator84.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:381:17: ( COLON numberLiteral )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==COLON) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:381:18: COLON numberLiteral
                    {
                    COLON85=(Token)match(input,COLON,FOLLOW_COLON_in_structOrVariantDeclarator1882); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON85);

                    pushFollow(FOLLOW_numberLiteral_in_structOrVariantDeclarator1884);
                    numberLiteral86=numberLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_numberLiteral.add(numberLiteral86.getTree());

                    }
                    break;

            }


            }



            // AST REWRITE
            // elements: declarator
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 381:41: -> declarator
            {
                adaptor.addChild(root_0, stream_declarator.nextTree());

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "structOrVariantDeclarator"

    public static class variantSpecifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variantSpecifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:385:1: variantSpecifier : VARIANTTOK ( ( variantName ( ( variantTag ( variantBody | ) ) | variantBody ) ) | ( variantTag variantBody ) | variantBody ) -> ^( VARIANT ( variantName )? ( variantTag )? ( variantBody )? ) ;
    public final CTFParser.variantSpecifier_return variantSpecifier() throws RecognitionException {
        CTFParser.variantSpecifier_return retval = new CTFParser.variantSpecifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token VARIANTTOK87=null;
        CTFParser.variantName_return variantName88 = null;

        CTFParser.variantTag_return variantTag89 = null;

        CTFParser.variantBody_return variantBody90 = null;

        CTFParser.variantBody_return variantBody91 = null;

        CTFParser.variantTag_return variantTag92 = null;

        CTFParser.variantBody_return variantBody93 = null;

        CTFParser.variantBody_return variantBody94 = null;


        CommonTree VARIANTTOK87_tree=null;
        RewriteRuleTokenStream stream_VARIANTTOK=new RewriteRuleTokenStream(adaptor,"token VARIANTTOK");
        RewriteRuleSubtreeStream stream_variantName=new RewriteRuleSubtreeStream(adaptor,"rule variantName");
        RewriteRuleSubtreeStream stream_variantTag=new RewriteRuleSubtreeStream(adaptor,"rule variantTag");
        RewriteRuleSubtreeStream stream_variantBody=new RewriteRuleSubtreeStream(adaptor,"rule variantBody");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:386:3: ( VARIANTTOK ( ( variantName ( ( variantTag ( variantBody | ) ) | variantBody ) ) | ( variantTag variantBody ) | variantBody ) -> ^( VARIANT ( variantName )? ( variantTag )? ( variantBody )? ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:386:5: VARIANTTOK ( ( variantName ( ( variantTag ( variantBody | ) ) | variantBody ) ) | ( variantTag variantBody ) | variantBody )
            {
            VARIANTTOK87=(Token)match(input,VARIANTTOK,FOLLOW_VARIANTTOK_in_variantSpecifier1908); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VARIANTTOK.add(VARIANTTOK87);

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:387:3: ( ( variantName ( ( variantTag ( variantBody | ) ) | variantBody ) ) | ( variantTag variantBody ) | variantBody )
            int alt30=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt30=1;
                }
                break;
            case LT:
                {
                alt30=2;
                }
                break;
            case LCURL:
                {
                alt30=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:388:5: ( variantName ( ( variantTag ( variantBody | ) ) | variantBody ) )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:388:5: ( variantName ( ( variantTag ( variantBody | ) ) | variantBody ) )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:389:7: variantName ( ( variantTag ( variantBody | ) ) | variantBody )
                    {
                    pushFollow(FOLLOW_variantName_in_variantSpecifier1926);
                    variantName88=variantName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variantName.add(variantName88.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:390:7: ( ( variantTag ( variantBody | ) ) | variantBody )
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==LT) ) {
                        alt29=1;
                    }
                    else if ( (LA29_0==LCURL) ) {
                        alt29=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;
                    }
                    switch (alt29) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:391:9: ( variantTag ( variantBody | ) )
                            {
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:391:9: ( variantTag ( variantBody | ) )
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:392:11: variantTag ( variantBody | )
                            {
                            pushFollow(FOLLOW_variantTag_in_variantSpecifier1956);
                            variantTag89=variantTag();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variantTag.add(variantTag89.getTree());
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:393:11: ( variantBody | )
                            int alt28=2;
                            int LA28_0 = input.LA(1);

                            if ( (LA28_0==LCURL) ) {
                                switch ( input.LA(2) ) {
                                case ALIGNTOK:
                                case EVENTTOK:
                                case STRING_LITERAL:
                                    {
                                    alt28=2;
                                    }
                                    break;
                                case IDENTIFIER:
                                    {
                                    int LA28_3 = input.LA(3);

                                    if ( (LA28_3==SEPARATOR||LA28_3==ASSIGNMENT||LA28_3==RCURL) ) {
                                        alt28=2;
                                    }
                                    else if ( ((LA28_3>=CONSTTOK && LA28_3<=ENUMTOK)||(LA28_3>=FLOATINGPOINTTOK && LA28_3<=SIGNEDTOK)||(LA28_3>=STRINGTOK && LA28_3<=STRUCTTOK)||(LA28_3>=TYPEDEFTOK && LA28_3<=IMAGINARYTOK)||LA28_3==POINTER||LA28_3==IDENTIFIER) ) {
                                        alt28=1;
                                    }
                                    else {
                                        if (state.backtracking>0) {state.failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 28, 3, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case SIGNEDTOK:
                                    {
                                    int LA28_4 = input.LA(3);

                                    if ( ((LA28_4>=CONSTTOK && LA28_4<=ENUMTOK)||(LA28_4>=FLOATINGPOINTTOK && LA28_4<=SIGNEDTOK)||(LA28_4>=STRINGTOK && LA28_4<=STRUCTTOK)||(LA28_4>=TYPEDEFTOK && LA28_4<=IMAGINARYTOK)||LA28_4==POINTER||LA28_4==IDENTIFIER) ) {
                                        alt28=1;
                                    }
                                    else if ( (LA28_4==SEPARATOR||LA28_4==ASSIGNMENT||LA28_4==RCURL) ) {
                                        alt28=2;
                                    }
                                    else {
                                        if (state.backtracking>0) {state.failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 28, 4, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case CONSTTOK:
                                case CHARTOK:
                                case DOUBLETOK:
                                case ENUMTOK:
                                case FLOATINGPOINTTOK:
                                case FLOATTOK:
                                case INTEGERTOK:
                                case INTTOK:
                                case LONGTOK:
                                case SHORTTOK:
                                case STRUCTTOK:
                                case TYPEALIASTOK:
                                case TYPEDEFTOK:
                                case UNSIGNEDTOK:
                                case VARIANTTOK:
                                case VOIDTOK:
                                case BOOLTOK:
                                case COMPLEXTOK:
                                case IMAGINARYTOK:
                                    {
                                    alt28=1;
                                    }
                                    break;
                                case STRINGTOK:
                                    {
                                    int LA28_6 = input.LA(3);

                                    if ( ((LA28_6>=CONSTTOK && LA28_6<=ENUMTOK)||(LA28_6>=FLOATINGPOINTTOK && LA28_6<=SIGNEDTOK)||(LA28_6>=STRINGTOK && LA28_6<=STRUCTTOK)||(LA28_6>=TYPEDEFTOK && LA28_6<=IMAGINARYTOK)||LA28_6==LCURL||LA28_6==POINTER||LA28_6==IDENTIFIER) ) {
                                        alt28=1;
                                    }
                                    else if ( (LA28_6==SEPARATOR||LA28_6==ASSIGNMENT||LA28_6==RCURL) ) {
                                        alt28=2;
                                    }
                                    else {
                                        if (state.backtracking>0) {state.failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 28, 6, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                default:
                                    if (state.backtracking>0) {state.failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 28, 1, input);

                                    throw nvae;
                                }

                            }
                            else if ( (LA28_0==EOF||(LA28_0>=CONSTTOK && LA28_0<=ENUMTOK)||(LA28_0>=FLOATINGPOINTTOK && LA28_0<=SIGNEDTOK)||(LA28_0>=STRINGTOK && LA28_0<=STRUCTTOK)||(LA28_0>=TYPEDEFTOK && LA28_0<=IMAGINARYTOK)||LA28_0==TYPE_ASSIGNMENT||LA28_0==LPAREN||(LA28_0>=TERM && LA28_0<=POINTER)||LA28_0==IDENTIFIER) ) {
                                alt28=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 28, 0, input);

                                throw nvae;
                            }
                            switch (alt28) {
                                case 1 :
                                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:394:13: variantBody
                                    {
                                    pushFollow(FOLLOW_variantBody_in_variantSpecifier1982);
                                    variantBody90=variantBody();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_variantBody.add(variantBody90.getTree());

                                    }
                                    break;
                                case 2 :
                                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:397:11: 
                                    {
                                    }
                                    break;

                            }


                            }


                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:400:9: variantBody
                            {
                            pushFollow(FOLLOW_variantBody_in_variantSpecifier2050);
                            variantBody91=variantBody();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_variantBody.add(variantBody91.getTree());

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:403:5: ( variantTag variantBody )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:403:5: ( variantTag variantBody )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:403:6: variantTag variantBody
                    {
                    pushFollow(FOLLOW_variantTag_in_variantSpecifier2071);
                    variantTag92=variantTag();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variantTag.add(variantTag92.getTree());
                    pushFollow(FOLLOW_variantBody_in_variantSpecifier2073);
                    variantBody93=variantBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variantBody.add(variantBody93.getTree());

                    }


                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:404:5: variantBody
                    {
                    pushFollow(FOLLOW_variantBody_in_variantSpecifier2080);
                    variantBody94=variantBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variantBody.add(variantBody94.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: variantName, variantTag, variantBody
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 405:5: -> ^( VARIANT ( variantName )? ( variantTag )? ( variantBody )? )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:405:8: ^( VARIANT ( variantName )? ( variantTag )? ( variantBody )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(VARIANT, "VARIANT"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:405:18: ( variantName )?
                if ( stream_variantName.hasNext() ) {
                    adaptor.addChild(root_1, stream_variantName.nextTree());

                }
                stream_variantName.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:405:31: ( variantTag )?
                if ( stream_variantTag.hasNext() ) {
                    adaptor.addChild(root_1, stream_variantTag.nextTree());

                }
                stream_variantTag.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:405:43: ( variantBody )?
                if ( stream_variantBody.hasNext() ) {
                    adaptor.addChild(root_1, stream_variantBody.nextTree());

                }
                stream_variantBody.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "variantSpecifier"

    public static class variantName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variantName"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:408:1: variantName : IDENTIFIER -> ^( VARIANT_NAME IDENTIFIER ) ;
    public final CTFParser.variantName_return variantName() throws RecognitionException {
        CTFParser.variantName_return retval = new CTFParser.variantName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER95=null;

        CommonTree IDENTIFIER95_tree=null;
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:409:3: ( IDENTIFIER -> ^( VARIANT_NAME IDENTIFIER ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:409:5: IDENTIFIER
            {
            IDENTIFIER95=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variantName2112); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER95);



            // AST REWRITE
            // elements: IDENTIFIER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 409:16: -> ^( VARIANT_NAME IDENTIFIER )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:409:19: ^( VARIANT_NAME IDENTIFIER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(VARIANT_NAME, "VARIANT_NAME"), root_1);

                adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "variantName"

    public static class variantBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variantBody"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:412:1: variantBody : LCURL structOrVariantDeclarationList RCURL -> ^( VARIANT_BODY structOrVariantDeclarationList ) ;
    public final CTFParser.variantBody_return variantBody() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CTFParser.variantBody_return retval = new CTFParser.variantBody_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LCURL96=null;
        Token RCURL98=null;
        CTFParser.structOrVariantDeclarationList_return structOrVariantDeclarationList97 = null;


        CommonTree LCURL96_tree=null;
        CommonTree RCURL98_tree=null;
        RewriteRuleTokenStream stream_LCURL=new RewriteRuleTokenStream(adaptor,"token LCURL");
        RewriteRuleTokenStream stream_RCURL=new RewriteRuleTokenStream(adaptor,"token RCURL");
        RewriteRuleSubtreeStream stream_structOrVariantDeclarationList=new RewriteRuleSubtreeStream(adaptor,"rule structOrVariantDeclarationList");

            ((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:417:3: ( LCURL structOrVariantDeclarationList RCURL -> ^( VARIANT_BODY structOrVariantDeclarationList ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:417:5: LCURL structOrVariantDeclarationList RCURL
            {
            LCURL96=(Token)match(input,LCURL,FOLLOW_LCURL_in_variantBody2143); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURL.add(LCURL96);

            pushFollow(FOLLOW_structOrVariantDeclarationList_in_variantBody2145);
            structOrVariantDeclarationList97=structOrVariantDeclarationList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_structOrVariantDeclarationList.add(structOrVariantDeclarationList97.getTree());
            RCURL98=(Token)match(input,RCURL,FOLLOW_RCURL_in_variantBody2147); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURL.add(RCURL98);



            // AST REWRITE
            // elements: structOrVariantDeclarationList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 418:7: -> ^( VARIANT_BODY structOrVariantDeclarationList )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:418:10: ^( VARIANT_BODY structOrVariantDeclarationList )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(VARIANT_BODY, "VARIANT_BODY"), root_1);

                adaptor.addChild(root_1, stream_structOrVariantDeclarationList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "variantBody"

    public static class variantTag_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "variantTag"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:421:1: variantTag : LT IDENTIFIER GT -> ^( VARIANT_TAG IDENTIFIER ) ;
    public final CTFParser.variantTag_return variantTag() throws RecognitionException {
        CTFParser.variantTag_return retval = new CTFParser.variantTag_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LT99=null;
        Token IDENTIFIER100=null;
        Token GT101=null;

        CommonTree LT99_tree=null;
        CommonTree IDENTIFIER100_tree=null;
        CommonTree GT101_tree=null;
        RewriteRuleTokenStream stream_GT=new RewriteRuleTokenStream(adaptor,"token GT");
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:422:3: ( LT IDENTIFIER GT -> ^( VARIANT_TAG IDENTIFIER ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:422:5: LT IDENTIFIER GT
            {
            LT99=(Token)match(input,LT,FOLLOW_LT_in_variantTag2174); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LT.add(LT99);

            IDENTIFIER100=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variantTag2176); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER100);

            GT101=(Token)match(input,GT,FOLLOW_GT_in_variantTag2178); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_GT.add(GT101);



            // AST REWRITE
            // elements: IDENTIFIER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 422:22: -> ^( VARIANT_TAG IDENTIFIER )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:422:25: ^( VARIANT_TAG IDENTIFIER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(VARIANT_TAG, "VARIANT_TAG"), root_1);

                adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "variantTag"

    public static class enumSpecifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumSpecifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:425:1: enumSpecifier : ENUMTOK ( ( enumName ( enumContainerType enumBody | enumBody | ) ) | ( enumContainerType enumBody | enumBody ) ) -> ^( ENUM ( enumName )? ( enumContainerType )? ( enumBody )? ) ;
    public final CTFParser.enumSpecifier_return enumSpecifier() throws RecognitionException {
        CTFParser.enumSpecifier_return retval = new CTFParser.enumSpecifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ENUMTOK102=null;
        CTFParser.enumName_return enumName103 = null;

        CTFParser.enumContainerType_return enumContainerType104 = null;

        CTFParser.enumBody_return enumBody105 = null;

        CTFParser.enumBody_return enumBody106 = null;

        CTFParser.enumContainerType_return enumContainerType107 = null;

        CTFParser.enumBody_return enumBody108 = null;

        CTFParser.enumBody_return enumBody109 = null;


        CommonTree ENUMTOK102_tree=null;
        RewriteRuleTokenStream stream_ENUMTOK=new RewriteRuleTokenStream(adaptor,"token ENUMTOK");
        RewriteRuleSubtreeStream stream_enumName=new RewriteRuleSubtreeStream(adaptor,"rule enumName");
        RewriteRuleSubtreeStream stream_enumContainerType=new RewriteRuleSubtreeStream(adaptor,"rule enumContainerType");
        RewriteRuleSubtreeStream stream_enumBody=new RewriteRuleSubtreeStream(adaptor,"rule enumBody");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:426:3: ( ENUMTOK ( ( enumName ( enumContainerType enumBody | enumBody | ) ) | ( enumContainerType enumBody | enumBody ) ) -> ^( ENUM ( enumName )? ( enumContainerType )? ( enumBody )? ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:426:5: ENUMTOK ( ( enumName ( enumContainerType enumBody | enumBody | ) ) | ( enumContainerType enumBody | enumBody ) )
            {
            ENUMTOK102=(Token)match(input,ENUMTOK,FOLLOW_ENUMTOK_in_enumSpecifier2199); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ENUMTOK.add(ENUMTOK102);

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:427:5: ( ( enumName ( enumContainerType enumBody | enumBody | ) ) | ( enumContainerType enumBody | enumBody ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==IDENTIFIER) ) {
                alt33=1;
            }
            else if ( (LA33_0==COLON||LA33_0==LCURL) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:429:9: ( enumName ( enumContainerType enumBody | enumBody | ) )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:429:9: ( enumName ( enumContainerType enumBody | enumBody | ) )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:430:13: enumName ( enumContainerType enumBody | enumBody | )
                    {
                    pushFollow(FOLLOW_enumName_in_enumSpecifier2238);
                    enumName103=enumName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_enumName.add(enumName103.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:431:13: ( enumContainerType enumBody | enumBody | )
                    int alt31=3;
                    alt31 = dfa31.predict(input);
                    switch (alt31) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:432:17: enumContainerType enumBody
                            {
                            pushFollow(FOLLOW_enumContainerType_in_enumSpecifier2270);
                            enumContainerType104=enumContainerType();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumContainerType.add(enumContainerType104.getTree());
                            pushFollow(FOLLOW_enumBody_in_enumSpecifier2272);
                            enumBody105=enumBody();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumBody.add(enumBody105.getTree());

                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:434:17: enumBody
                            {
                            pushFollow(FOLLOW_enumBody_in_enumSpecifier2302);
                            enumBody106=enumBody();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumBody.add(enumBody106.getTree());

                            }
                            break;
                        case 3 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:437:13: 
                            {
                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:441:9: ( enumContainerType enumBody | enumBody )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:441:9: ( enumContainerType enumBody | enumBody )
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==COLON) ) {
                        alt32=1;
                    }
                    else if ( (LA32_0==LCURL) ) {
                        alt32=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 32, 0, input);

                        throw nvae;
                    }
                    switch (alt32) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:442:13: enumContainerType enumBody
                            {
                            pushFollow(FOLLOW_enumContainerType_in_enumSpecifier2394);
                            enumContainerType107=enumContainerType();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumContainerType.add(enumContainerType107.getTree());
                            pushFollow(FOLLOW_enumBody_in_enumSpecifier2396);
                            enumBody108=enumBody();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumBody.add(enumBody108.getTree());

                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:444:13: enumBody
                            {
                            pushFollow(FOLLOW_enumBody_in_enumSpecifier2420);
                            enumBody109=enumBody();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumBody.add(enumBody109.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }



            // AST REWRITE
            // elements: enumBody, enumContainerType, enumName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 446:7: -> ^( ENUM ( enumName )? ( enumContainerType )? ( enumBody )? )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:446:10: ^( ENUM ( enumName )? ( enumContainerType )? ( enumBody )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ENUM, "ENUM"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:446:17: ( enumName )?
                if ( stream_enumName.hasNext() ) {
                    adaptor.addChild(root_1, stream_enumName.nextTree());

                }
                stream_enumName.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:446:27: ( enumContainerType )?
                if ( stream_enumContainerType.hasNext() ) {
                    adaptor.addChild(root_1, stream_enumContainerType.nextTree());

                }
                stream_enumContainerType.reset();
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:446:46: ( enumBody )?
                if ( stream_enumBody.hasNext() ) {
                    adaptor.addChild(root_1, stream_enumBody.nextTree());

                }
                stream_enumBody.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumSpecifier"

    public static class enumName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumName"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:449:1: enumName : IDENTIFIER -> ^( ENUM_NAME IDENTIFIER ) ;
    public final CTFParser.enumName_return enumName() throws RecognitionException {
        CTFParser.enumName_return retval = new CTFParser.enumName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER110=null;

        CommonTree IDENTIFIER110_tree=null;
        RewriteRuleTokenStream stream_IDENTIFIER=new RewriteRuleTokenStream(adaptor,"token IDENTIFIER");

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:450:3: ( IDENTIFIER -> ^( ENUM_NAME IDENTIFIER ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:450:5: IDENTIFIER
            {
            IDENTIFIER110=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_enumName2464); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENTIFIER.add(IDENTIFIER110);



            // AST REWRITE
            // elements: IDENTIFIER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 450:16: -> ^( ENUM_NAME IDENTIFIER )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:450:19: ^( ENUM_NAME IDENTIFIER )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ENUM_NAME, "ENUM_NAME"), root_1);

                adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumName"

    public static class enumBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumBody"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:453:1: enumBody : LCURL enumeratorList ( SEPARATOR )? RCURL -> ^( ENUM_BODY enumeratorList ) ;
    public final CTFParser.enumBody_return enumBody() throws RecognitionException {
        CTFParser.enumBody_return retval = new CTFParser.enumBody_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LCURL111=null;
        Token SEPARATOR113=null;
        Token RCURL114=null;
        CTFParser.enumeratorList_return enumeratorList112 = null;


        CommonTree LCURL111_tree=null;
        CommonTree SEPARATOR113_tree=null;
        CommonTree RCURL114_tree=null;
        RewriteRuleTokenStream stream_LCURL=new RewriteRuleTokenStream(adaptor,"token LCURL");
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleTokenStream stream_RCURL=new RewriteRuleTokenStream(adaptor,"token RCURL");
        RewriteRuleSubtreeStream stream_enumeratorList=new RewriteRuleSubtreeStream(adaptor,"rule enumeratorList");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:454:3: ( LCURL enumeratorList ( SEPARATOR )? RCURL -> ^( ENUM_BODY enumeratorList ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:454:5: LCURL enumeratorList ( SEPARATOR )? RCURL
            {
            LCURL111=(Token)match(input,LCURL,FOLLOW_LCURL_in_enumBody2485); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURL.add(LCURL111);

            pushFollow(FOLLOW_enumeratorList_in_enumBody2487);
            enumeratorList112=enumeratorList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_enumeratorList.add(enumeratorList112.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:454:26: ( SEPARATOR )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==SEPARATOR) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:454:26: SEPARATOR
                    {
                    SEPARATOR113=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_enumBody2489); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEPARATOR.add(SEPARATOR113);


                    }
                    break;

            }

            RCURL114=(Token)match(input,RCURL,FOLLOW_RCURL_in_enumBody2492); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURL.add(RCURL114);



            // AST REWRITE
            // elements: enumeratorList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 454:43: -> ^( ENUM_BODY enumeratorList )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:454:46: ^( ENUM_BODY enumeratorList )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ENUM_BODY, "ENUM_BODY"), root_1);

                adaptor.addChild(root_1, stream_enumeratorList.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumBody"

    public static class enumContainerType_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumContainerType"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:457:1: enumContainerType : COLON declarationSpecifiers -> ^( ENUM_CONTAINER_TYPE declarationSpecifiers ) ;
    public final CTFParser.enumContainerType_return enumContainerType() throws RecognitionException {
        CTFParser.enumContainerType_return retval = new CTFParser.enumContainerType_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COLON115=null;
        CTFParser.declarationSpecifiers_return declarationSpecifiers116 = null;


        CommonTree COLON115_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_declarationSpecifiers=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifiers");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:458:3: ( COLON declarationSpecifiers -> ^( ENUM_CONTAINER_TYPE declarationSpecifiers ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:458:5: COLON declarationSpecifiers
            {
            COLON115=(Token)match(input,COLON,FOLLOW_COLON_in_enumContainerType2513); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON115);

            pushFollow(FOLLOW_declarationSpecifiers_in_enumContainerType2515);
            declarationSpecifiers116=declarationSpecifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarationSpecifiers.add(declarationSpecifiers116.getTree());


            // AST REWRITE
            // elements: declarationSpecifiers
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 458:33: -> ^( ENUM_CONTAINER_TYPE declarationSpecifiers )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:458:36: ^( ENUM_CONTAINER_TYPE declarationSpecifiers )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ENUM_CONTAINER_TYPE, "ENUM_CONTAINER_TYPE"), root_1);

                adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumContainerType"

    public static class enumeratorList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumeratorList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:461:1: enumeratorList : enumerator ( SEPARATOR enumerator )* -> ( ^( ENUM_ENUMERATOR enumerator ) )+ ;
    public final CTFParser.enumeratorList_return enumeratorList() throws RecognitionException {
        CTFParser.enumeratorList_return retval = new CTFParser.enumeratorList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SEPARATOR118=null;
        CTFParser.enumerator_return enumerator117 = null;

        CTFParser.enumerator_return enumerator119 = null;


        CommonTree SEPARATOR118_tree=null;
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleSubtreeStream stream_enumerator=new RewriteRuleSubtreeStream(adaptor,"rule enumerator");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:462:3: ( enumerator ( SEPARATOR enumerator )* -> ( ^( ENUM_ENUMERATOR enumerator ) )+ )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:462:5: enumerator ( SEPARATOR enumerator )*
            {
            pushFollow(FOLLOW_enumerator_in_enumeratorList2536);
            enumerator117=enumerator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_enumerator.add(enumerator117.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:462:16: ( SEPARATOR enumerator )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==SEPARATOR) ) {
                    int LA35_1 = input.LA(2);

                    if ( (LA35_1==ALIGNTOK||LA35_1==EVENTTOK||LA35_1==SIGNEDTOK||LA35_1==STRINGTOK||LA35_1==STRING_LITERAL||LA35_1==IDENTIFIER) ) {
                        alt35=1;
                    }


                }


                switch (alt35) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:462:17: SEPARATOR enumerator
            	    {
            	    SEPARATOR118=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_enumeratorList2539); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_SEPARATOR.add(SEPARATOR118);

            	    pushFollow(FOLLOW_enumerator_in_enumeratorList2541);
            	    enumerator119=enumerator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_enumerator.add(enumerator119.getTree());

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);



            // AST REWRITE
            // elements: enumerator
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 462:40: -> ( ^( ENUM_ENUMERATOR enumerator ) )+
            {
                if ( !(stream_enumerator.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_enumerator.hasNext() ) {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:462:44: ^( ENUM_ENUMERATOR enumerator )
                    {
                    CommonTree root_1 = (CommonTree)adaptor.nil();
                    root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ENUM_ENUMERATOR, "ENUM_ENUMERATOR"), root_1);

                    adaptor.addChild(root_1, stream_enumerator.nextTree());

                    adaptor.addChild(root_0, root_1);
                    }

                }
                stream_enumerator.reset();

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumeratorList"

    public static class enumerator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumerator"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:465:1: enumerator : enumConstant ( enumeratorValue )? ;
    public final CTFParser.enumerator_return enumerator() throws RecognitionException {
        CTFParser.enumerator_return retval = new CTFParser.enumerator_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.enumConstant_return enumConstant120 = null;

        CTFParser.enumeratorValue_return enumeratorValue121 = null;



        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:466:3: ( enumConstant ( enumeratorValue )? )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:466:5: enumConstant ( enumeratorValue )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_enumConstant_in_enumerator2567);
            enumConstant120=enumConstant();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstant120.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:466:18: ( enumeratorValue )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==ASSIGNMENT) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:466:18: enumeratorValue
                    {
                    pushFollow(FOLLOW_enumeratorValue_in_enumerator2569);
                    enumeratorValue121=enumeratorValue();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumeratorValue121.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumerator"

    public static class enumeratorValue_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enumeratorValue"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:469:1: enumeratorValue : ASSIGNMENT e1= unaryExpression ( -> ^( ENUM_VALUE $e1) | ELIPSES e2= unaryExpression -> ^( ENUM_VALUE_RANGE $e1 $e2) ) ;
    public final CTFParser.enumeratorValue_return enumeratorValue() throws RecognitionException {
        CTFParser.enumeratorValue_return retval = new CTFParser.enumeratorValue_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASSIGNMENT122=null;
        Token ELIPSES123=null;
        CTFParser.unaryExpression_return e1 = null;

        CTFParser.unaryExpression_return e2 = null;


        CommonTree ASSIGNMENT122_tree=null;
        CommonTree ELIPSES123_tree=null;
        RewriteRuleTokenStream stream_ASSIGNMENT=new RewriteRuleTokenStream(adaptor,"token ASSIGNMENT");
        RewriteRuleTokenStream stream_ELIPSES=new RewriteRuleTokenStream(adaptor,"token ELIPSES");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:470:3: ( ASSIGNMENT e1= unaryExpression ( -> ^( ENUM_VALUE $e1) | ELIPSES e2= unaryExpression -> ^( ENUM_VALUE_RANGE $e1 $e2) ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:470:5: ASSIGNMENT e1= unaryExpression ( -> ^( ENUM_VALUE $e1) | ELIPSES e2= unaryExpression -> ^( ENUM_VALUE_RANGE $e1 $e2) )
            {
            ASSIGNMENT122=(Token)match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_enumeratorValue2583); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGNMENT.add(ASSIGNMENT122);

            pushFollow(FOLLOW_unaryExpression_in_enumeratorValue2587);
            e1=unaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_unaryExpression.add(e1.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:471:7: ( -> ^( ENUM_VALUE $e1) | ELIPSES e2= unaryExpression -> ^( ENUM_VALUE_RANGE $e1 $e2) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==SEPARATOR||LA37_0==RCURL) ) {
                alt37=1;
            }
            else if ( (LA37_0==ELIPSES) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:472:11: 
                    {

                    // AST REWRITE
                    // elements: e1
                    // token labels: 
                    // rule labels: retval, e1
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 472:11: -> ^( ENUM_VALUE $e1)
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:472:14: ^( ENUM_VALUE $e1)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ENUM_VALUE, "ENUM_VALUE"), root_1);

                        adaptor.addChild(root_1, stream_e1.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:473:9: ELIPSES e2= unaryExpression
                    {
                    ELIPSES123=(Token)match(input,ELIPSES,FOLLOW_ELIPSES_in_enumeratorValue2626); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELIPSES.add(ELIPSES123);

                    pushFollow(FOLLOW_unaryExpression_in_enumeratorValue2630);
                    e2=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(e2.getTree());


                    // AST REWRITE
                    // elements: e2, e1
                    // token labels: 
                    // rule labels: retval, e1, e2
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
                    RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 474:11: -> ^( ENUM_VALUE_RANGE $e1 $e2)
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:474:14: ^( ENUM_VALUE_RANGE $e1 $e2)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ENUM_VALUE_RANGE, "ENUM_VALUE_RANGE"), root_1);

                        adaptor.addChild(root_1, stream_e1.nextTree());
                        adaptor.addChild(root_1, stream_e2.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enumeratorValue"

    public static class declarator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declarator"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:478:1: declarator : ( pointer )* directDeclarator -> ^( TYPE_DECLARATOR ( pointer )* directDeclarator ) ;
    public final CTFParser.declarator_return declarator() throws RecognitionException {
        CTFParser.declarator_return retval = new CTFParser.declarator_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.pointer_return pointer124 = null;

        CTFParser.directDeclarator_return directDeclarator125 = null;


        RewriteRuleSubtreeStream stream_directDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule directDeclarator");
        RewriteRuleSubtreeStream stream_pointer=new RewriteRuleSubtreeStream(adaptor,"rule pointer");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:479:3: ( ( pointer )* directDeclarator -> ^( TYPE_DECLARATOR ( pointer )* directDeclarator ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:479:5: ( pointer )* directDeclarator
            {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:479:5: ( pointer )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==POINTER) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:479:5: pointer
            	    {
            	    pushFollow(FOLLOW_pointer_in_declarator2673);
            	    pointer124=pointer();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_pointer.add(pointer124.getTree());

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            pushFollow(FOLLOW_directDeclarator_in_declarator2676);
            directDeclarator125=directDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_directDeclarator.add(directDeclarator125.getTree());


            // AST REWRITE
            // elements: pointer, directDeclarator
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 480:7: -> ^( TYPE_DECLARATOR ( pointer )* directDeclarator )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:480:10: ^( TYPE_DECLARATOR ( pointer )* directDeclarator )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_DECLARATOR, "TYPE_DECLARATOR"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:480:28: ( pointer )*
                while ( stream_pointer.hasNext() ) {
                    adaptor.addChild(root_1, stream_pointer.nextTree());

                }
                stream_pointer.reset();
                adaptor.addChild(root_1, stream_directDeclarator.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declarator"

    public static class directDeclarator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "directDeclarator"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:483:1: directDeclarator : ( IDENTIFIER ) ( directDeclaratorSuffix )* ;
    public final CTFParser.directDeclarator_return directDeclarator() throws RecognitionException {
        CTFParser.directDeclarator_return retval = new CTFParser.directDeclarator_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER126=null;
        CTFParser.directDeclaratorSuffix_return directDeclaratorSuffix127 = null;


        CommonTree IDENTIFIER126_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:484:3: ( ( IDENTIFIER ) ( directDeclaratorSuffix )* )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:484:5: ( IDENTIFIER ) ( directDeclaratorSuffix )*
            {
            root_0 = (CommonTree)adaptor.nil();

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:484:5: ( IDENTIFIER )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:485:7: IDENTIFIER
            {
            IDENTIFIER126=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_directDeclarator2714); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER126_tree = (CommonTree)adaptor.create(IDENTIFIER126);
            adaptor.addChild(root_0, IDENTIFIER126_tree);
            }
            if ( state.backtracking==0 ) {
               if (inTypedef()) addTypeName((IDENTIFIER126!=null?IDENTIFIER126.getText():null)); 
            }
            if ( state.backtracking==0 ) {
               debug_print((IDENTIFIER126!=null?IDENTIFIER126.getText():null)); 
            }

            }

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:490:5: ( directDeclaratorSuffix )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==OPENBRAC) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:490:5: directDeclaratorSuffix
            	    {
            	    pushFollow(FOLLOW_directDeclaratorSuffix_in_directDeclarator2754);
            	    directDeclaratorSuffix127=directDeclaratorSuffix();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, directDeclaratorSuffix127.getTree());

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "directDeclarator"

    public static class directDeclaratorSuffix_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "directDeclaratorSuffix"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:493:1: directDeclaratorSuffix : OPENBRAC directDeclaratorLength CLOSEBRAC -> ^( LENGTH directDeclaratorLength ) ;
    public final CTFParser.directDeclaratorSuffix_return directDeclaratorSuffix() throws RecognitionException {
        CTFParser.directDeclaratorSuffix_return retval = new CTFParser.directDeclaratorSuffix_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token OPENBRAC128=null;
        Token CLOSEBRAC130=null;
        CTFParser.directDeclaratorLength_return directDeclaratorLength129 = null;


        CommonTree OPENBRAC128_tree=null;
        CommonTree CLOSEBRAC130_tree=null;
        RewriteRuleTokenStream stream_OPENBRAC=new RewriteRuleTokenStream(adaptor,"token OPENBRAC");
        RewriteRuleTokenStream stream_CLOSEBRAC=new RewriteRuleTokenStream(adaptor,"token CLOSEBRAC");
        RewriteRuleSubtreeStream stream_directDeclaratorLength=new RewriteRuleSubtreeStream(adaptor,"rule directDeclaratorLength");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:494:3: ( OPENBRAC directDeclaratorLength CLOSEBRAC -> ^( LENGTH directDeclaratorLength ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:494:5: OPENBRAC directDeclaratorLength CLOSEBRAC
            {
            OPENBRAC128=(Token)match(input,OPENBRAC,FOLLOW_OPENBRAC_in_directDeclaratorSuffix2768); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OPENBRAC.add(OPENBRAC128);

            pushFollow(FOLLOW_directDeclaratorLength_in_directDeclaratorSuffix2770);
            directDeclaratorLength129=directDeclaratorLength();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_directDeclaratorLength.add(directDeclaratorLength129.getTree());
            CLOSEBRAC130=(Token)match(input,CLOSEBRAC,FOLLOW_CLOSEBRAC_in_directDeclaratorSuffix2772); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CLOSEBRAC.add(CLOSEBRAC130);



            // AST REWRITE
            // elements: directDeclaratorLength
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 495:7: -> ^( LENGTH directDeclaratorLength )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:495:10: ^( LENGTH directDeclaratorLength )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(LENGTH, "LENGTH"), root_1);

                adaptor.addChild(root_1, stream_directDeclaratorLength.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "directDeclaratorSuffix"

    public static class directDeclaratorLength_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "directDeclaratorLength"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:498:1: directDeclaratorLength : unaryExpression ;
    public final CTFParser.directDeclaratorLength_return directDeclaratorLength() throws RecognitionException {
        CTFParser.directDeclaratorLength_return retval = new CTFParser.directDeclaratorLength_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.unaryExpression_return unaryExpression131 = null;



        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:499:3: ( unaryExpression )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:499:5: unaryExpression
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_unaryExpression_in_directDeclaratorLength2800);
            unaryExpression131=unaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression131.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "directDeclaratorLength"

    public static class abstractDeclarator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "abstractDeclarator"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:502:1: abstractDeclarator : ( ( pointer )+ ( directAbstractDeclarator )? -> ^( TYPE_DECLARATOR ( pointer )+ ( directAbstractDeclarator )? ) | directAbstractDeclarator -> ^( TYPE_DECLARATOR directAbstractDeclarator ) );
    public final CTFParser.abstractDeclarator_return abstractDeclarator() throws RecognitionException {
        CTFParser.abstractDeclarator_return retval = new CTFParser.abstractDeclarator_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.pointer_return pointer132 = null;

        CTFParser.directAbstractDeclarator_return directAbstractDeclarator133 = null;

        CTFParser.directAbstractDeclarator_return directAbstractDeclarator134 = null;


        RewriteRuleSubtreeStream stream_pointer=new RewriteRuleSubtreeStream(adaptor,"rule pointer");
        RewriteRuleSubtreeStream stream_directAbstractDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule directAbstractDeclarator");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:503:3: ( ( pointer )+ ( directAbstractDeclarator )? -> ^( TYPE_DECLARATOR ( pointer )+ ( directAbstractDeclarator )? ) | directAbstractDeclarator -> ^( TYPE_DECLARATOR directAbstractDeclarator ) )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==POINTER) ) {
                alt42=1;
            }
            else if ( (LA42_0==LPAREN||LA42_0==IDENTIFIER) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:503:5: ( pointer )+ ( directAbstractDeclarator )?
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:503:5: ( pointer )+
                    int cnt40=0;
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==POINTER) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:503:5: pointer
                    	    {
                    	    pushFollow(FOLLOW_pointer_in_abstractDeclarator2813);
                    	    pointer132=pointer();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_pointer.add(pointer132.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt40 >= 1 ) break loop40;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(40, input);
                                throw eee;
                        }
                        cnt40++;
                    } while (true);

                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:503:14: ( directAbstractDeclarator )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==LPAREN||LA41_0==IDENTIFIER) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:503:14: directAbstractDeclarator
                            {
                            pushFollow(FOLLOW_directAbstractDeclarator_in_abstractDeclarator2816);
                            directAbstractDeclarator133=directAbstractDeclarator();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_directAbstractDeclarator.add(directAbstractDeclarator133.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: pointer, directAbstractDeclarator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 504:7: -> ^( TYPE_DECLARATOR ( pointer )+ ( directAbstractDeclarator )? )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:504:10: ^( TYPE_DECLARATOR ( pointer )+ ( directAbstractDeclarator )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_DECLARATOR, "TYPE_DECLARATOR"), root_1);

                        if ( !(stream_pointer.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_pointer.hasNext() ) {
                            adaptor.addChild(root_1, stream_pointer.nextTree());

                        }
                        stream_pointer.reset();
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:504:37: ( directAbstractDeclarator )?
                        if ( stream_directAbstractDeclarator.hasNext() ) {
                            adaptor.addChild(root_1, stream_directAbstractDeclarator.nextTree());

                        }
                        stream_directAbstractDeclarator.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:505:5: directAbstractDeclarator
                    {
                    pushFollow(FOLLOW_directAbstractDeclarator_in_abstractDeclarator2841);
                    directAbstractDeclarator134=directAbstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_directAbstractDeclarator.add(directAbstractDeclarator134.getTree());


                    // AST REWRITE
                    // elements: directAbstractDeclarator
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 506:7: -> ^( TYPE_DECLARATOR directAbstractDeclarator )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:506:10: ^( TYPE_DECLARATOR directAbstractDeclarator )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_DECLARATOR, "TYPE_DECLARATOR"), root_1);

                        adaptor.addChild(root_1, stream_directAbstractDeclarator.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "abstractDeclarator"

    public static class directAbstractDeclarator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "directAbstractDeclarator"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:509:1: directAbstractDeclarator : ( IDENTIFIER | ( LPAREN abstractDeclarator RPAREN ) ) ( OPENBRAC ( unaryExpression )? CLOSEBRAC )? ;
    public final CTFParser.directAbstractDeclarator_return directAbstractDeclarator() throws RecognitionException {
        CTFParser.directAbstractDeclarator_return retval = new CTFParser.directAbstractDeclarator_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER135=null;
        Token LPAREN136=null;
        Token RPAREN138=null;
        Token OPENBRAC139=null;
        Token CLOSEBRAC141=null;
        CTFParser.abstractDeclarator_return abstractDeclarator137 = null;

        CTFParser.unaryExpression_return unaryExpression140 = null;


        CommonTree IDENTIFIER135_tree=null;
        CommonTree LPAREN136_tree=null;
        CommonTree RPAREN138_tree=null;
        CommonTree OPENBRAC139_tree=null;
        CommonTree CLOSEBRAC141_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:515:3: ( ( IDENTIFIER | ( LPAREN abstractDeclarator RPAREN ) ) ( OPENBRAC ( unaryExpression )? CLOSEBRAC )? )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:515:5: ( IDENTIFIER | ( LPAREN abstractDeclarator RPAREN ) ) ( OPENBRAC ( unaryExpression )? CLOSEBRAC )?
            {
            root_0 = (CommonTree)adaptor.nil();

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:515:5: ( IDENTIFIER | ( LPAREN abstractDeclarator RPAREN ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==IDENTIFIER) ) {
                alt43=1;
            }
            else if ( (LA43_0==LPAREN) ) {
                alt43=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:516:7: IDENTIFIER
                    {
                    IDENTIFIER135=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_directAbstractDeclarator2878); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENTIFIER135_tree = (CommonTree)adaptor.create(IDENTIFIER135);
                    adaptor.addChild(root_0, IDENTIFIER135_tree);
                    }

                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:517:9: ( LPAREN abstractDeclarator RPAREN )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:517:9: ( LPAREN abstractDeclarator RPAREN )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:517:10: LPAREN abstractDeclarator RPAREN
                    {
                    LPAREN136=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_directAbstractDeclarator2889); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN136_tree = (CommonTree)adaptor.create(LPAREN136);
                    adaptor.addChild(root_0, LPAREN136_tree);
                    }
                    pushFollow(FOLLOW_abstractDeclarator_in_directAbstractDeclarator2891);
                    abstractDeclarator137=abstractDeclarator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, abstractDeclarator137.getTree());
                    RPAREN138=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_directAbstractDeclarator2893); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN138_tree = (CommonTree)adaptor.create(RPAREN138);
                    adaptor.addChild(root_0, RPAREN138_tree);
                    }

                    }


                    }
                    break;

            }

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:518:5: ( OPENBRAC ( unaryExpression )? CLOSEBRAC )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==OPENBRAC) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:519:7: OPENBRAC ( unaryExpression )? CLOSEBRAC
                    {
                    OPENBRAC139=(Token)match(input,OPENBRAC,FOLLOW_OPENBRAC_in_directAbstractDeclarator2908); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OPENBRAC139_tree = (CommonTree)adaptor.create(OPENBRAC139);
                    adaptor.addChild(root_0, OPENBRAC139_tree);
                    }
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:519:16: ( unaryExpression )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==ALIGNTOK||LA44_0==EVENTTOK||(LA44_0>=SIGNEDTOK && LA44_0<=STRINGTOK)||LA44_0==TRACETOK||(LA44_0>=ENVTOK && LA44_0<=CALLSITETOK)||LA44_0==SIGN||(LA44_0>=OCTAL_LITERAL && LA44_0<=HEX_LITERAL)||LA44_0==CHARACTER_LITERAL||LA44_0==STRING_LITERAL||LA44_0==IDENTIFIER) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:519:16: unaryExpression
                            {
                            pushFollow(FOLLOW_unaryExpression_in_directAbstractDeclarator2910);
                            unaryExpression140=unaryExpression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression140.getTree());

                            }
                            break;

                    }

                    CLOSEBRAC141=(Token)match(input,CLOSEBRAC,FOLLOW_CLOSEBRAC_in_directAbstractDeclarator2913); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CLOSEBRAC141_tree = (CommonTree)adaptor.create(CLOSEBRAC141);
                    adaptor.addChild(root_0, CLOSEBRAC141_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "directAbstractDeclarator"

    public static class pointer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pointer"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:523:1: pointer : POINTER ( typeQualifierList )? -> ^( POINTER ( typeQualifierList )? ) ;
    public final CTFParser.pointer_return pointer() throws RecognitionException {
        CTFParser.pointer_return retval = new CTFParser.pointer_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token POINTER142=null;
        CTFParser.typeQualifierList_return typeQualifierList143 = null;


        CommonTree POINTER142_tree=null;
        RewriteRuleTokenStream stream_POINTER=new RewriteRuleTokenStream(adaptor,"token POINTER");
        RewriteRuleSubtreeStream stream_typeQualifierList=new RewriteRuleSubtreeStream(adaptor,"rule typeQualifierList");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:524:3: ( POINTER ( typeQualifierList )? -> ^( POINTER ( typeQualifierList )? ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:524:5: POINTER ( typeQualifierList )?
            {
            POINTER142=(Token)match(input,POINTER,FOLLOW_POINTER_in_pointer2931); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_POINTER.add(POINTER142);

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:524:13: ( typeQualifierList )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==CONSTTOK) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:524:13: typeQualifierList
                    {
                    pushFollow(FOLLOW_typeQualifierList_in_pointer2933);
                    typeQualifierList143=typeQualifierList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeQualifierList.add(typeQualifierList143.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: typeQualifierList, POINTER
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 524:32: -> ^( POINTER ( typeQualifierList )? )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:524:35: ^( POINTER ( typeQualifierList )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_POINTER.nextNode(), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:524:45: ( typeQualifierList )?
                if ( stream_typeQualifierList.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeQualifierList.nextTree());

                }
                stream_typeQualifierList.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "pointer"

    public static class typeQualifierList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeQualifierList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:527:1: typeQualifierList : ( typeQualifier )+ ;
    public final CTFParser.typeQualifierList_return typeQualifierList() throws RecognitionException {
        CTFParser.typeQualifierList_return retval = new CTFParser.typeQualifierList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.typeQualifier_return typeQualifier144 = null;



        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:528:3: ( ( typeQualifier )+ )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:528:5: ( typeQualifier )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:528:5: ( typeQualifier )+
            int cnt47=0;
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==CONSTTOK) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:528:5: typeQualifier
            	    {
            	    pushFollow(FOLLOW_typeQualifier_in_typeQualifierList2956);
            	    typeQualifier144=typeQualifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeQualifier144.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt47 >= 1 ) break loop47;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(47, input);
                        throw eee;
                }
                cnt47++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeQualifierList"

    public static class typedefName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typedefName"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:531:1: typedefName : {...}? IDENTIFIER ;
    public final CTFParser.typedefName_return typedefName() throws RecognitionException {
        CTFParser.typedefName_return retval = new CTFParser.typedefName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER145=null;

        CommonTree IDENTIFIER145_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:532:3: ({...}? IDENTIFIER )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:532:5: {...}? IDENTIFIER
            {
            root_0 = (CommonTree)adaptor.nil();

            if ( !((inTypealiasAlias() || isTypeName(input.LT(1).getText()))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "typedefName", "inTypealiasAlias() || isTypeName(input.LT(1).getText())");
            }
            IDENTIFIER145=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_typedefName2972); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENTIFIER145_tree = (CommonTree)adaptor.create(IDENTIFIER145);
            adaptor.addChild(root_0, IDENTIFIER145_tree);
            }
            if ( state.backtracking==0 ) {
               if ((inTypedef() || inTypealiasAlias()) && !isTypeName((IDENTIFIER145!=null?IDENTIFIER145.getText():null))) { addTypeName((IDENTIFIER145!=null?IDENTIFIER145.getText():null)); } 
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typedefName"

    public static class typealiasTarget_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typealiasTarget"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:535:1: typealiasTarget : declarationSpecifiers ( abstractDeclaratorList )? ;
    public final CTFParser.typealiasTarget_return typealiasTarget() throws RecognitionException {
        CTFParser.typealiasTarget_return retval = new CTFParser.typealiasTarget_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.declarationSpecifiers_return declarationSpecifiers146 = null;

        CTFParser.abstractDeclaratorList_return abstractDeclaratorList147 = null;



        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:542:3: ( declarationSpecifiers ( abstractDeclaratorList )? )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:542:5: declarationSpecifiers ( abstractDeclaratorList )?
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_declarationSpecifiers_in_typealiasTarget2989);
            declarationSpecifiers146=declarationSpecifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, declarationSpecifiers146.getTree());
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:542:27: ( abstractDeclaratorList )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LPAREN||LA48_0==POINTER||LA48_0==IDENTIFIER) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:542:27: abstractDeclaratorList
                    {
                    pushFollow(FOLLOW_abstractDeclaratorList_in_typealiasTarget2991);
                    abstractDeclaratorList147=abstractDeclaratorList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, abstractDeclaratorList147.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typealiasTarget"

    public static class typealiasAlias_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typealiasAlias"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:545:1: typealiasAlias : ( abstractDeclaratorList | declarationSpecifiers ( abstractDeclaratorList )? );
    public final CTFParser.typealiasAlias_return typealiasAlias() throws RecognitionException {
        CTFParser.typealiasAlias_return retval = new CTFParser.typealiasAlias_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.abstractDeclaratorList_return abstractDeclaratorList148 = null;

        CTFParser.declarationSpecifiers_return declarationSpecifiers149 = null;

        CTFParser.abstractDeclaratorList_return abstractDeclaratorList150 = null;




            typealiasAliasOn();

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:558:3: ( abstractDeclaratorList | declarationSpecifiers ( abstractDeclaratorList )? )
            int alt50=2;
            switch ( input.LA(1) ) {
            case LPAREN:
            case POINTER:
                {
                alt50=1;
                }
                break;
            case IDENTIFIER:
                {
                int LA50_2 = input.LA(2);

                if ( (!(((( inTypealiasAlias() || isTypeName(input.LT(1).getText()) )&&(inTypealiasAlias() || isTypeName(input.LT(1).getText())))))) ) {
                    alt50=1;
                }
                else if ( ((( inTypealiasAlias() || isTypeName(input.LT(1).getText()) )&&(inTypealiasAlias() || isTypeName(input.LT(1).getText())))) ) {
                    alt50=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 2, input);

                    throw nvae;
                }
                }
                break;
            case CONSTTOK:
            case CHARTOK:
            case DOUBLETOK:
            case ENUMTOK:
            case FLOATINGPOINTTOK:
            case FLOATTOK:
            case INTEGERTOK:
            case INTTOK:
            case LONGTOK:
            case SHORTTOK:
            case SIGNEDTOK:
            case STRINGTOK:
            case STRUCTTOK:
            case TYPEDEFTOK:
            case UNSIGNEDTOK:
            case VARIANTTOK:
            case VOIDTOK:
            case BOOLTOK:
            case COMPLEXTOK:
            case IMAGINARYTOK:
                {
                alt50=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }

            switch (alt50) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:558:5: abstractDeclaratorList
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_abstractDeclaratorList_in_typealiasAlias3017);
                    abstractDeclaratorList148=abstractDeclaratorList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, abstractDeclaratorList148.getTree());

                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:559:5: declarationSpecifiers ( abstractDeclaratorList )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_declarationSpecifiers_in_typealiasAlias3023);
                    declarationSpecifiers149=declarationSpecifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, declarationSpecifiers149.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:559:27: ( abstractDeclaratorList )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==LPAREN||LA49_0==POINTER||LA49_0==IDENTIFIER) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:559:27: abstractDeclaratorList
                            {
                            pushFollow(FOLLOW_abstractDeclaratorList_in_typealiasAlias3025);
                            abstractDeclaratorList150=abstractDeclaratorList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, abstractDeclaratorList150.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  typealiasAliasOff();

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typealiasAlias"

    public static class typealiasDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typealiasDecl"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:562:1: typealiasDecl : TYPEALIASTOK typealiasTarget TYPE_ASSIGNMENT typealiasAlias -> ^( TYPEALIAS ^( TYPEALIAS_TARGET typealiasTarget ) ^( TYPEALIAS_ALIAS typealiasAlias ) ) ;
    public final CTFParser.typealiasDecl_return typealiasDecl() throws RecognitionException {
        CTFParser.typealiasDecl_return retval = new CTFParser.typealiasDecl_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TYPEALIASTOK151=null;
        Token TYPE_ASSIGNMENT153=null;
        CTFParser.typealiasTarget_return typealiasTarget152 = null;

        CTFParser.typealiasAlias_return typealiasAlias154 = null;


        CommonTree TYPEALIASTOK151_tree=null;
        CommonTree TYPE_ASSIGNMENT153_tree=null;
        RewriteRuleTokenStream stream_TYPE_ASSIGNMENT=new RewriteRuleTokenStream(adaptor,"token TYPE_ASSIGNMENT");
        RewriteRuleTokenStream stream_TYPEALIASTOK=new RewriteRuleTokenStream(adaptor,"token TYPEALIASTOK");
        RewriteRuleSubtreeStream stream_typealiasAlias=new RewriteRuleSubtreeStream(adaptor,"rule typealiasAlias");
        RewriteRuleSubtreeStream stream_typealiasTarget=new RewriteRuleSubtreeStream(adaptor,"rule typealiasTarget");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:563:3: ( TYPEALIASTOK typealiasTarget TYPE_ASSIGNMENT typealiasAlias -> ^( TYPEALIAS ^( TYPEALIAS_TARGET typealiasTarget ) ^( TYPEALIAS_ALIAS typealiasAlias ) ) )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:563:5: TYPEALIASTOK typealiasTarget TYPE_ASSIGNMENT typealiasAlias
            {
            TYPEALIASTOK151=(Token)match(input,TYPEALIASTOK,FOLLOW_TYPEALIASTOK_in_typealiasDecl3039); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TYPEALIASTOK.add(TYPEALIASTOK151);

            pushFollow(FOLLOW_typealiasTarget_in_typealiasDecl3041);
            typealiasTarget152=typealiasTarget();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typealiasTarget.add(typealiasTarget152.getTree());
            TYPE_ASSIGNMENT153=(Token)match(input,TYPE_ASSIGNMENT,FOLLOW_TYPE_ASSIGNMENT_in_typealiasDecl3043); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TYPE_ASSIGNMENT.add(TYPE_ASSIGNMENT153);

            pushFollow(FOLLOW_typealiasAlias_in_typealiasDecl3045);
            typealiasAlias154=typealiasAlias();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typealiasAlias.add(typealiasAlias154.getTree());


            // AST REWRITE
            // elements: typealiasAlias, typealiasTarget
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 564:7: -> ^( TYPEALIAS ^( TYPEALIAS_TARGET typealiasTarget ) ^( TYPEALIAS_ALIAS typealiasAlias ) )
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:564:10: ^( TYPEALIAS ^( TYPEALIAS_TARGET typealiasTarget ) ^( TYPEALIAS_ALIAS typealiasAlias ) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPEALIAS, "TYPEALIAS"), root_1);

                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:565:14: ^( TYPEALIAS_TARGET typealiasTarget )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPEALIAS_TARGET, "TYPEALIAS_TARGET"), root_2);

                adaptor.addChild(root_2, stream_typealiasTarget.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:566:14: ^( TYPEALIAS_ALIAS typealiasAlias )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPEALIAS_ALIAS, "TYPEALIAS_ALIAS"), root_2);

                adaptor.addChild(root_2, stream_typealiasAlias.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typealiasDecl"

    public static class ctfKeyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ctfKeyword"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:572:1: ctfKeyword : ( ALIGNTOK | EVENTTOK | SIGNEDTOK | STRINGTOK );
    public final CTFParser.ctfKeyword_return ctfKeyword() throws RecognitionException {
        CTFParser.ctfKeyword_return retval = new CTFParser.ctfKeyword_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set155=null;

        CommonTree set155_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:573:3: ( ALIGNTOK | EVENTTOK | SIGNEDTOK | STRINGTOK )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set155=(Token)input.LT(1);
            if ( input.LA(1)==ALIGNTOK||input.LA(1)==EVENTTOK||input.LA(1)==SIGNEDTOK||input.LA(1)==STRINGTOK ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set155));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ctfKeyword"

    public static class ctfSpecifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ctfSpecifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:579:1: ctfSpecifier : ( ctfSpecifierHead ctfBody -> ^( ctfSpecifierHead ctfBody ) | typealiasDecl -> ^( DECLARATION typealiasDecl ) );
    public final CTFParser.ctfSpecifier_return ctfSpecifier() throws RecognitionException {
        CTFParser.ctfSpecifier_return retval = new CTFParser.ctfSpecifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CTFParser.ctfSpecifierHead_return ctfSpecifierHead156 = null;

        CTFParser.ctfBody_return ctfBody157 = null;

        CTFParser.typealiasDecl_return typealiasDecl158 = null;


        RewriteRuleSubtreeStream stream_ctfSpecifierHead=new RewriteRuleSubtreeStream(adaptor,"rule ctfSpecifierHead");
        RewriteRuleSubtreeStream stream_typealiasDecl=new RewriteRuleSubtreeStream(adaptor,"rule typealiasDecl");
        RewriteRuleSubtreeStream stream_ctfBody=new RewriteRuleSubtreeStream(adaptor,"rule ctfBody");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:581:3: ( ctfSpecifierHead ctfBody -> ^( ctfSpecifierHead ctfBody ) | typealiasDecl -> ^( DECLARATION typealiasDecl ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==EVENTTOK||LA51_0==STREAMTOK||LA51_0==TRACETOK||(LA51_0>=ENVTOK && LA51_0<=CALLSITETOK)) ) {
                alt51=1;
            }
            else if ( (LA51_0==TYPEALIASTOK) ) {
                alt51=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:581:5: ctfSpecifierHead ctfBody
                    {
                    pushFollow(FOLLOW_ctfSpecifierHead_in_ctfSpecifier3145);
                    ctfSpecifierHead156=ctfSpecifierHead();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ctfSpecifierHead.add(ctfSpecifierHead156.getTree());
                    pushFollow(FOLLOW_ctfBody_in_ctfSpecifier3147);
                    ctfBody157=ctfBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ctfBody.add(ctfBody157.getTree());


                    // AST REWRITE
                    // elements: ctfBody, ctfSpecifierHead
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 581:30: -> ^( ctfSpecifierHead ctfBody )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:581:33: ^( ctfSpecifierHead ctfBody )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ctfSpecifierHead.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ctfBody.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:583:5: typealiasDecl
                    {
                    pushFollow(FOLLOW_typealiasDecl_in_ctfSpecifier3164);
                    typealiasDecl158=typealiasDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typealiasDecl.add(typealiasDecl158.getTree());


                    // AST REWRITE
                    // elements: typealiasDecl
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 583:19: -> ^( DECLARATION typealiasDecl )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:583:22: ^( DECLARATION typealiasDecl )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(DECLARATION, "DECLARATION"), root_1);

                        adaptor.addChild(root_1, stream_typealiasDecl.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ctfSpecifier"

    public static class ctfSpecifierHead_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ctfSpecifierHead"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:586:1: ctfSpecifierHead : ( EVENTTOK -> EVENT | STREAMTOK -> STREAM | TRACETOK -> TRACE | ENVTOK -> ENV | CLOCKTOK -> CLOCK | CALLSITETOK -> CALLSITE );
    public final CTFParser.ctfSpecifierHead_return ctfSpecifierHead() throws RecognitionException {
        CTFParser.ctfSpecifierHead_return retval = new CTFParser.ctfSpecifierHead_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EVENTTOK159=null;
        Token STREAMTOK160=null;
        Token TRACETOK161=null;
        Token ENVTOK162=null;
        Token CLOCKTOK163=null;
        Token CALLSITETOK164=null;

        CommonTree EVENTTOK159_tree=null;
        CommonTree STREAMTOK160_tree=null;
        CommonTree TRACETOK161_tree=null;
        CommonTree ENVTOK162_tree=null;
        CommonTree CLOCKTOK163_tree=null;
        CommonTree CALLSITETOK164_tree=null;
        RewriteRuleTokenStream stream_EVENTTOK=new RewriteRuleTokenStream(adaptor,"token EVENTTOK");
        RewriteRuleTokenStream stream_CALLSITETOK=new RewriteRuleTokenStream(adaptor,"token CALLSITETOK");
        RewriteRuleTokenStream stream_STREAMTOK=new RewriteRuleTokenStream(adaptor,"token STREAMTOK");
        RewriteRuleTokenStream stream_ENVTOK=new RewriteRuleTokenStream(adaptor,"token ENVTOK");
        RewriteRuleTokenStream stream_CLOCKTOK=new RewriteRuleTokenStream(adaptor,"token CLOCKTOK");
        RewriteRuleTokenStream stream_TRACETOK=new RewriteRuleTokenStream(adaptor,"token TRACETOK");

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:587:3: ( EVENTTOK -> EVENT | STREAMTOK -> STREAM | TRACETOK -> TRACE | ENVTOK -> ENV | CLOCKTOK -> CLOCK | CALLSITETOK -> CALLSITE )
            int alt52=6;
            switch ( input.LA(1) ) {
            case EVENTTOK:
                {
                alt52=1;
                }
                break;
            case STREAMTOK:
                {
                alt52=2;
                }
                break;
            case TRACETOK:
                {
                alt52=3;
                }
                break;
            case ENVTOK:
                {
                alt52=4;
                }
                break;
            case CLOCKTOK:
                {
                alt52=5;
                }
                break;
            case CALLSITETOK:
                {
                alt52=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }

            switch (alt52) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:587:5: EVENTTOK
                    {
                    EVENTTOK159=(Token)match(input,EVENTTOK,FOLLOW_EVENTTOK_in_ctfSpecifierHead3185); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_EVENTTOK.add(EVENTTOK159);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 587:14: -> EVENT
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(EVENT, "EVENT"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:588:5: STREAMTOK
                    {
                    STREAMTOK160=(Token)match(input,STREAMTOK,FOLLOW_STREAMTOK_in_ctfSpecifierHead3195); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STREAMTOK.add(STREAMTOK160);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 588:15: -> STREAM
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(STREAM, "STREAM"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:589:5: TRACETOK
                    {
                    TRACETOK161=(Token)match(input,TRACETOK,FOLLOW_TRACETOK_in_ctfSpecifierHead3205); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TRACETOK.add(TRACETOK161);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 589:14: -> TRACE
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(TRACE, "TRACE"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:590:5: ENVTOK
                    {
                    ENVTOK162=(Token)match(input,ENVTOK,FOLLOW_ENVTOK_in_ctfSpecifierHead3215); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ENVTOK.add(ENVTOK162);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 590:12: -> ENV
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ENV, "ENV"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:591:5: CLOCKTOK
                    {
                    CLOCKTOK163=(Token)match(input,CLOCKTOK,FOLLOW_CLOCKTOK_in_ctfSpecifierHead3225); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CLOCKTOK.add(CLOCKTOK163);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 591:14: -> CLOCK
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(CLOCK, "CLOCK"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 6 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:592:5: CALLSITETOK
                    {
                    CALLSITETOK164=(Token)match(input,CALLSITETOK,FOLLOW_CALLSITETOK_in_ctfSpecifierHead3235); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CALLSITETOK.add(CALLSITETOK164);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 592:17: -> CALLSITE
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(CALLSITE, "CALLSITE"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ctfSpecifierHead"

    public static class ctfTypeSpecifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ctfTypeSpecifier"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:595:1: ctfTypeSpecifier : ( FLOATINGPOINTTOK ctfBody -> ^( FLOATING_POINT ( ctfBody )? ) | INTEGERTOK ctfBody -> ^( INTEGER ( ctfBody )? ) | STRINGTOK ( ctfBody )? -> ^( STRING ( ctfBody )? ) );
    public final CTFParser.ctfTypeSpecifier_return ctfTypeSpecifier() throws RecognitionException {
        CTFParser.ctfTypeSpecifier_return retval = new CTFParser.ctfTypeSpecifier_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token FLOATINGPOINTTOK165=null;
        Token INTEGERTOK167=null;
        Token STRINGTOK169=null;
        CTFParser.ctfBody_return ctfBody166 = null;

        CTFParser.ctfBody_return ctfBody168 = null;

        CTFParser.ctfBody_return ctfBody170 = null;


        CommonTree FLOATINGPOINTTOK165_tree=null;
        CommonTree INTEGERTOK167_tree=null;
        CommonTree STRINGTOK169_tree=null;
        RewriteRuleTokenStream stream_FLOATINGPOINTTOK=new RewriteRuleTokenStream(adaptor,"token FLOATINGPOINTTOK");
        RewriteRuleTokenStream stream_STRINGTOK=new RewriteRuleTokenStream(adaptor,"token STRINGTOK");
        RewriteRuleTokenStream stream_INTEGERTOK=new RewriteRuleTokenStream(adaptor,"token INTEGERTOK");
        RewriteRuleSubtreeStream stream_ctfBody=new RewriteRuleSubtreeStream(adaptor,"rule ctfBody");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:597:3: ( FLOATINGPOINTTOK ctfBody -> ^( FLOATING_POINT ( ctfBody )? ) | INTEGERTOK ctfBody -> ^( INTEGER ( ctfBody )? ) | STRINGTOK ( ctfBody )? -> ^( STRING ( ctfBody )? ) )
            int alt54=3;
            switch ( input.LA(1) ) {
            case FLOATINGPOINTTOK:
                {
                alt54=1;
                }
                break;
            case INTEGERTOK:
                {
                alt54=2;
                }
                break;
            case STRINGTOK:
                {
                alt54=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:597:5: FLOATINGPOINTTOK ctfBody
                    {
                    FLOATINGPOINTTOK165=(Token)match(input,FLOATINGPOINTTOK,FOLLOW_FLOATINGPOINTTOK_in_ctfTypeSpecifier3258); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FLOATINGPOINTTOK.add(FLOATINGPOINTTOK165);

                    pushFollow(FOLLOW_ctfBody_in_ctfTypeSpecifier3260);
                    ctfBody166=ctfBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ctfBody.add(ctfBody166.getTree());


                    // AST REWRITE
                    // elements: ctfBody
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 597:30: -> ^( FLOATING_POINT ( ctfBody )? )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:597:33: ^( FLOATING_POINT ( ctfBody )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(FLOATING_POINT, "FLOATING_POINT"), root_1);

                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:597:50: ( ctfBody )?
                        if ( stream_ctfBody.hasNext() ) {
                            adaptor.addChild(root_1, stream_ctfBody.nextTree());

                        }
                        stream_ctfBody.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:598:5: INTEGERTOK ctfBody
                    {
                    INTEGERTOK167=(Token)match(input,INTEGERTOK,FOLLOW_INTEGERTOK_in_ctfTypeSpecifier3275); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INTEGERTOK.add(INTEGERTOK167);

                    pushFollow(FOLLOW_ctfBody_in_ctfTypeSpecifier3277);
                    ctfBody168=ctfBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ctfBody.add(ctfBody168.getTree());


                    // AST REWRITE
                    // elements: ctfBody
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 598:24: -> ^( INTEGER ( ctfBody )? )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:598:27: ^( INTEGER ( ctfBody )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(INTEGER, "INTEGER"), root_1);

                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:598:37: ( ctfBody )?
                        if ( stream_ctfBody.hasNext() ) {
                            adaptor.addChild(root_1, stream_ctfBody.nextTree());

                        }
                        stream_ctfBody.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:599:5: STRINGTOK ( ctfBody )?
                    {
                    STRINGTOK169=(Token)match(input,STRINGTOK,FOLLOW_STRINGTOK_in_ctfTypeSpecifier3292); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRINGTOK.add(STRINGTOK169);

                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:599:15: ( ctfBody )?
                    int alt53=2;
                    alt53 = dfa53.predict(input);
                    switch (alt53) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:599:15: ctfBody
                            {
                            pushFollow(FOLLOW_ctfBody_in_ctfTypeSpecifier3294);
                            ctfBody170=ctfBody();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ctfBody.add(ctfBody170.getTree());

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: ctfBody
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 599:24: -> ^( STRING ( ctfBody )? )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:599:27: ^( STRING ( ctfBody )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(STRING, "STRING"), root_1);

                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:599:36: ( ctfBody )?
                        if ( stream_ctfBody.hasNext() ) {
                            adaptor.addChild(root_1, stream_ctfBody.nextTree());

                        }
                        stream_ctfBody.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ctfTypeSpecifier"

    public static class ctfBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ctfBody"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:602:1: ctfBody : LCURL ( ctfAssignmentExpressionList )? RCURL -> ( ctfAssignmentExpressionList )? ;
    public final CTFParser.ctfBody_return ctfBody() throws RecognitionException {
        Symbols_stack.push(new Symbols_scope());

        CTFParser.ctfBody_return retval = new CTFParser.ctfBody_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LCURL171=null;
        Token RCURL173=null;
        CTFParser.ctfAssignmentExpressionList_return ctfAssignmentExpressionList172 = null;


        CommonTree LCURL171_tree=null;
        CommonTree RCURL173_tree=null;
        RewriteRuleTokenStream stream_LCURL=new RewriteRuleTokenStream(adaptor,"token LCURL");
        RewriteRuleTokenStream stream_RCURL=new RewriteRuleTokenStream(adaptor,"token RCURL");
        RewriteRuleSubtreeStream stream_ctfAssignmentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule ctfAssignmentExpressionList");

            ((Symbols_scope)Symbols_stack.peek()).types = new HashSet<String>();

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:607:3: ( LCURL ( ctfAssignmentExpressionList )? RCURL -> ( ctfAssignmentExpressionList )? )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:607:5: LCURL ( ctfAssignmentExpressionList )? RCURL
            {
            LCURL171=(Token)match(input,LCURL,FOLLOW_LCURL_in_ctfBody3327); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURL.add(LCURL171);

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:607:11: ( ctfAssignmentExpressionList )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( ((LA55_0>=ALIGNTOK && LA55_0<=CALLSITETOK)||LA55_0==SIGN||(LA55_0>=OCTAL_LITERAL && LA55_0<=HEX_LITERAL)||LA55_0==CHARACTER_LITERAL||LA55_0==STRING_LITERAL||LA55_0==IDENTIFIER) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:607:11: ctfAssignmentExpressionList
                    {
                    pushFollow(FOLLOW_ctfAssignmentExpressionList_in_ctfBody3329);
                    ctfAssignmentExpressionList172=ctfAssignmentExpressionList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ctfAssignmentExpressionList.add(ctfAssignmentExpressionList172.getTree());

                    }
                    break;

            }

            RCURL173=(Token)match(input,RCURL,FOLLOW_RCURL_in_ctfBody3332); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURL.add(RCURL173);



            // AST REWRITE
            // elements: ctfAssignmentExpressionList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 607:46: -> ( ctfAssignmentExpressionList )?
            {
                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:607:49: ( ctfAssignmentExpressionList )?
                if ( stream_ctfAssignmentExpressionList.hasNext() ) {
                    adaptor.addChild(root_0, stream_ctfAssignmentExpressionList.nextTree());

                }
                stream_ctfAssignmentExpressionList.reset();

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
            Symbols_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "ctfBody"

    public static class ctfAssignmentExpressionList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ctfAssignmentExpressionList"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:610:1: ctfAssignmentExpressionList : ( ctfAssignmentExpression TERM )+ ;
    public final CTFParser.ctfAssignmentExpressionList_return ctfAssignmentExpressionList() throws RecognitionException {
        CTFParser.ctfAssignmentExpressionList_return retval = new CTFParser.ctfAssignmentExpressionList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TERM175=null;
        CTFParser.ctfAssignmentExpression_return ctfAssignmentExpression174 = null;


        CommonTree TERM175_tree=null;

        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:611:3: ( ( ctfAssignmentExpression TERM )+ )
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:611:5: ( ctfAssignmentExpression TERM )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:611:5: ( ctfAssignmentExpression TERM )+
            int cnt56=0;
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( ((LA56_0>=ALIGNTOK && LA56_0<=CALLSITETOK)||LA56_0==SIGN||(LA56_0>=OCTAL_LITERAL && LA56_0<=HEX_LITERAL)||LA56_0==CHARACTER_LITERAL||LA56_0==STRING_LITERAL||LA56_0==IDENTIFIER) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:611:6: ctfAssignmentExpression TERM
            	    {
            	    pushFollow(FOLLOW_ctfAssignmentExpression_in_ctfAssignmentExpressionList3351);
            	    ctfAssignmentExpression174=ctfAssignmentExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, ctfAssignmentExpression174.getTree());
            	    TERM175=(Token)match(input,TERM,FOLLOW_TERM_in_ctfAssignmentExpressionList3353); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt56 >= 1 ) break loop56;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(56, input);
                        throw eee;
                }
                cnt56++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ctfAssignmentExpressionList"

    public static class ctfAssignmentExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ctfAssignmentExpression"
    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:614:1: ctfAssignmentExpression : (left= unaryExpression (assignment= ASSIGNMENT right1= unaryExpression -> ^( CTF_EXPRESSION_VAL ^( CTF_LEFT $left) ^( CTF_RIGHT $right1) ) | type_assignment= TYPE_ASSIGNMENT right2= typeSpecifier -> ^( CTF_EXPRESSION_TYPE ^( CTF_LEFT $left) ^( CTF_RIGHT ^( TYPE_SPECIFIER_LIST $right2) ) ) ) | ( declarationSpecifiers {...}? declaratorList ) -> ^( TYPEDEF declaratorList declarationSpecifiers ) | typealiasDecl );
    public final CTFParser.ctfAssignmentExpression_return ctfAssignmentExpression() throws RecognitionException {
        CTFParser.ctfAssignmentExpression_return retval = new CTFParser.ctfAssignmentExpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token assignment=null;
        Token type_assignment=null;
        CTFParser.unaryExpression_return left = null;

        CTFParser.unaryExpression_return right1 = null;

        CTFParser.typeSpecifier_return right2 = null;

        CTFParser.declarationSpecifiers_return declarationSpecifiers176 = null;

        CTFParser.declaratorList_return declaratorList177 = null;

        CTFParser.typealiasDecl_return typealiasDecl178 = null;


        CommonTree assignment_tree=null;
        CommonTree type_assignment_tree=null;
        RewriteRuleTokenStream stream_ASSIGNMENT=new RewriteRuleTokenStream(adaptor,"token ASSIGNMENT");
        RewriteRuleTokenStream stream_TYPE_ASSIGNMENT=new RewriteRuleTokenStream(adaptor,"token TYPE_ASSIGNMENT");
        RewriteRuleSubtreeStream stream_declaratorList=new RewriteRuleSubtreeStream(adaptor,"rule declaratorList");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        RewriteRuleSubtreeStream stream_declarationSpecifiers=new RewriteRuleSubtreeStream(adaptor,"rule declarationSpecifiers");
        RewriteRuleSubtreeStream stream_typeSpecifier=new RewriteRuleSubtreeStream(adaptor,"rule typeSpecifier");
        try {
            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:620:3: (left= unaryExpression (assignment= ASSIGNMENT right1= unaryExpression -> ^( CTF_EXPRESSION_VAL ^( CTF_LEFT $left) ^( CTF_RIGHT $right1) ) | type_assignment= TYPE_ASSIGNMENT right2= typeSpecifier -> ^( CTF_EXPRESSION_TYPE ^( CTF_LEFT $left) ^( CTF_RIGHT ^( TYPE_SPECIFIER_LIST $right2) ) ) ) | ( declarationSpecifiers {...}? declaratorList ) -> ^( TYPEDEF declaratorList declarationSpecifiers ) | typealiasDecl )
            int alt58=3;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                int LA58_1 = input.LA(2);

                if ( ((LA58_1>=ASSIGNMENT && LA58_1<=TYPE_ASSIGNMENT)||LA58_1==OPENBRAC||(LA58_1>=ARROW && LA58_1<=DOT)) ) {
                    alt58=1;
                }
                else if ( ((LA58_1>=CONSTTOK && LA58_1<=ENUMTOK)||(LA58_1>=FLOATINGPOINTTOK && LA58_1<=SIGNEDTOK)||(LA58_1>=STRINGTOK && LA58_1<=STRUCTTOK)||(LA58_1>=TYPEDEFTOK && LA58_1<=IMAGINARYTOK)||LA58_1==POINTER||LA58_1==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {
                    alt58=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 1, input);

                    throw nvae;
                }
                }
                break;
            case ALIGNTOK:
            case EVENTTOK:
            case STREAMTOK:
            case TRACETOK:
            case ENVTOK:
            case CLOCKTOK:
            case CALLSITETOK:
            case SIGN:
            case OCTAL_LITERAL:
            case DECIMAL_LITERAL:
            case HEX_LITERAL:
            case CHARACTER_LITERAL:
            case STRING_LITERAL:
                {
                alt58=1;
                }
                break;
            case SIGNEDTOK:
                {
                switch ( input.LA(2) ) {
                case ASSIGNMENT:
                case TYPE_ASSIGNMENT:
                case OPENBRAC:
                case ARROW:
                case DOT:
                    {
                    alt58=1;
                    }
                    break;
                case CONSTTOK:
                case CHARTOK:
                case DOUBLETOK:
                case ENUMTOK:
                case FLOATINGPOINTTOK:
                case FLOATTOK:
                case INTEGERTOK:
                case INTTOK:
                case LONGTOK:
                case SHORTTOK:
                case SIGNEDTOK:
                case STRINGTOK:
                case STRUCTTOK:
                case UNSIGNEDTOK:
                case VARIANTTOK:
                case VOIDTOK:
                case BOOLTOK:
                case COMPLEXTOK:
                case IMAGINARYTOK:
                case POINTER:
                case IDENTIFIER:
                    {
                    alt58=2;
                    }
                    break;
                case TYPEDEFTOK:
                    {
                    alt58=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 3, input);

                    throw nvae;
                }

                }
                break;
            case CONSTTOK:
            case CHARTOK:
            case DOUBLETOK:
            case ENUMTOK:
            case FLOATINGPOINTTOK:
            case FLOATTOK:
            case INTEGERTOK:
            case INTTOK:
            case LONGTOK:
            case SHORTTOK:
            case STRUCTTOK:
            case TYPEDEFTOK:
            case UNSIGNEDTOK:
            case VARIANTTOK:
            case VOIDTOK:
            case BOOLTOK:
            case COMPLEXTOK:
            case IMAGINARYTOK:
                {
                alt58=2;
                }
                break;
            case STRINGTOK:
                {
                switch ( input.LA(2) ) {
                case CONSTTOK:
                case CHARTOK:
                case DOUBLETOK:
                case ENUMTOK:
                case FLOATINGPOINTTOK:
                case FLOATTOK:
                case INTEGERTOK:
                case INTTOK:
                case LONGTOK:
                case SHORTTOK:
                case SIGNEDTOK:
                case STRINGTOK:
                case STRUCTTOK:
                case UNSIGNEDTOK:
                case VARIANTTOK:
                case VOIDTOK:
                case BOOLTOK:
                case COMPLEXTOK:
                case IMAGINARYTOK:
                case LCURL:
                case POINTER:
                case IDENTIFIER:
                    {
                    alt58=2;
                    }
                    break;
                case TYPEDEFTOK:
                    {
                    alt58=2;
                    }
                    break;
                case ASSIGNMENT:
                case TYPE_ASSIGNMENT:
                case OPENBRAC:
                case ARROW:
                case DOT:
                    {
                    alt58=1;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 5, input);

                    throw nvae;
                }

                }
                break;
            case TYPEALIASTOK:
                {
                alt58=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:620:5: left= unaryExpression (assignment= ASSIGNMENT right1= unaryExpression -> ^( CTF_EXPRESSION_VAL ^( CTF_LEFT $left) ^( CTF_RIGHT $right1) ) | type_assignment= TYPE_ASSIGNMENT right2= typeSpecifier -> ^( CTF_EXPRESSION_TYPE ^( CTF_LEFT $left) ^( CTF_RIGHT ^( TYPE_SPECIFIER_LIST $right2) ) ) )
                    {
                    pushFollow(FOLLOW_unaryExpression_in_ctfAssignmentExpression3376);
                    left=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(left.getTree());
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:621:7: (assignment= ASSIGNMENT right1= unaryExpression -> ^( CTF_EXPRESSION_VAL ^( CTF_LEFT $left) ^( CTF_RIGHT $right1) ) | type_assignment= TYPE_ASSIGNMENT right2= typeSpecifier -> ^( CTF_EXPRESSION_TYPE ^( CTF_LEFT $left) ^( CTF_RIGHT ^( TYPE_SPECIFIER_LIST $right2) ) ) )
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==ASSIGNMENT) ) {
                        alt57=1;
                    }
                    else if ( (LA57_0==TYPE_ASSIGNMENT) ) {
                        alt57=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 57, 0, input);

                        throw nvae;
                    }
                    switch (alt57) {
                        case 1 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:621:9: assignment= ASSIGNMENT right1= unaryExpression
                            {
                            assignment=(Token)match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_ctfAssignmentExpression3388); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ASSIGNMENT.add(assignment);

                            pushFollow(FOLLOW_unaryExpression_in_ctfAssignmentExpression3392);
                            right1=unaryExpression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_unaryExpression.add(right1.getTree());


                            // AST REWRITE
                            // elements: left, right1
                            // token labels: 
                            // rule labels: retval, left, right1
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);
                            RewriteRuleSubtreeStream stream_right1=new RewriteRuleSubtreeStream(adaptor,"rule right1",right1!=null?right1.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 622:11: -> ^( CTF_EXPRESSION_VAL ^( CTF_LEFT $left) ^( CTF_RIGHT $right1) )
                            {
                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:622:14: ^( CTF_EXPRESSION_VAL ^( CTF_LEFT $left) ^( CTF_RIGHT $right1) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CTF_EXPRESSION_VAL, "CTF_EXPRESSION_VAL"), root_1);

                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:623:18: ^( CTF_LEFT $left)
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CTF_LEFT, "CTF_LEFT"), root_2);

                                adaptor.addChild(root_2, stream_left.nextTree());

                                adaptor.addChild(root_1, root_2);
                                }
                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:624:18: ^( CTF_RIGHT $right1)
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CTF_RIGHT, "CTF_RIGHT"), root_2);

                                adaptor.addChild(root_2, stream_right1.nextTree());

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:625:9: type_assignment= TYPE_ASSIGNMENT right2= typeSpecifier
                            {
                            type_assignment=(Token)match(input,TYPE_ASSIGNMENT,FOLLOW_TYPE_ASSIGNMENT_in_ctfAssignmentExpression3468); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_TYPE_ASSIGNMENT.add(type_assignment);

                            pushFollow(FOLLOW_typeSpecifier_in_ctfAssignmentExpression3472);
                            right2=typeSpecifier();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_typeSpecifier.add(right2.getTree());


                            // AST REWRITE
                            // elements: right2, left
                            // token labels: 
                            // rule labels: retval, left, right2
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);
                            RewriteRuleSubtreeStream stream_right2=new RewriteRuleSubtreeStream(adaptor,"rule right2",right2!=null?right2.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 626:11: -> ^( CTF_EXPRESSION_TYPE ^( CTF_LEFT $left) ^( CTF_RIGHT ^( TYPE_SPECIFIER_LIST $right2) ) )
                            {
                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:626:14: ^( CTF_EXPRESSION_TYPE ^( CTF_LEFT $left) ^( CTF_RIGHT ^( TYPE_SPECIFIER_LIST $right2) ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CTF_EXPRESSION_TYPE, "CTF_EXPRESSION_TYPE"), root_1);

                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:627:18: ^( CTF_LEFT $left)
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CTF_LEFT, "CTF_LEFT"), root_2);

                                adaptor.addChild(root_2, stream_left.nextTree());

                                adaptor.addChild(root_1, root_2);
                                }
                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:628:18: ^( CTF_RIGHT ^( TYPE_SPECIFIER_LIST $right2) )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CTF_RIGHT, "CTF_RIGHT"), root_2);

                                // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:628:30: ^( TYPE_SPECIFIER_LIST $right2)
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPE_SPECIFIER_LIST, "TYPE_SPECIFIER_LIST"), root_3);

                                adaptor.addChild(root_3, stream_right2.nextTree());

                                adaptor.addChild(root_2, root_3);
                                }

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:630:5: ( declarationSpecifiers {...}? declaratorList )
                    {
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:630:5: ( declarationSpecifiers {...}? declaratorList )
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:630:6: declarationSpecifiers {...}? declaratorList
                    {
                    pushFollow(FOLLOW_declarationSpecifiers_in_ctfAssignmentExpression3555);
                    declarationSpecifiers176=declarationSpecifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declarationSpecifiers.add(declarationSpecifiers176.getTree());
                    if ( !((inTypedef())) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "ctfAssignmentExpression", "inTypedef()");
                    }
                    pushFollow(FOLLOW_declaratorList_in_ctfAssignmentExpression3559);
                    declaratorList177=declaratorList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declaratorList.add(declaratorList177.getTree());

                    }



                    // AST REWRITE
                    // elements: declaratorList, declarationSpecifiers
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 631:7: -> ^( TYPEDEF declaratorList declarationSpecifiers )
                    {
                        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:631:10: ^( TYPEDEF declaratorList declarationSpecifiers )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TYPEDEF, "TYPEDEF"), root_1);

                        adaptor.addChild(root_1, stream_declaratorList.nextTree());
                        adaptor.addChild(root_1, stream_declarationSpecifiers.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:632:5: typealiasDecl
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_typealiasDecl_in_ctfAssignmentExpression3582);
                    typealiasDecl178=typealiasDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typealiasDecl178.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (inTypedef()) {
                      typedefOff();
                  }

            }
        }

            catch (RecognitionException e) {
                throw e;
            }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ctfAssignmentExpression"

    // $ANTLR start synpred1_CTFParser
    public final void synpred1_CTFParser_fragment() throws RecognitionException {   
        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:194:5: ( IDENTIFIER )
        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:194:6: IDENTIFIER
        {
        match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_synpred1_CTFParser560); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_CTFParser

    // $ANTLR start synpred2_CTFParser
    public final void synpred2_CTFParser_fragment() throws RecognitionException {   
        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:196:5: ( ctfKeyword )
        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:196:6: ctfKeyword
        {
        pushFollow(FOLLOW_ctfKeyword_in_synpred2_CTFParser586);
        ctfKeyword();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_CTFParser

    // $ANTLR start synpred3_CTFParser
    public final void synpred3_CTFParser_fragment() throws RecognitionException {   
        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:197:5: ( STRING_LITERAL )
        // src/main/antlr3/org/eclipse/linuxtools/ctf/parser/CTFParser.g:197:6: STRING_LITERAL
        {
        match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_synpred3_CTFParser606); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_CTFParser

    // Delegated rules

    public final boolean synpred2_CTFParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_CTFParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_CTFParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_CTFParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_CTFParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_CTFParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA23 dfa23 = new DFA23(this);
    protected DFA31 dfa31 = new DFA31(this);
    protected DFA53 dfa53 = new DFA53(this);
    static final String DFA4_eotS =
        "\12\uffff";
    static final String DFA4_eofS =
        "\12\uffff";
    static final String DFA4_minS =
        "\1\4\3\0\6\uffff";
    static final String DFA4_maxS =
        "\1\121\3\0\6\uffff";
    static final String DFA4_acceptS =
        "\4\uffff\1\4\1\6\1\1\1\5\1\2\1\3";
    static final String DFA4_specialS =
        "\1\uffff\1\0\1\1\1\2\6\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\2\4\uffff\1\2\6\uffff\1\2\1\uffff\1\2\37\uffff\1\4\12\uffff"+
            "\3\4\7\uffff\1\5\2\uffff\1\3\6\uffff\1\1",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "193:1: primaryExpression : ( ( IDENTIFIER )=> IDENTIFIER -> ^( UNARY_EXPRESSION_STRING IDENTIFIER ) | ( ctfKeyword )=> ctfKeyword -> ^( UNARY_EXPRESSION_STRING ctfKeyword ) | ( STRING_LITERAL )=> STRING_LITERAL -> ^( UNARY_EXPRESSION_STRING_QUOTES STRING_LITERAL ) | numberLiteral | enumConstant | CHARACTER_LITERAL );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA4_1 = input.LA(1);

                         
                        int index4_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_CTFParser()) ) {s = 6;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index4_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA4_2 = input.LA(1);

                         
                        int index4_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_CTFParser()) ) {s = 8;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index4_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA4_3 = input.LA(1);

                         
                        int index4_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred3_CTFParser()) ) {s = 9;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index4_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 4, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA9_eotS =
        "\17\uffff";
    static final String DFA9_eofS =
        "\17\uffff";
    static final String DFA9_minS =
        "\1\4\16\uffff";
    static final String DFA9_maxS =
        "\1\121\16\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\2\1\7\uffff\1\2\4\uffff";
    static final String DFA9_specialS =
        "\17\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1\4\uffff\1\2\6\uffff\1\1\1\12\1\1\1\uffff\1\12\10\uffff"+
            "\3\12\22\uffff\1\1\12\uffff\3\1\7\uffff\1\1\2\uffff\1\1\6\uffff"+
            "\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "211:1: postfixExpression : ( primaryExpression ( postfixExpressionSuffix )* | ctfSpecifierHead ( postfixExpressionSuffix )+ );";
        }
    }
    static final String DFA16_eotS =
        "\22\uffff";
    static final String DFA16_eofS =
        "\22\uffff";
    static final String DFA16_minS =
        "\1\6\21\uffff";
    static final String DFA16_maxS =
        "\1\121\21\uffff";
    static final String DFA16_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1"+
        "\15\1\16\1\17\1\20\1\21";
    static final String DFA16_specialS =
        "\1\0\21\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\7\1\10\1\17\1\uffff\1\20\1\1\1\20\1\2\1\3\1\4\1\5\1\uffff"+
            "\1\20\1\15\3\uffff\1\6\1\16\1\11\1\12\1\13\1\14\64\uffff\1\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "268:1: typeSpecifier : ( FLOATTOK | INTTOK | LONGTOK | SHORTTOK | SIGNEDTOK | UNSIGNEDTOK | CHARTOK | DOUBLETOK | VOIDTOK | BOOLTOK | COMPLEXTOK | IMAGINARYTOK | structSpecifier | variantSpecifier | enumSpecifier | ctfTypeSpecifier | {...}? => typedefName );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_0 = input.LA(1);

                         
                        int index16_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA16_0==FLOATTOK) ) {s = 1;}

                        else if ( (LA16_0==INTTOK) ) {s = 2;}

                        else if ( (LA16_0==LONGTOK) ) {s = 3;}

                        else if ( (LA16_0==SHORTTOK) ) {s = 4;}

                        else if ( (LA16_0==SIGNEDTOK) ) {s = 5;}

                        else if ( (LA16_0==UNSIGNEDTOK) ) {s = 6;}

                        else if ( (LA16_0==CHARTOK) ) {s = 7;}

                        else if ( (LA16_0==DOUBLETOK) ) {s = 8;}

                        else if ( (LA16_0==VOIDTOK) ) {s = 9;}

                        else if ( (LA16_0==BOOLTOK) ) {s = 10;}

                        else if ( (LA16_0==COMPLEXTOK) ) {s = 11;}

                        else if ( (LA16_0==IMAGINARYTOK) ) {s = 12;}

                        else if ( (LA16_0==STRUCTTOK) ) {s = 13;}

                        else if ( (LA16_0==VARIANTTOK) ) {s = 14;}

                        else if ( (LA16_0==ENUMTOK) ) {s = 15;}

                        else if ( (LA16_0==FLOATINGPOINTTOK||LA16_0==INTEGERTOK||LA16_0==STRINGTOK) ) {s = 16;}

                        else if ( (LA16_0==IDENTIFIER) && (( inTypealiasAlias() || isTypeName(input.LT(1).getText()) ))) {s = 17;}

                         
                        input.seek(index16_0);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA23_eotS =
        "\6\uffff";
    static final String DFA23_eofS =
        "\6\uffff";
    static final String DFA23_minS =
        "\1\61\1\5\1\0\1\5\2\uffff";
    static final String DFA23_maxS =
        "\2\121\1\0\1\121\2\uffff";
    static final String DFA23_acceptS =
        "\4\uffff\1\1\1\2";
    static final String DFA23_specialS =
        "\2\uffff\1\0\3\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\1\37\uffff\1\2",
            "\1\3\53\uffff\1\1\37\uffff\1\2",
            "\1\uffff",
            "\1\3\53\uffff\1\1\37\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "353:10: ({...}? => declaratorList -> ^( TYPEDEF declaratorList declarationSpecifiers ) | structOrVariantDeclaratorList -> ^( SV_DECLARATION declarationSpecifiers structOrVariantDeclaratorList ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA23_2 = input.LA(1);

                         
                        int index23_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((inTypedef())) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index23_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 23, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA31_eotS =
        "\35\uffff";
    static final String DFA31_eofS =
        "\1\3\34\uffff";
    static final String DFA31_minS =
        "\1\5\34\uffff";
    static final String DFA31_maxS =
        "\1\121\34\uffff";
    static final String DFA31_acceptS =
        "\1\uffff\1\1\1\2\1\3\31\uffff";
    static final String DFA31_specialS =
        "\35\uffff}>";
    static final String[] DFA31_transitionS = {
            "\4\3\1\uffff\7\3\1\uffff\2\3\2\uffff\7\3\7\uffff\1\1\2\uffff"+
            "\1\3\4\uffff\1\3\1\uffff\1\2\1\uffff\2\3\37\uffff\1\3",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "431:13: ( enumContainerType enumBody | enumBody | )";
        }
    }
    static final String DFA53_eotS =
        "\34\uffff";
    static final String DFA53_eofS =
        "\1\2\33\uffff";
    static final String DFA53_minS =
        "\1\5\33\uffff";
    static final String DFA53_maxS =
        "\1\121\33\uffff";
    static final String DFA53_acceptS =
        "\1\uffff\1\1\1\2\31\uffff";
    static final String DFA53_specialS =
        "\34\uffff}>";
    static final String[] DFA53_transitionS = {
            "\4\2\1\uffff\7\2\1\uffff\2\2\2\uffff\7\2\12\uffff\1\2\4\uffff"+
            "\1\2\1\uffff\1\1\1\uffff\2\2\37\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA53_eot = DFA.unpackEncodedString(DFA53_eotS);
    static final short[] DFA53_eof = DFA.unpackEncodedString(DFA53_eofS);
    static final char[] DFA53_min = DFA.unpackEncodedStringToUnsignedChars(DFA53_minS);
    static final char[] DFA53_max = DFA.unpackEncodedStringToUnsignedChars(DFA53_maxS);
    static final short[] DFA53_accept = DFA.unpackEncodedString(DFA53_acceptS);
    static final short[] DFA53_special = DFA.unpackEncodedString(DFA53_specialS);
    static final short[][] DFA53_transition;

    static {
        int numStates = DFA53_transitionS.length;
        DFA53_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA53_transition[i] = DFA.unpackEncodedString(DFA53_transitionS[i]);
        }
    }

    class DFA53 extends DFA {

        public DFA53(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 53;
            this.eot = DFA53_eot;
            this.eof = DFA53_eof;
            this.min = DFA53_min;
            this.max = DFA53_max;
            this.accept = DFA53_accept;
            this.special = DFA53_special;
            this.transition = DFA53_transition;
        }
        public String getDescription() {
            return "599:15: ( ctfBody )?";
        }
    }
 

    public static final BitSet FOLLOW_declaration_in_parse449 = new BitSet(new long[]{0x00000000FFFFFFE0L,0x0000000000020000L});
    public static final BitSet FOLLOW_EOF_in_parse452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIGN_in_numberLiteral474 = new BitSet(new long[]{0xE004000000000000L});
    public static final BitSet FOLLOW_HEX_LITERAL_in_numberLiteral485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_numberLiteral506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OCTAL_LITERAL_in_numberLiteral527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_primaryExpression565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctfKeyword_in_primaryExpression591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_primaryExpression611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numberLiteral_in_primaryExpression636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumConstant_in_primaryExpression642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARACTER_LITERAL_in_primaryExpression648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPENBRAC_in_postfixExpressionSuffix661 = new BitSet(new long[]{0xE0040000E0170210L,0x0000000000020480L});
    public static final BitSet FOLLOW_unaryExpression_in_postfixExpressionSuffix663 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_CLOSEBRAC_in_postfixExpressionSuffix665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_postfixExpressionSuffix675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ARROW_in_postfixExpressionSuffix681 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_postfixExpressionSuffix684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primaryExpression_in_postfixExpression716 = new BitSet(new long[]{0x0018040000000002L});
    public static final BitSet FOLLOW_postfixExpressionSuffix_in_postfixExpression718 = new BitSet(new long[]{0x0018040000000002L});
    public static final BitSet FOLLOW_ctfSpecifierHead_in_postfixExpression725 = new BitSet(new long[]{0x0018040000000000L});
    public static final BitSet FOLLOW_postfixExpressionSuffix_in_postfixExpression727 = new BitSet(new long[]{0x0018040000000002L});
    public static final BitSet FOLLOW_postfixExpression_in_unaryExpression743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_enumConstant760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumConstant774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctfKeyword_in_enumConstant788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_declaration816 = new BitSet(new long[]{0x0003000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_declaratorList_in_declaration818 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_TERM_in_declaration821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctfSpecifier_in_declaration889 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_TERM_in_declaration891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_storageClassSpecifier_in_declarationSpecifiers929 = new BitSet(new long[]{0x000000001FCDFDE2L,0x0000000000020000L});
    public static final BitSet FOLLOW_typeQualifier_in_declarationSpecifiers939 = new BitSet(new long[]{0x000000001FCDFDE2L,0x0000000000020000L});
    public static final BitSet FOLLOW_typeSpecifier_in_declarationSpecifiers949 = new BitSet(new long[]{0x000000001FCDFDE2L,0x0000000000020000L});
    public static final BitSet FOLLOW_declarator_in_declaratorList979 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_SEPARATOR_in_declaratorList982 = new BitSet(new long[]{0x0002000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_declarator_in_declaratorList984 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_abstractDeclarator_in_abstractDeclaratorList1014 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_SEPARATOR_in_abstractDeclaratorList1017 = new BitSet(new long[]{0x0002100000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_abstractDeclarator_in_abstractDeclaratorList1019 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_TYPEDEFTOK_in_storageClassSpecifier1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATTOK_in_typeSpecifier1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTTOK_in_typeSpecifier1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONGTOK_in_typeSpecifier1077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHORTTOK_in_typeSpecifier1083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIGNEDTOK_in_typeSpecifier1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNSIGNEDTOK_in_typeSpecifier1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARTOK_in_typeSpecifier1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLETOK_in_typeSpecifier1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOIDTOK_in_typeSpecifier1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLTOK_in_typeSpecifier1119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMPLEXTOK_in_typeSpecifier1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMAGINARYTOK_in_typeSpecifier1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structSpecifier_in_typeSpecifier1137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variantSpecifier_in_typeSpecifier1143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumSpecifier_in_typeSpecifier1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctfTypeSpecifier_in_typeSpecifier1155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typedefName_in_typeSpecifier1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTTOK_in_typeQualifier1178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALIGNTOK_in_alignAttribute1191 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LPAREN_in_alignAttribute1193 = new BitSet(new long[]{0xE0040000E0170210L,0x0000000000020480L});
    public static final BitSet FOLLOW_unaryExpression_in_alignAttribute1195 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_RPAREN_in_alignAttribute1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURL_in_structBody1231 = new BitSet(new long[]{0x00008000FFFFFFE0L,0x0000000000020000L});
    public static final BitSet FOLLOW_structOrVariantDeclarationList_in_structBody1233 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_RCURL_in_structBody1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCTTOK_in_structSpecifier1264 = new BitSet(new long[]{0x0000400000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_structName_in_structSpecifier1289 = new BitSet(new long[]{0x0000400000000012L,0x0000000000020000L});
    public static final BitSet FOLLOW_alignAttribute_in_structSpecifier1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structBody_in_structSpecifier1347 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_alignAttribute_in_structSpecifier1378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structBody_in_structSpecifier1494 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_alignAttribute_in_structSpecifier1512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_structName1578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structOrVariantDeclaration_in_structOrVariantDeclarationList1599 = new BitSet(new long[]{0x00000000FFFFFFE2L,0x0000000000020000L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_structOrVariantDeclaration1632 = new BitSet(new long[]{0x0002000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_declaratorList_in_structOrVariantDeclaration1673 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_structOrVariantDeclaratorList_in_structOrVariantDeclaration1713 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_typealiasDecl_in_structOrVariantDeclaration1772 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_TERM_in_structOrVariantDeclaration1784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifier_in_specifierQualifierList1798 = new BitSet(new long[]{0x000000001FCDFDE2L,0x0000000000020000L});
    public static final BitSet FOLLOW_typeSpecifier_in_specifierQualifierList1802 = new BitSet(new long[]{0x000000001FCDFDE2L,0x0000000000020000L});
    public static final BitSet FOLLOW_structOrVariantDeclarator_in_structOrVariantDeclaratorList1835 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_SEPARATOR_in_structOrVariantDeclaratorList1838 = new BitSet(new long[]{0x0002000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_structOrVariantDeclarator_in_structOrVariantDeclaratorList1840 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_declarator_in_structOrVariantDeclarator1879 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_COLON_in_structOrVariantDeclarator1882 = new BitSet(new long[]{0xE004000000000000L});
    public static final BitSet FOLLOW_numberLiteral_in_structOrVariantDeclarator1884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIANTTOK_in_variantSpecifier1908 = new BitSet(new long[]{0x0000410000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_variantName_in_variantSpecifier1926 = new BitSet(new long[]{0x0000410000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_variantTag_in_variantSpecifier1956 = new BitSet(new long[]{0x0000410000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_variantBody_in_variantSpecifier1982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variantBody_in_variantSpecifier2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variantTag_in_variantSpecifier2071 = new BitSet(new long[]{0x0000410000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_variantBody_in_variantSpecifier2073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variantBody_in_variantSpecifier2080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variantName2112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURL_in_variantBody2143 = new BitSet(new long[]{0x00000000FFFFFFE0L,0x0000000000020000L});
    public static final BitSet FOLLOW_structOrVariantDeclarationList_in_variantBody2145 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_RCURL_in_variantBody2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_variantTag2174 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variantTag2176 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_GT_in_variantTag2178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENUMTOK_in_enumSpecifier2199 = new BitSet(new long[]{0x0000401000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_enumName_in_enumSpecifier2238 = new BitSet(new long[]{0x0000401000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_enumContainerType_in_enumSpecifier2270 = new BitSet(new long[]{0x0000401000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_enumBody_in_enumSpecifier2272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumBody_in_enumSpecifier2302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumContainerType_in_enumSpecifier2394 = new BitSet(new long[]{0x0000401000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_enumBody_in_enumSpecifier2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumBody_in_enumSpecifier2420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_enumName2464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURL_in_enumBody2485 = new BitSet(new long[]{0x0000000000050210L,0x0000000000020400L});
    public static final BitSet FOLLOW_enumeratorList_in_enumBody2487 = new BitSet(new long[]{0x0000800800000000L});
    public static final BitSet FOLLOW_SEPARATOR_in_enumBody2489 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_RCURL_in_enumBody2492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_enumContainerType2513 = new BitSet(new long[]{0x000000001FCDFDE0L,0x0000000000020000L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_enumContainerType2515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumerator_in_enumeratorList2536 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_SEPARATOR_in_enumeratorList2539 = new BitSet(new long[]{0x0000000000050210L,0x0000000000020400L});
    public static final BitSet FOLLOW_enumerator_in_enumeratorList2541 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_enumConstant_in_enumerator2567 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_enumeratorValue_in_enumerator2569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_enumeratorValue2583 = new BitSet(new long[]{0xE0040000E0170210L,0x0000000000020480L});
    public static final BitSet FOLLOW_unaryExpression_in_enumeratorValue2587 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_ELIPSES_in_enumeratorValue2626 = new BitSet(new long[]{0xE0040000E0170210L,0x0000000000020480L});
    public static final BitSet FOLLOW_unaryExpression_in_enumeratorValue2630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_declarator2673 = new BitSet(new long[]{0x0002000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_directDeclarator_in_declarator2676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_directDeclarator2714 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_directDeclaratorSuffix_in_directDeclarator2754 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_OPENBRAC_in_directDeclaratorSuffix2768 = new BitSet(new long[]{0xE0040000E0170210L,0x0000000000020480L});
    public static final BitSet FOLLOW_directDeclaratorLength_in_directDeclaratorSuffix2770 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_CLOSEBRAC_in_directDeclaratorSuffix2772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_directDeclaratorLength2800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pointer_in_abstractDeclarator2813 = new BitSet(new long[]{0x0002100000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_directAbstractDeclarator_in_abstractDeclarator2816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_directAbstractDeclarator_in_abstractDeclarator2841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_directAbstractDeclarator2878 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_LPAREN_in_directAbstractDeclarator2889 = new BitSet(new long[]{0x0002100000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_abstractDeclarator_in_directAbstractDeclarator2891 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_RPAREN_in_directAbstractDeclarator2893 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_OPENBRAC_in_directAbstractDeclarator2908 = new BitSet(new long[]{0xE0040800E0170210L,0x0000000000020480L});
    public static final BitSet FOLLOW_unaryExpression_in_directAbstractDeclarator2910 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_CLOSEBRAC_in_directAbstractDeclarator2913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POINTER_in_pointer2931 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_typeQualifierList_in_pointer2933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeQualifier_in_typeQualifierList2956 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_IDENTIFIER_in_typedefName2972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_typealiasTarget2989 = new BitSet(new long[]{0x0002100000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_abstractDeclaratorList_in_typealiasTarget2991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractDeclaratorList_in_typealiasAlias3017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_typealiasAlias3023 = new BitSet(new long[]{0x0002100000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_abstractDeclaratorList_in_typealiasAlias3025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPEALIASTOK_in_typealiasDecl3039 = new BitSet(new long[]{0x000000001FCDFDE0L,0x0000000000020000L});
    public static final BitSet FOLLOW_typealiasTarget_in_typealiasDecl3041 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_TYPE_ASSIGNMENT_in_typealiasDecl3043 = new BitSet(new long[]{0x000210001FCDFDE0L,0x0000000000020000L});
    public static final BitSet FOLLOW_typealiasAlias_in_typealiasDecl3045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ctfKeyword0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctfSpecifierHead_in_ctfSpecifier3145 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ctfBody_in_ctfSpecifier3147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typealiasDecl_in_ctfSpecifier3164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EVENTTOK_in_ctfSpecifierHead3185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STREAMTOK_in_ctfSpecifierHead3195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRACETOK_in_ctfSpecifierHead3205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENVTOK_in_ctfSpecifierHead3215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLOCKTOK_in_ctfSpecifierHead3225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALLSITETOK_in_ctfSpecifierHead3235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOATINGPOINTTOK_in_ctfTypeSpecifier3258 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ctfBody_in_ctfTypeSpecifier3260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGERTOK_in_ctfTypeSpecifier3275 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ctfBody_in_ctfTypeSpecifier3277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRINGTOK_in_ctfTypeSpecifier3292 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ctfBody_in_ctfTypeSpecifier3294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURL_in_ctfBody3327 = new BitSet(new long[]{0xE0048000FFFFFFF0L,0x0000000000020480L});
    public static final BitSet FOLLOW_ctfAssignmentExpressionList_in_ctfBody3329 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_RCURL_in_ctfBody3332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctfAssignmentExpression_in_ctfAssignmentExpressionList3351 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_TERM_in_ctfAssignmentExpressionList3353 = new BitSet(new long[]{0xE0040000FFFFFFF2L,0x0000000000020480L});
    public static final BitSet FOLLOW_unaryExpression_in_ctfAssignmentExpression3376 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_ASSIGNMENT_in_ctfAssignmentExpression3388 = new BitSet(new long[]{0xE0040000E0170210L,0x0000000000020480L});
    public static final BitSet FOLLOW_unaryExpression_in_ctfAssignmentExpression3392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_ASSIGNMENT_in_ctfAssignmentExpression3468 = new BitSet(new long[]{0x000000001FCDFDE0L,0x0000000000020000L});
    public static final BitSet FOLLOW_typeSpecifier_in_ctfAssignmentExpression3472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declarationSpecifiers_in_ctfAssignmentExpression3555 = new BitSet(new long[]{0x0002000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_declaratorList_in_ctfAssignmentExpression3559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typealiasDecl_in_ctfAssignmentExpression3582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_synpred1_CTFParser560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ctfKeyword_in_synpred2_CTFParser586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_synpred3_CTFParser606 = new BitSet(new long[]{0x0000000000000002L});

}